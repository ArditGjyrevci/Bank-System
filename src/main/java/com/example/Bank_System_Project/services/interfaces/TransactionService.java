package com.example.Bank_System_Project.services.interfaces;

import com.example.Bank_System_Project.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Transaction findById(Integer id);
    List<Transaction> findAll();
    List<Transaction>findByAccountId(Integer id);
    void deleteById(Integer id);
    public void performTransaction(Transaction transaction, boolean isFlatFee) throws Exception;



}