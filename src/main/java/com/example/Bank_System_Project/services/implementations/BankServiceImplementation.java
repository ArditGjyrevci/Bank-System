package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BankServiceImplementation implements BankService {
    private BankRepository bankRepository;
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
    public Bank findById(Integer id) {
        Optional<Bank> result = bankRepository.findById(id);
        Bank bank=null;
        if(result.isPresent()){
            bank = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+id);
        }
        return bank;
    }

    @Override
    public List<Bank> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        bankRepository.deleteById(id);
    }
}
