package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CheckBankTotalTransactionFeeAmountOperation extends BankOperation{
    private final BankService bankService;

    public CheckBankTotalTransactionFeeAmountOperation(BankService bankService) {
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
            System.out.println("Total Transaction Fee Amount: " + bank.getTotalTransactionFeeAmount());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid value.");
            scanner.nextLine();
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
