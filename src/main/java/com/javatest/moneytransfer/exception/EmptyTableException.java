package com.javatest.moneytransfer.exception;

public class EmptyTableException extends RuntimeException {
    public EmptyTableException(String message) {
        super(message);
    }
}
