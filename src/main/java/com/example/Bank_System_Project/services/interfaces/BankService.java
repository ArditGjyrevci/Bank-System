package com.example.Bank_System_Project.services.interfaces;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;

import java.util.List;
import java.util.Optional;

public interface BankService {
    Bank save(Bank bank);
    public Optional<Bank> findById(Integer id);
    List<Bank> findAll();
    void deleteById(Integer id);
    public void setCurrentBank(Bank bank);
    public Bank getCurrentBank();

}