package com.demos.paymybuddy.exception;

import lombok.Getter;

@Getter
public class ServiceException extends Exception {
    private final String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }
}