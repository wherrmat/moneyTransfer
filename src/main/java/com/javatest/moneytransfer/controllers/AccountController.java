package com.javatest.moneytransfer.controllers;

import com.javatest.moneytransfer.models.AccountModel;
import com.javatest.moneytransfer.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController{
    @Autowired
    private AccountService accountService;

    // Return a list of all accounts
    @GetMapping
    public ArrayList<AccountModel> getAllAccounts(){
        return this.accountService.getAccounts();
    }

    // Return an account searched by ID
    @GetMapping(path = "/{id}")
    public Optional<AccountModel> getAccountById(@PathVariable Long id){
        return this.accountService.getById(id);
    }

    //
    @PostMapping
    public AccountModel createAccount(@RequestBody AccountModel account){
        return this.accountService.createAccount(account);
    }
}