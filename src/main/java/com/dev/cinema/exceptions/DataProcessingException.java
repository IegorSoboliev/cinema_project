package com.dev.cinema.exceptions;

public class DataProcessingException extends RuntimeException {

    public DataProcessingException(String message, Exception e) {
        super(message);
    }

}
