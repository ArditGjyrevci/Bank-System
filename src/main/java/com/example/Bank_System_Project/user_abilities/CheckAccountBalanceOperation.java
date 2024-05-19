package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.services.interfaces.AccountService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckAccountBalanceOperation extends BankOperation{
    private final AccountService accountService;

    public CheckAccountBalanceOperation(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            Account account = accountService.findById(accountId);
            System.out.println("Account balance: " + account.getAccountBalance());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
