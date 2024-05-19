package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckBankTotalTransferAmountOperation extends BankOperation {
    private final BankService bankService;

    public CheckBankTotalTransferAmountOperation(BankService bankService) {
        this.bankService = bankService;
    }
    @Override
    public void execute(Scanner scanner) {
        AuxiliaryFunctions auxiliaryFunctions=new AuxiliaryFunctions(bankService);
        auxiliaryFunctions.showBanks(scanner);
        try {
            System.out.print("Enter bank ID: ");
            int accountId = scanner.nextInt();
            Bank bank = bankService.getBankById(accountId);
            System.out.println("Total Transfer Amount: " + bank.getTotalTransferAmount());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
