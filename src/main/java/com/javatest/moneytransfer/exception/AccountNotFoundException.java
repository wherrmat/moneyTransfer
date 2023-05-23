package com.javatest.moneytransfer.exception;

public class AccountNotFoundException extends IllegalArgumentException {
    public AccountNotFoundException(String message) {
        super(message);
    }
}
