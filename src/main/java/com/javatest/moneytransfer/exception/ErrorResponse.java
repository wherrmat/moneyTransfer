package com.javatest.moneytransfer.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
        /**
         * "type": "https://example.com/probs/out-of-credit",
         * "title": "You do not have enough credit.",
         * "detail": "Your current balance is 30, but that costs 50.",
         * "instance": "/account/12345/msgs/abc",
         * "balance": 30,
         * "accounts": ["/account/12345",
         *              "/account/67890"]
         */


        private HttpStatus status;
        private String message;

        public ErrorResponse(HttpStatus status, String message) {
                this.status = status;
                this.message = message;
        }

        public HttpStatus getStatus(){
                return this.status;
        }
        public void setStatus(HttpStatus status){
                this.status = status;
        }
        public String getMessage(){
                return this.message;
        }
        public void setMessage(String message){
                this.message = message;
        }
}
