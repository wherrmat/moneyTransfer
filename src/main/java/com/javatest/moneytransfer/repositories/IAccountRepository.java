package com.javatest.moneytransfer.repositories;

import com.javatest.moneytransfer.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface IAccountRepository extends JpaRepository<AccountModel, Long> {

    // Check if any account already exists using the account_number
    boolean existsByAccountNumber(String account_number);

    // Return an account using searched by the account_number
    AccountModel findByAccountNumber(String account_number);
}
