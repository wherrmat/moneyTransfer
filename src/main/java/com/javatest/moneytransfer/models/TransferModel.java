package com.javatest.moneytransfer.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name="transfers")
public class TransferModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name="source_account_id")
    @JsonProperty("source_account_id")
    private Long sourceAccountId;

    @NotNull
    @Column(name="destination_account_id")
    @JsonProperty("destination_account_id")
    private Long destinationAccountId;
    @DecimalMin("0.00")
    @NotNull
    @Column(name="amount")
    private BigDecimal amount;


}
