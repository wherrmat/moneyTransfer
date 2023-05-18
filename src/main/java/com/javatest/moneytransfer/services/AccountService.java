package com.javatest.moneytransfer.services;

import com.javatest.moneytransfer.models.AccountModel;
import com.javatest.moneytransfer.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    IAccountRepository accountRepository;

    public ArrayList<AccountModel> getAccounts(){
        return (ArrayList<AccountModel>) accountRepository.findAll();
    }

    public AccountModel createAccount(AccountModel account){
        return accountRepository.save(account);
    }

    public Optional<AccountModel> getById(Long id){
        return accountRepository.findById(id);
    }

    public AccountModel addBalanceById(Long id, BigDecimal ammount){
        AccountModel account = accountRepository.getReferenceById(id);

        account.addBalance(ammount);
    }
}
