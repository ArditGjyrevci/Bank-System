package com.example.Bank_System_Project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;
    @Column(name = "amount")
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "originating_account_id")
    @JsonBackReference
    private Account originatingAccount;
    @ManyToOne
    @JoinColumn(name = "resulting_account_id")
    @JsonBackReference
    private Account resultingAccount;
    @Column(name = "transaction_reason")
    private String transactionReason;
    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    public Transaction() {
    }

    public Transaction(BigDecimal amount, String transactionReason, LocalDateTime transactionDate) {
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
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

    public String getTransactionReason() {
        return transactionReason;
    }

    public void setTransactionReason(String transactionReason) {
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
                ", originatingAccount=" + originatingAccount +
                ", resultingAccount=" + resultingAccount +
                ", transactionReason='" + transactionReason + '\'' +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
