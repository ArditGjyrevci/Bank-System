package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.services.interfaces.AccountService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ListAllAccountsOperation extends BankOperation{
    private final AccountService accountService;

    public ListAllAccountsOperation(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void execute(Scanner scanner) {
        try{List<Account> accounts = accountService.findAll();
        accounts.forEach(System.out::println);
        } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        }
    }
}
