package com.example.Bank_System_Project.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal totalTransactionFeeAmount=BigDecimal.ZERO;

    @Column(name = "total_transfer_amount")
    private BigDecimal totalTransferAmount=BigDecimal.ZERO;

    @Column(name = "transaction_flat_fee_amount")
    private Double transactionFlatFeeAmount;

    @Column(name = "transaction_percent_fee_value")
    private Double transactionPercentFeeValue;
    @OneToMany(mappedBy = "bank")
    @JsonManagedReference
    private List<Account> accounts;

    public Bank() {
    }

    public Bank(String bankName, Double transactionFlatFeeAmount, Double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
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

    public BigDecimal getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public void setTotalTransactionFeeAmount(BigDecimal totalTransactionFeeAmount) {
        this.totalTransactionFeeAmount = totalTransactionFeeAmount;
    }

    public BigDecimal getTotalTransferAmount() {
        return totalTransferAmount;
    }

    public void setTotalTransferAmount(BigDecimal totalTransferAmount) {
        this.totalTransferAmount = totalTransferAmount;
    }

    public Double getTransactionFlatFeeAmount() {
        return transactionFlatFeeAmount;
    }

    public void setTransactionFlatFeeAmount(Double transactionFlatFeeAmount) {
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
    }

    public Double getTransactionPercentFeeValue() {
        return transactionPercentFeeValue;
    }

    public void setTransactionPercentFeeValue(Double transactionPercentFeeValue) {
        this.transactionPercentFeeValue = transactionPercentFeeValue;
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

    @Override
    public String toString() {
        return "Bank{" +
                "bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", totalTransactionFeeAmount=" + totalTransactionFeeAmount +
                ", totalTransferAmount=" + totalTransferAmount +
                ", TransactionFlatFeeAmount=" + transactionFlatFeeAmount +
                ", TransactionPercentFeeValue=" + transactionPercentFeeValue +
                '}';
    }
}
