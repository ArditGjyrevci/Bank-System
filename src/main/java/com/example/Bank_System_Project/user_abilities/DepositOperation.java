package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.services.interfaces.AccountService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DepositOperation extends BankOperation{
    private final AccountService accountService;
    public DepositOperation(AccountService accountService) {
        this.accountService = accountService;
    }
    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            System.out.print("Enter amount you want to deposit: ");
            BigDecimal amount = scanner.nextBigDecimal();
            System.out.print("Is this a flat fee deposit? (true/false): ");
            boolean isFlatFee = scanner.nextBoolean();
            accountService.deposit(accountId, amount, isFlatFee);
            System.out.println("Deposit completed successfully.");
        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
