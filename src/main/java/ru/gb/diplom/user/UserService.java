package ru.gb.diplom.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.position.Position;
import ru.gb.diplom.role.Role;
import ru.gb.diplom.role.RoleRepository;
import ru.gb.diplom.util.CSVUtils;
import ru.gb.diplom.util.EntityUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository repository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.repository = repository;
        this.roleRepository = roleRepository;
    }




    public UserService() {
        super();
    }




    public UserService(UserRepository repository) {
        super();
        this.repository = repository;
    }


    /**
     * This method creates {@link User}
     * @param createUserDto
     * @return new user id
     */
    public Long createUser(CreateUserDto createUserDto) {
        createUserDto.setActive(true);
        createUserDto.setPassword
                (passwordEncoder.encode
                        (createUserDto.getPassword()));
        createUserDto.setCreated(Timestamp.from(Instant.now()));
        User user = repository.save(UserMapper.toEntity(createUserDto));
        return user.getId();
    }

    UserDto getUser(Long id) {
        return repository.findById(id).map(UserMapper::toDto).orElseThrow(() -> new ModelNotFound("User", id));
    }

    /**
     * This method gets all users from repository
     * @return
     */
    List<UserDto> getUsers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<UserDto> getUsersWithPosition(Position position) {
        return StreamSupport.stream(repository.findByPosition(position).spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    List<UserDto> getUserWithRole(Collection<Role> roles) {
        return repository.findByRolesIn(roles).stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }


    void deleteUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ModelNotFound("User", id));
        repository.delete(user);
    }


    /**
     * This method updates user if user has role ADMIN or user.id == logged user.id
     * @param userDto
     * @return true when an update was executed
     */
    boolean isUpdated(UserDto userDto) {
        User user = repository.findById(userDto.getId()).orElseThrow(() -> new ModelNotFound("User", userDto.getId()));
        User loggedUser = getLoggedUser().orElseThrow(() -> new ModelNotFound("User", "as logged"));
        if (loggedUser.getRoles().stream().anyMatch(o -> "ROLE_ADMIN".equals(o.getName()))
                || user.getId() == loggedUser.getId()) {
            EntityUtils.setter(userDto.getUsername(), t -> user.setUsername(t));
            EntityUtils.setter(userDto.getPassword(), t -> user.setPassword(passwordEncoder.encode(t)));
            EntityUtils.setter(userDto.getFirstname(), t -> user.setFirstname(t));
            EntityUtils.setter(userDto.getLastname(), t -> user.setLastname(t));
            EntityUtils.setter(userDto.getEmail(), t -> user.setEmail(t));
            EntityUtils.setter(userDto.getPhone(), t -> user.setPhone(t));
            EntityUtils.setter(userDto.getActive(), t -> user.setActive(t));
            EntityUtils.setter(userDto.getRoles(), t -> user.setRoles(t));
            EntityUtils.setter(userDto.getPosition(), t -> user.setPosition(t));
            repository.save(user);
            return true;
        }
        return false;
    }
    /**
     * This method sets flag {@link active} on false for entity with certain id
     * @param id
     */
    void deactivateUser(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ModelNotFound("User", id));
        user.setActive(false);
        repository.save(user);
    }
    /**
     * This method finds user by username
     * @param username
     * @return user entity
     */
    public User findByUserName(String username) {
        return repository.findByUsername(username);
    }
    /**
     * This method gets logged {@link User} and returns Optional
     * @return Optional
     */
    Optional<User> getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return Optional.ofNullable(repository.findByUsername(auth.getName()));
    }

    /**
     * This method gets {@link User} as a parameter and get user logged in security session.
     *  Then logged user is updated with credentials coming from parameter and finally
     *  updated user is saved to repository
     * @param user
     */
    void setLoggedUser(User user) {
        User tmpUser = getLoggedUser().orElseThrow(() -> new ModelNotFound("User", "as logged"));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getUsername(), passwordEncoder, grantedAuthorities));
        tmpUser.setUsername(user.getUsername());
        tmpUser.setPassword(passwordEncoder.encode(user.getPassword()));
        tmpUser.setRoles(user.getRoles());
        repository.save(tmpUser);
    }

    @ExceptionHandler(ModelNotFound.class)
    ResponseEntity handleException(ModelNotFound e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    List<UserDto> getUserSearch(String username, String firstname, String lastname, String email, String phone,
                                Boolean active, Position position) {
        List<UserDto> dtos = StreamSupport.stream(repository.findAll().spliterator(), false)
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
        return filter(dtos, username, firstname, lastname, email, phone,
                active, position);
    }

    private static List<UserDto> filter(List<UserDto> dtos, String username, String firstname,
                                        String lastname, String email, String phone,
                                        Boolean active, Position position){
        return dtos.stream()
                .filter(addFilter(username, userDto -> username.equals(userDto.getUsername())))
                .filter(addFilter(firstname, userDto -> firstname.equals(userDto.getFirstname())))
                .filter(addFilter(lastname, userDto -> lastname.equals(userDto.getLastname())))
                .filter(addFilter(email, userDto -> email.equals(userDto.getEmail())))
                .filter(addFilter(phone, userDto -> phone.equals(userDto.getPhone())))
                .filter(addFilter(active, userDto -> active.equals(userDto.getActive())))
                .filter(addFilter(position, userDto -> position.equals(userDto.getPosition())))
                .collect(Collectors.toList());
    }


    private static Predicate<UserDto> addFilter(Object value, Predicate<UserDto> predicate) {
        if (value != null) {
            return predicate;
        }
        return userDto -> true;
    }

    void importDataFromCSV(String filename) {
        List<User> users = CSVUtils.buildListFromCSV(filename, User.class);
        saveToDB(users);
    }

    void saveToDB(List<User> users) {
        repository.saveAll(users);
    }

    void exportDataToCSV(String filename) {
        List<User> users = buildListFromDB();
        CSVUtils.exportListToCSV(filename, users);
    }

    List<User> buildListFromDB() {
        return repository.findAll();
    }

}
