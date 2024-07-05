package ru.gb.diplom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.gb.diplom.role.Role;
import ru.gb.diplom.role.RoleRepository;
import ru.gb.diplom.user.User;
import ru.gb.diplom.user.UserRepository;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init() {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                Role adminRole = roleRepository.findOneByName("ROLE_ADMIN");
                if (adminRole == null) {
                    adminRole = new Role();
                    adminRole.setName("ROLE_ADMIN");
                    roleRepository.save(adminRole);
                }

                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin"));
                adminUser.setFirstname("Admin");
                adminUser.setLastname("User");
                adminUser.setEmail("admin@example.com");
                adminUser.setPhone("123-456-789");
                adminUser.setActive(true);
                adminUser.setCreated(Timestamp.from(Instant.now()));

                Set<Role> roles = new HashSet<>();
                roles.add(adminRole);
                adminUser.setRoles(roles);

                userRepository.save(adminUser);
            }
        };
    }
}