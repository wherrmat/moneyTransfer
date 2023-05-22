package com.javatest.moneytransfer.services;

import com.javatest.moneytransfer.models.AccountModel;
import com.javatest.moneytransfer.models.TransferModel;
import com.javatest.moneytransfer.repositories.IAccountRepository;
import com.javatest.moneytransfer.repositories.ITransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TransferService {
    @Autowired
    private ITransferRepository transferRepository;
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    // Get all transfers from the database
    public ArrayList<TransferModel> getTransfers(){
        return (ArrayList<TransferModel>) transferRepository.findAll();
    }

    // Get an account searched by id
    public Optional<TransferModel> getById(Long id){
        return transferRepository.findById(id);
    }

    // Make a transfer
    public Optional<TransferModel> makeTransfer(TransferModel transfer) {
        Optional<AccountModel> sourceAccount = accountRepository.findById(transfer.getSourceAccountId());
        Optional<AccountModel> destinationAccount = accountRepository.findById(transfer.getDestinationAccountId());

        if (sourceAccount.isPresent()) {
            if(destinationAccount.isPresent()){
                AccountModel srcAccount = sourceAccount.get();
                AccountModel dstAccount = destinationAccount.get();

                if (srcAccount.getBalance().compareTo(transfer.getAmount()) >= 0) {

                    srcAccount.setBalance(srcAccount.getBalance().subtract(transfer.getAmount()));
                    dstAccount.setBalance(dstAccount.getBalance().add(transfer.getAmount()));

                    accountService.updateById(srcAccount,transfer.getSourceAccountId());
                    accountService.updateById(dstAccount, transfer.getDestinationAccountId());

                    System.out.println("Transfer was successful");
                    return Optional.of(transferRepository.save(transfer));
                }else{
                    System.out.println("Insufficient funds in the source account");
                    return null;
                }
            }else{
                System.out.println("Destination account doesn't exist");
                return null;
            }
        } else{
            System.out.println("Source account doesn't exist");
            return null;
        }
    }
}
