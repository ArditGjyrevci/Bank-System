package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankServiceImplementation implements BankService {
    private BankRepository bankRepository;
    private Bank currentBank;
    @Autowired
    public BankServiceImplementation (BankRepository bankRepository)
    {
        this.bankRepository=bankRepository;
    }
    @Override
    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    @Override
    public  Optional<Bank> findById(Integer id) {
        Optional<Bank> result = bankRepository.findById(id);
        return result;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bankRepository.deleteById(id);
    }
    @Override
    public void setCurrentBank(Bank bank) {
        this.currentBank = bank;
    }
    @Override
    public Bank getCurrentBank() {
        return currentBank;
    }
}
