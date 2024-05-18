package com.example.Bank_System_Project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Column(name = "amount")
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    @JsonBackReference
    private Account originatingAccount;
    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    @JsonBackReference
    private Account resultingAccount;
    @Column(name = "transaction_reason")
    private Integer transactionReason;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(Double amount, Integer transactionReason, LocalDateTime transactionDate) {
        this.amount = amount;
        this.transactionReason = transactionReason;
        this.transactionDate = transactionDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Account getOriginatingAccount() {
        return originatingAccount;
    }

    public void setOriginatingAccount(Account originatingAccount) {
        this.originatingAccount = originatingAccount;
    }

    public Account getResultingAccount() {
        return resultingAccount;
    }

    public void setResultingAccount(Account resultingAccount) {
        this.resultingAccount = resultingAccount;
    }

    public Integer getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(Integer transactionReason) {
        this.transactionReason = transactionReason;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", amount=" + amount +
                ", transactionReason=" + transactionReason +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
