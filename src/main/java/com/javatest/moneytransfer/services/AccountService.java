package com.javatest.moneytransfer.services;

import com.javatest.moneytransfer.exception.EntityNotFoundException;
import com.javatest.moneytransfer.exception.AlreadyExistantAccountException;
import com.javatest.moneytransfer.exception.EmptyTableException;
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

    // Create an account
    public AccountModel createAccount(AccountModel account){
        if(accountRepository.existsByAccountNumber(account.getAccountNumber())){
            throw new AlreadyExistantAccountException("The account you are trying to create already exists in the database");
        }else{
            return accountRepository.save(account);
        }
    }

    // Get all accounts from the database
    public ArrayList<AccountModel> getAccounts(){
        ArrayList<AccountModel> accountsList = (ArrayList<AccountModel>) accountRepository.findAll();
        if (accountsList.isEmpty()) {
            throw new EmptyTableException("There are no accounts in the database");
        }else{
            return accountsList;
        }
    }

    // Get an account searched by id
    public Optional<AccountModel> getById(Long id){
        if(accountRepository.existsById(id)) {
            return accountRepository.findById(id);
        }else {
            throw new EntityNotFoundException("The account with id " + id + " doesn't exist");
        }
    }
    // Get an account searched by account number
    public AccountModel getByAccountNumber(String accountNumber){
        if(accountRepository.existsByAccountNumber(accountNumber)) {
            return accountRepository.findByAccountNumber(accountNumber);
        }else {
            throw new EntityNotFoundException("The account with account_number " + accountNumber + " doesn't exist");
        }
    }

    // Update an account using the id number, not working
    public AccountModel updateById(AccountModel request, Long id){
        if(accountRepository.existsById(id)) {
            AccountModel account = accountRepository.findById(id).get();
            account.setAccountNumber(request.getAccountNumber());
            account.setBalance(request.getBalance());
            return account;
        }else {
            throw new EntityNotFoundException("The account with id " + id + " doesn't exist");
        }
    }


    // Delete an account using the id
    public String deleteAccountById(Long id){
        if(accountRepository.existsById(id)) {
            accountRepository.deleteById(id);
            return("The account with the id " + id + " has been deleted");
        }else {
            throw new EntityNotFoundException("The account with id " + id + " doesn't exist");
        }
    }

    // Delete an account using the account_number
    public String deleteAccountByAccountNumber(String accountNumber){
        if(accountRepository.existsByAccountNumber(accountNumber)) {
            AccountModel accountToDelete = accountRepository.findByAccountNumber(accountNumber);
            accountRepository.deleteById(accountToDelete.getId());
            return ("The account with account_number " + accountNumber + " has been deleted");
        }else {
            throw new EntityNotFoundException("The account with account_number " + accountNumber + " doesn't exist");
        }
    }

}
