package com.javatest.moneytransfer.exception;


import org.springframework.http.HttpStatus;

public enum MessageUtil {
    OK("Succesfull procces.", 200),
    CREATED("Succesfull ccreation.", 201),
    UPDATED("Succesfull updating.", 200),
    DELETED("Succesfull deleting.", 200),
    BADREQUEST("There was an error with your request", 400),
    NOTFOUND("The entity was not found", 404),
    INTERNALERROR("Server error", 500),
    ;

    private String message;
    private int code;
    private MessageUtil(String message, int code){
        this.message = message;
        this.code = code;
    }

    public String getMessage(){
        return this.message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code = code;
    }

}
