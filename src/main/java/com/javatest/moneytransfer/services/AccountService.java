package com.javatest.moneytransfer.services;

import com.javatest.moneytransfer.models.AccountModel;
import com.javatest.moneytransfer.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private IAccountRepository accountRepository;

    // Get all accounts from the database
    public ArrayList<AccountModel> getAccounts(){
        return (ArrayList<AccountModel>) accountRepository.findAll();
    }

    // Create an account
    public AccountModel createAccount(AccountModel account){
        return accountRepository.save(account);
    }

    // Get an account searched by id
    public Optional<AccountModel> getById(Long id){
        return accountRepository.findById(id);
    }

    // Update an account using the id number
    public AccountModel updateById(AccountModel request, Long id){
        AccountModel account = accountRepository.findById(id).get();
        account.setAccountNumber(request.getAccountNumber());
        account.setBalance(request.getBalance());
        return account;
    }

    // Delete an account
    public boolean deleteAccount(Long id){
        try{
            accountRepository.deleteById(id);
            System.out.println("The account with the id " + id + " has been deleted");
            return true;
        }catch (Exception e){
            System.out.println("Unable to delete account with the id " + id);
            return false;
        }
    }

}
