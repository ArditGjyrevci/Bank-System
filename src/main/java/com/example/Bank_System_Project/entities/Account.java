package com.example.Bank_System_Project.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Integer accountId;
    @Column(name = "user_name")
    private String username;
    @Column(name = "account_balance")
    private BigDecimal accountBalance;
    @ManyToOne
    @JoinColumn(name = "bank_id")
    @JsonBackReference
    private Bank bank;

    @OneToMany(mappedBy = "originatingAccount")
    @JsonManagedReference
    private List<Transaction> outgoingTransactions;

    @OneToMany(mappedBy = "resultingAccount")
    @JsonManagedReference
    private List<Transaction> incomingTransactions;

    public Account(){

    }

    public Account(String username, BigDecimal accountBalance) {
        this.username = username;
        this.accountBalance = accountBalance;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public List<Transaction> getOutgoingTransactions() {
        return outgoingTransactions;
    }

    public void setOutgoingTransactions(List<Transaction> outgoingTransactions) {
        this.outgoingTransactions = outgoingTransactions;
    }

    public List<Transaction> getIncomingTransactions() {
        return incomingTransactions;
    }

    public void setIncomingTransactions(List<Transaction> incomingTransactions) {
        this.incomingTransactions = incomingTransactions;
    }

    public void addOutgoingTransaction(Transaction tempOutgoingTransaction) {
        if (outgoingTransactions == null){
            outgoingTransactions = new ArrayList<>();
        }
        outgoingTransactions.add(tempOutgoingTransaction);
        tempOutgoingTransaction.setOriginatingAccount(this);
    }

    public void addIncomingTransaction(Transaction tempIncomingTransaction) {
        if (incomingTransactions == null){
            incomingTransactions = new ArrayList<>();
        }
        incomingTransactions.add(tempIncomingTransaction);
        tempIncomingTransaction.setResultingAccount(this);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
