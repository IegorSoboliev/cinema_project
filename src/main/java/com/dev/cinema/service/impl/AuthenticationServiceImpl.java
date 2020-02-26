package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.model.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private UserService userService;
    private ShoppingCartService shoppingCartService;
    private PasswordEncoder passwordEncoder;
    private RoleDao roleDao;

    public AuthenticationServiceImpl(UserService userService, ShoppingCartService
            shoppingCartService, PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }

    public User login(String email, String password) throws AuthenticationException {
        User user = userService.getByEmail(email);
        if (user == null
                || !user.getPassword().equals(passwordEncoder.encode(password))) {
            throw new AuthenticationException("Wrong email or password");
        }
        return user;
    }

    public User register(String email, String password) throws EmailAlreadyRegisteredException {
        if (userService.getByEmail(email) != null) {
            throw new EmailAlreadyRegisteredException("This email already registered");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.addRole(roleDao.getByRoleName("USER"));
        User userRegistered = userService.add(user);
        shoppingCartService.registerNewShoppingCart(userRegistered);
        return userRegistered;
    }
}
