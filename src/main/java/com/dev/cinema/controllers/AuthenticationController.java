package com.dev.cinema.controllers;

import com.dev.cinema.dto.UserRegistrationDto;
import com.dev.cinema.dto.UserRequestDto;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.exceptions.EmailAlreadyRegisteredException;
import com.dev.cinema.service.AuthenticationService;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private AuthenticationService authenticationService;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/authentication")
    public String login(@RequestBody @Valid UserRequestDto userRequestDto) {
        try {
            authenticationService.login(userRequestDto.getEmail(), userRequestDto.getPassword());
        } catch (AuthenticationException e) {
            LOGGER.warn("Wrong email or password", e);
        }
        return "Welcome!";
    }

    @PostMapping("/registration")
    public String register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        try {
            authenticationService.register(userRegistrationDto.getEmail(),
                    userRegistrationDto.getPassword());
        } catch (EmailAlreadyRegisteredException e) {
            LOGGER.warn("This email already registered", e);
        }
        return "Welcome!";
    }
}
