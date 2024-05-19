package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PerformTransactionOperation extends BankOperation {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public PerformTransactionOperation(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter originating account ID: ");
            int originatingAccountId = scanner.nextInt();
            System.out.print("Enter resulting account ID: ");
            int resultingAccountId = scanner.nextInt();
            System.out.print("Enter amount: ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine();
            System.out.print("Enter transaction reason: ");
            String reason = scanner.nextLine();
            System.out.print("Is this a flat fee transaction? (true/false): ");
            boolean isFlatFee = scanner.nextBoolean();
            LocalDateTime transactionDate = LocalDateTime.now();
            Transaction transaction= new Transaction(amount,reason,transactionDate);
            transaction.setOriginatingAccount(accountService.findById(originatingAccountId));
            transaction.setResultingAccount(accountService.findById(resultingAccountId));
            transactionService.performTransaction(transaction, isFlatFee);
            transactionService.save(transaction);
            System.out.println("Transaction completed successfully.");

        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
