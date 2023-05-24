package com.javatest.moneytransfer.exception;

public class EntityNotFoundException extends IllegalArgumentException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
