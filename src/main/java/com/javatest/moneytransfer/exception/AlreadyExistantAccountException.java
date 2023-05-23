package com.javatest.moneytransfer.exception;

public class AlreadyExistantAccountException extends RuntimeException {
    public AlreadyExistantAccountException(String message) {
        super(message);
    }
}
