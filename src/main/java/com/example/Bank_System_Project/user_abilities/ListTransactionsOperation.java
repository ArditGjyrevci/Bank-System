package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.services.interfaces.TransactionService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ListTransactionsOperation extends BankOperation{
    private final TransactionService transactionService;
    public ListTransactionsOperation(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            transactionService.findByAccountId(accountId);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
