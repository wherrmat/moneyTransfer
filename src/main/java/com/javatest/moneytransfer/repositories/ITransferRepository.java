package com.javatest.moneytransfer.repositories;

import com.javatest.moneytransfer.models.TransferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransferRepository extends JpaRepository<TransferModel, Long> {
}
