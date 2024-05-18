package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.AccountRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService {
    private AccountRepository accountRepository;
    private Account currentAccount;
    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository)
    {
        this.accountRepository=accountRepository;
    }
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> result = accountRepository.findById(id);
        Account account=null;
        if(result.isPresent()){
            account = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+id);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getCurrentAccount() {
        return currentAccount;
    }

}
