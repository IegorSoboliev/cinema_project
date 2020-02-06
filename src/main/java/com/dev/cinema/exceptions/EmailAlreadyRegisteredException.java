package com.dev.cinema.exceptions;

public class EmailAlreadyRegisteredException extends AuthenticationException {

    public EmailAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailAlreadyRegisteredException(String message) {
        super(message);
    }
}
