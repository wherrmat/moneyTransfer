package com.javatest.moneytransfer.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

import java.math.BigDecimal;
@Entity
@Table(name="account")
public class AccountModel {
    // Define the attributes of the account class
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="balance")
    private BigDecimal balance;

    // Define the methods for get and set the attributes
    public Long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public BigDecimal getBalance(){
        return this.balance;
    }
    public void setBalance(BigDecimal balance){
        this.balance = balance;
    }
    public void addBalance(BigDecimal amount){
        this.balance.add(amount);
    }
}
