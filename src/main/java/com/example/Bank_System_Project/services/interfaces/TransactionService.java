package com.example.Bank_System_Project.services.interfaces;

import com.example.Bank_System_Project.entities.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(Transaction transaction);
    Transaction findById(Integer id);
    List<Transaction> findAll();
    void deleteById(Integer id);

}