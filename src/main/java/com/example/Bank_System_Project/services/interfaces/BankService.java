package com.example.Bank_System_Project.services.interfaces;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;

import java.util.List;

public interface BankService {
    Bank save(Bank bank);
    Bank findById(Integer id);
    List<Bank> findAll();
    void deleteById(Integer id);

}