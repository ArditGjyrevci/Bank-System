package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateAccountOperation extends BankOperation {
    private final BankService bankService;
    private final AccountService accountService;

    public CreateAccountOperation(BankService bankService, AccountService accountService) {
        this.bankService = bankService;
        this.accountService = accountService;
    }
    @Override
    public void execute(Scanner scanner) {
        AuxiliaryFunctions auxiliaryFunctions=new AuxiliaryFunctions(bankService);
        auxiliaryFunctions.showBanks(scanner);
        auxiliaryFunctions.selectBank(scanner);
        Bank currentBank = bankService.getCurrentBank();
        if (currentBank == null) {
            System.out.println("Error: No bank selected. Please select or create a bank first.");
            return;
        }
        try{System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        BigDecimal initialBalance = scanner.nextBigDecimal();
        Account account = new Account(username, initialBalance);
        account.setBank(currentBank);
        accountService.save(account);
        System.out.println("Account created successfully!");
        }catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
