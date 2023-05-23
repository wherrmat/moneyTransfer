package com.javatest.moneytransfer.exception;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;

public class MTException extends Exception{
    private ArrayList<String> messages = new ArrayList<>();
    private HttpStatus code;

    public MTException(ArrayList<String> messages, HttpStatus code){
        super();
        this.messages = messages;
        this.code = code;
    }

    public MTException(String messages, HttpStatus code){
        super();
        this.messages.add(messages);
        this.code = code;
    }
}
