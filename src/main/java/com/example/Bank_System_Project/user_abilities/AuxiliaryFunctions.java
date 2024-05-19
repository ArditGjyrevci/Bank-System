package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AuxiliaryFunctions {
    private final BankService bankService;

    public AuxiliaryFunctions(BankService bankService) {
        this.bankService = bankService;
    }

    public boolean showBanks(Scanner scanner) {
        List<Bank> banks = bankService.findAll();
        if (banks.isEmpty()) {
            System.out.println("No banks found. Please create a new bank.");
            return false;
        }

        System.out.println("Select a bank from the list:");
        for (Bank bank : banks) {
            System.out.println("ID: "+ bank.getBankId() + " Name: " + bank.getBankName());
        }
        return true;
    }
    public boolean selectBank(Scanner scanner){
        System.out.print("Enter bank ID: ");
        int bankId = scanner.nextInt();
        scanner.nextLine();

        Optional<Bank> bankOptional = bankService.findById(bankId);
        if (bankOptional.isPresent()) {
            bankService.setCurrentBank(bankOptional.get());
            System.out.println("Selected bank: " + bankOptional.get().getBankName());
            return true;
        } else {
            System.out.println("Invalid bank ID. Please try again.");
            return false;
        }
    }
}
