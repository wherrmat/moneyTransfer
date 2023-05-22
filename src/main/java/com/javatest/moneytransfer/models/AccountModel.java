package com.javatest.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
@Entity
@Table(name="accounts")
public class AccountModel {
    // Define the attributes of the account class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name="account_number")
    @JsonProperty("account_number")
    private String accountNumber;
    @DecimalMin("0.00")
    @Column(name="balance")
    private BigDecimal balance = BigDecimal.valueOf(0.00);

    // Define the methods for get and set the attributes
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getAccountNumber(){
        return this.accountNumber;
    }
    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
}
