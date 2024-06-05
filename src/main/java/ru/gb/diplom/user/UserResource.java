package ru.gb.diplom.user;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import ru.gb.diplom.document.DocumentResource;
import ru.gb.diplom.exception.ModelNotFound;
import ru.gb.diplom.position.Position;
import ru.gb.diplom.role.Role;
import ru.gb.diplom.util.CSVUtils;

import java.util.Collections;

@RequestMapping("/users")
@RestController
public class UserResource {

    @Autowired
    UserRepository repository;



    private final UserService service;


    private static final Logger log = LoggerFactory.getLogger(DocumentResource.class);



    public UserResource(UserService service) {
        this.service = service;
    }



    @GetMapping("/{id}")
    ResponseEntity getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getUser(id));
    }


    @GetMapping("/username/{username}")
    ResponseEntity getUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(service.findByUserName(username));
    }

    @GetMapping("/position/{position}")
    ResponseEntity getUser(@PathVariable("position") Position position) {
        return ResponseEntity.ok(service.getUsersWithPosition(position));
    }

    @GetMapping("/search")
    ResponseEntity getAdditionalSearchRequest(@RequestParam(name="username", required = false) String username,
                                              @RequestParam(name="firstname", required = false) String firstname,
                                              @RequestParam(name="lastname", required = false) String lastname,
                                              @RequestParam(name="email", required = false) String email,
                                              @RequestParam(name="phone", required = false) String phone,
                                              @RequestParam(name="active", required = false) Boolean active,
                                              @RequestParam(name="position", required = false) Position position) {
        return ResponseEntity.ok(service.getUserSearch(username, firstname,
                lastname, email, phone, active, position));
    }


    @GetMapping("/role/{role}")
    public ResponseEntity getUser(@PathVariable("role") Role role) {
        return ResponseEntity.ok(service.getUserWithRole(Collections.singletonList(role)));
    }

    @GetMapping
    ResponseEntity getUsers() {
        return ResponseEntity.ok(service.getUsers());
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    ResponseEntity createUser(@Valid @RequestBody CreateUserDto createUserDto ) {
        long id = service.createUser(createUserDto);
        log.debug("User created with id: " + id);
        return ResponseEntity.ok(id);
    }


    @PutMapping
    ResponseEntity updateUser(@Valid @RequestBody UserDto userDto) {
        if (service.isUpdated(userDto)) {
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/{id}")
    ResponseEntity deleteUser(@PathVariable("id") Long id) {
        service.deactivateUser(id);
        return ResponseEntity.accepted().build();

    }


    @GetMapping("/import")
    ResponseEntity getImportListFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        return ResponseEntity.ok(CSVUtils.buildListFromCSV(filename, User.class));
    }

    @PostMapping("/import")
    ResponseEntity addEntitiesFromCSV(@RequestParam(name = "filename", required = true) String filename) {
        new UserService(repository).importDataFromCSV(filename);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/export")
    ResponseEntity exportDBtoFile(@RequestParam(name = "filename", required = true) String filename) {
        new UserService(repository).exportDataToCSV(filename);
        return ResponseEntity.accepted().build();
    }



    @ExceptionHandler(ModelNotFound.class)
    ResponseEntity handleException(ModelNotFound e){
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}