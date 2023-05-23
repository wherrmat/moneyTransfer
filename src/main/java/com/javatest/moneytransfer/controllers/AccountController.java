package com.javatest.moneytransfer.controllers;

import com.javatest.moneytransfer.exception.AccountNotFoundException;
import com.javatest.moneytransfer.exception.ErrorResponse;
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

    // Return a list with all accounts
    @GetMapping(path = "/allaccounts")
    public ResponseEntity<Object> getAllAccounts(){
        try{
            ArrayList<AccountModel> accountsList = this.accountService.getAccounts();
            return ResponseEntity.ok().build();
        }catch (AccountNotFoundException ex){
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    // Return an account searched by ID
    @GetMapping(path = "/account{id}")
    public Optional<AccountModel> getAccountById(@PathVariable Long id){
        return this.accountService.getById(id);
    }

    // Create an account
    @PostMapping(path = "/createaccount")
    public AccountModel createAccount(@Valid @RequestBody AccountModel account){
        return this.accountService.createAccount(account);
    }

    // Update an account by id
    @PutMapping(path = "/account{id}")
    public AccountModel updateAccountById(@RequestBody AccountModel request, Long id){
        return this.accountService.updateById(request, id);
    }

    // Delete an account by id
    @DeleteMapping(path = "/delete/account{id}")
    public String deleteAccountById(@PathVariable("id") Long id){
        boolean ok = this.accountService.deleteAccount(id);
        if(ok){
            return "Account with id " + id + " has been deleted";
        }else{
            return "Error, the Account with id " + id + " has not been deleted";
        }
    }
}