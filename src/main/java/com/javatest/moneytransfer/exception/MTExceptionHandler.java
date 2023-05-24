package com.javatest.moneytransfer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MTExceptionHandler extends ResponseEntityExceptionHandler {

    // Operation failed exception
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Object> handleOperationFailedException(OperationFailedException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Empty table exception
    @ExceptionHandler(EmptyTableException.class)
    public ResponseEntity<Object> handleEmptyTableException(EmptyTableException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Already existant account exception
    @ExceptionHandler(AlreadyExistantAccountException.class)
    public ResponseEntity<Object> handleAlreadyExistantAccountException(AlreadyExistantAccountException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT, ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    // Account doesn't exist in the database
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleAccountNotFoundException(EntityNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Insufficient funds exception
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Object> handleInsufficientFundsException(InsufficientFundsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getClass().getSimpleName(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
