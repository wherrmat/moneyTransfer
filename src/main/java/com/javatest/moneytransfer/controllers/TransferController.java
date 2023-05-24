package com.javatest.moneytransfer.controllers;

import com.javatest.moneytransfer.exception.EmptyTableException;
import com.javatest.moneytransfer.exception.EntityNotFoundException;
import com.javatest.moneytransfer.exception.InsufficientFundsException;
import com.javatest.moneytransfer.exception.MTExceptionHandler;
import com.javatest.moneytransfer.models.TransferModel;
import com.javatest.moneytransfer.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;
    @Autowired
    private MTExceptionHandler exceptionHandler;

    // Return a list with all transfers
    @GetMapping(path = "/alltransfers")
    public ResponseEntity<Object> getAllTransfers(){
        try{
            return ResponseEntity.ok(this.transferService.getTransfers());
        }catch (EmptyTableException ex){
            return exceptionHandler.handleEmptyTableException(ex);
        }
    }

    // Return a transfer searched by id
    @GetMapping(path = "/transferbyid/{id}")
    public ResponseEntity<Object> getTransferById(@PathVariable("id") Long id){
        try {
            return ResponseEntity.ok(this.transferService.getTransferById(id));
        }catch (EntityNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }

    // Return a list of transfers recorded searched by account id
    @GetMapping(path = "/transfersbyaccountid/{accountid}")
    public ResponseEntity<Object> getTransfersByAccountId(@PathVariable("accountid") Long accountid){
        try {
            return ResponseEntity.ok(this.transferService.getTransfersByAccountId(accountid));
        }catch (EntityNotFoundException ex){
            return exceptionHandler.handleAccountNotFoundException(ex);
        }
    }

    // Make a transfer
    @PostMapping(path = "/maketransfer")
    public ResponseEntity<Object> makeTransfer(@Valid @RequestBody TransferModel transfer){
        try {
            return ResponseEntity.ok(this.transferService.makeTransfer(transfer));
        }catch (InsufficientFundsException ex){
            return exceptionHandler.handleInsufficientFundsException(ex);
        }
    }
}
