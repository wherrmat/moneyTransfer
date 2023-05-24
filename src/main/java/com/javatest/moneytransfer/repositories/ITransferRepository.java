package com.javatest.moneytransfer.repositories;

import com.javatest.moneytransfer.models.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ITransferRepository extends JpaRepository<TransferModel, Long> {
    boolean existsBySourceAccountIdOrDestinationAccountId(Long sourceAccountId, Long destinationAccountId);

    ArrayList<TransferModel> findBySourceAccountIdOrDestinationAccountId(Long sourceAccountId, Long destinationAccountId);
}
