package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.dao.UserDao;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao, RoleDao roleDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(roleDao.getByRoleName("USER"));

        userDao.add(user);
        return user;
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User getById(Long userId) {
        return userDao.getById(userId);
    }
}
