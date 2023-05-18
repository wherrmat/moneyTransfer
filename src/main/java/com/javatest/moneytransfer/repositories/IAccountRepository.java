package com.javatest.moneytransfer.repositories;

import com.javatest.moneytransfer.models.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<AccountModel, Long> {
}
