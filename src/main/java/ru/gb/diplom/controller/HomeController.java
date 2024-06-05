package ru.gb.diplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gb.diplom.role.Role;
import ru.gb.diplom.role.RoleService;
import ru.gb.diplom.user.CreateUserDto;
import ru.gb.diplom.user.UserService;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {


    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    private final UserService userService;


    private final RoleService roleService;


    public HomeController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/login")
    public String home() {
        return "/";
    }

    @ResponseBody
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @GetMapping("/add-user")
    @ResponseBody
    public String addUser() {
        CreateUserDto u = new CreateUserDto();
        u.setFirstname("default");
        u.setLastname("admin");
        u.setUsername("admin");
        u.setPassword("admin");
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName("ROLE_ADMIN"));
        u.setRoles(roles);
        u.setEmail("admin@admin.pl");
        userService.createUser(u);
        return "add-user";
    }



}
