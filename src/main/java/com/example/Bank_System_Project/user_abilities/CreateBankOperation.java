package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;
import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateBankOperation extends BankOperation {
    private final BankService bankService;

    public CreateBankOperation(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public void execute(Scanner scanner) {
        try {
            System.out.print("Enter bank name: ");
            String bankName = scanner.nextLine();

            System.out.print("Enter transaction flat fee amount: ");
            BigDecimal flatFee = scanner.nextBigDecimal();

            System.out.print("Enter transaction percent fee value: ");
            BigDecimal percentFee = scanner.nextBigDecimal();

            Bank bank = new Bank(bankName, flatFee, percentFee);
            bankService.save(bank);
            System.out.println("Bank created successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
