package com.javatest.moneytransfer.exception;

public class InsufficientFundsException extends IllegalArgumentException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
