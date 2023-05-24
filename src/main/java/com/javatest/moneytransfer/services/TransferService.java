package com.javatest.moneytransfer.services;

import com.javatest.moneytransfer.exception.EmptyTableException;
import com.javatest.moneytransfer.exception.EntityNotFoundException;
import com.javatest.moneytransfer.exception.InsufficientFundsException;
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
        ArrayList<TransferModel> arrayList = (ArrayList<TransferModel>) transferRepository.findAll();
        if(arrayList.isEmpty()){
            throw new EmptyTableException("There are no transfers in the database");
        }else{
            return arrayList;
        }
    }

    // Get an account searched by id
    public Optional<TransferModel> getTransferById(Long id){
        if(transferRepository.existsById(id)) {
            return transferRepository.findById(id);
        }else {
            throw new EntityNotFoundException("The transfer with id " + id + " doesn't exist");
        }
    }

    public ArrayList<TransferModel> getTransfersByAccountId(Long accountId){
        if(transferRepository.existsBySourceAccountIdOrDestinationAccountId(accountId, accountId)) {
            return transferRepository.findBySourceAccountIdOrDestinationAccountId(accountId, accountId);
        }else {
            throw new EntityNotFoundException("There are not transfers recorded for the account with id " + accountId);
        }
    }

    // Make a transfer
    public TransferModel makeTransfer(TransferModel transfer) {
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

                    return transferRepository.save(transfer);
                }else{
                    throw new InsufficientFundsException("Insufficient funds in the source account with id " + transfer.getSourceAccountId());
                }
            }else{
                throw new EntityNotFoundException("Destination account with id " + transfer.getDestinationAccountId() + " doesn't exist");
            }
        } else{
            throw new EntityNotFoundException("Source account with id " + transfer.getSourceAccountId() + " doesn't exist");
        }
    }
}
