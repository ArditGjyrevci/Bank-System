package com.example.Bank_System_Project.services.interfaces;


import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {
    Account save(Account account);
    Account findById(Integer id);
    List<Account> findAll();
    void deleteById(Integer id);
    public Account getCurrentAccount();
    public void withdraw(int accountId, BigDecimal amount, boolean isFlatFee);
    public void deposit(int accountId, BigDecimal amount, boolean isFlatFee);

}

