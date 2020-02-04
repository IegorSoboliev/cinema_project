package com.dev.cinema.exceptions;

public class DataProcessingException extends Exception {

    public DataProcessingException(String message, Exception e) {
        super(message);
    }
}
