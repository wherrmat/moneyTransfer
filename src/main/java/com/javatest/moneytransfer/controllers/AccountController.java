package com.javatest.moneytransfer.controllers;

import com.javatest.moneytransfer.exception.*;
import com.javatest.moneytransfer.models.AccountModel;
import com.javatest.moneytransfer.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController{
    @Autowired
    private AccountService accountService;
    @Autowired
    private MTExceptionHandler exceptionHandler;

    // Create an account
    @PostMapping(path = "/create")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody AccountModel account){
        try{
            AccountModel newAccount = this.accountService.createAccount(account);
            return ResponseEntity.ok(newAccount);
        }catch (AlreadyExistantAccountException ex){
            return exceptionHandler.handleAlreadyExistantAccountException(ex);
        }
    }

    // Return a list with all accounts
    @GetMapping(path = "/allaccounts")
    public ResponseEntity<Object> getAllAccounts(){
        try{
            return ResponseEntity.ok(this.accountService.getAccounts());
        }catch (EmptyTableException ex){
            return exceptionHandler.handleEmptyTableException(ex);
        }
    }

    // Return an account searched by ID
    @GetMapping(path = "/getbyid/{id}")
    public ResponseEntity<Object> getAccountById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(this.accountService.getById(id));
        }catch (AccountNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }

    // Return an account searched by Account Number
    @GetMapping(path = "/getbyaccountnumber/{account_number}")
    public ResponseEntity<Object> getAccountByAccountNumber(@PathVariable String account_number){
        try {
            return ResponseEntity.ok(this.accountService.getByAccountNumber(account_number));
        }catch (AccountNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }

    /**
    // Update an account using the id number, not working
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<Object> updateAccountById(@Valid @RequestBody AccountModel request, Long id){
        try {
            return ResponseEntity.ok(this.accountService.updateById(request, id));
        }catch (AccountNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }
    */

    // Delete an account searched by id
    @DeleteMapping(path = "/deletebyid/{id}")
    public ResponseEntity<Object> deleteAccountById(@PathVariable("id") Long id){
        try{
            return ResponseEntity.ok(this.accountService.deleteAccountById(id));
        }catch(AccountNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }

    // Delete an account searched by account_number
    @DeleteMapping(path = "/deletebyaccountnumber/{account_number}")
    public ResponseEntity<Object> deleteAccountByAccountNumber(@PathVariable("account_number") String account_number){
        try{
            return ResponseEntity.ok(this.accountService.deleteAccountByAccountNumber(account_number));
        }catch(AccountNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }catch(OperationFailedException ex){
            return exceptionHandler.handleOperationFailedException(ex);
        }
    }
}