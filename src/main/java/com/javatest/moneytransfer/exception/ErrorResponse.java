package com.javatest.moneytransfer.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

        private HttpStatus status;
        private String exception;
        private String message;

        public ErrorResponse(HttpStatus status, String exception, String message) {
                this.status = status;
                this.exception = exception;
                this.message = message;
        }

        public HttpStatus getStatus(){
                return this.status;
        }
        public void setStatus(HttpStatus status){
                this.status = status;
        }
        public String getException(){
                return this.exception;
        }
        public void setException(String exception){
                this.exception = exception;
        }
        public String getMessage(){
                return this.message;
        }
        public void setMessage(String message){
                this.message = message;
        }
}
