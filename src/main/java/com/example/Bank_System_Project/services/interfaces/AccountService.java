package com.example.Bank_System_Project.services.interfaces;


import com.example.Bank_System_Project.entities.Account;

import java.util.List;

public interface AccountService {
    Account save(Account account);
    Account findById(Integer id);
    List<Account> findAll();
    void deleteById(Integer id);

}
