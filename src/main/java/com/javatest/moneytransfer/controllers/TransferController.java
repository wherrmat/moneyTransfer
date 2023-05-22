package com.javatest.moneytransfer.controllers;

import com.javatest.moneytransfer.models.TransferModel;
import com.javatest.moneytransfer.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/transfers")
public class TransferController {
    @Autowired
    private TransferService transferService;

    // Return a list with all accounts
    @GetMapping(path = "/alltransfers")
    public ArrayList<TransferModel> getAllTransfers(){return this.transferService.getTransfers();
    }

    // Return a transfer searched by id
    @GetMapping(path = "/transfer{id}")
    public Optional<TransferModel> getTransfersById(@PathVariable Long id){
        return this.transferService.getById(id);
    }

    // Make a transfer
    @PostMapping(path = "/maketransfer")
    public Optional<TransferModel> makeTransfer(@Valid @RequestBody TransferModel transfer){
        return this.transferService.makeTransfer(transfer);
    }

}
