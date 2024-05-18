package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.TransactionRepository;
import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class TransactionServiceImplementation implements TransactionService {
    private TransactionRepository transactionRepository;
    @Autowired
    public TransactionServiceImplementation (TransactionRepository transactionRepository)
    {
        this.transactionRepository=transactionRepository;
    }
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(Integer id) {
        Optional<Transaction> result = transactionRepository.findById(id);
        Transaction transaction=null;
        if(result.isPresent()){
            transaction = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+id);
        }
        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
