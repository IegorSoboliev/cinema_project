package com.dev.cinema.controllers;

import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminInjectionController {
    private UserService userService;
    private RoleService roleService;
    private PasswordEncoder passwordEncoder;

    public AdminInjectionController(UserService userService, RoleService roleService,
                                    PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @EventListener
    public String addData(ContextRefreshedEvent event) throws EmailAlreadyRegisteredException {
        Role adminRole = new Role();
        adminRole.setRoleName("ADMIN");
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setRoleName("USER");
        roleService.add(userRole);

        User admin = new User();
        admin.setEmail("admin@yahoo.com");
        admin.setPassword(passwordEncoder.encode("1"));
        admin.addRole(roleService.getByRoleName("ADMIN"));
        userService.add(admin);
        return "Data was injected";
    }
}
