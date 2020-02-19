package com.dev.cinema.service;

import com.dev.cinema.model.User;

public interface UserService {

    User add(User user);

    User getByEmail(String email);

    User getById(Long userId);
}
