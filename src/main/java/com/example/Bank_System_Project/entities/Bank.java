package com.example.Bank_System_Project.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banks")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bank_id")
    private Integer bankId;
    @Column(name = "bank_name")
    private String bankName;
    @Column(name = "total_transaction_fee_amount")
    private Double totalTransactionFeeAmount;

    @Column(name = "total_transfer_amount")
    private Double totalTransferAmount;

    @Column(name = "transaction_flat_fee_amount")
    private Double TransactionFlatFeeAmount;

    @Column(name = "transaction_percent_fee_value")
    private Double TransactionPercentFeeValue;
    @OneToMany(mappedBy = "bank")
    @JsonManagedReference
    private List<Account> accounts;

    public Bank() {
    }

    public Bank(String bankName, Double totalTransactionFeeAmount, Double totalTransferAmount, Double transactionFlatFeeAmount, Double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
        this.totalTransferAmount = totalTransferAmount;
        TransactionFlatFeeAmount = transactionFlatFeeAmount;
        TransactionPercentFeeValue = transactionPercentFeeValue;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(Double totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public Double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(Double totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public Double getTransactionFlatFeeAmount() {
        return TransactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(Double transactionFlatFeeAmount) {
        TransactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public Double getTransactionPercentFeeValue() {
        return TransactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(Double transactionPercentFeeValue) {
        TransactionPercentFeeValue = transactionPercentFeeValue;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Account account) {
        if (accounts == null){
            accounts = new ArrayList<>();
        }
        accounts.add(account);
        account.setBank(this);
    }

}
