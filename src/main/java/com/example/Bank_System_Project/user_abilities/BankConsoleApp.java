package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;
import com.example.Bank_System_Project.services.interfaces.TransactionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankConsoleApp {
    private final BankService bankService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final Map<Integer, BankOperation> operations = new HashMap<>();

    public BankConsoleApp(BankService bankService, AccountService accountService, TransactionService transactionService) {
        this.bankService = bankService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        initializeOperations();
    }

    private void initializeOperations() {
        operations.put(1, new CreateBankOperation(bankService));
        operations.put(2, new CreateAccountOperation(bankService, accountService));
        operations.put(3, new PerformTransactionOperation(accountService, transactionService));
        operations.put(4, new WithdrawOperation(accountService));
        operations.put(5, new DepositOperation(accountService));
        operations.put(6, new ListTransactionsOperation(transactionService));
        operations.put(7, new CheckAccountBalanceOperation(accountService));
        operations.put(8, new ListAllAccountsOperation(accountService));
        operations.put(9, new CheckBankTotalTransactionFeeAmountOperation(bankService));
        operations.put(10, new CheckBankTotalTransferAmountOperation(bankService));
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Bank Console App");

        boolean exit = false;
        while (!exit) {
            System.out.println("\nOptions:");
            System.out.println("1. Create a new bank");
            System.out.println("2. Create an account");
            System.out.println("3. Perform transaction");
            System.out.println("4. Perform withdraw");
            System.out.println("5. Deposit money");
            System.out.println("6. List transactions by account id");
            System.out.println("7. Check Account Balance");
            System.out.println("8. List All Accounts");
            System.out.println("9. Check Bank Total Transaction Fee Amount ");
            System.out.println("10. Check Bank Total Transfer Amount");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 0) {
                exit = true;
            } else {
                BankOperation operation = operations.get(option);
                if (operation != null) {
                    operation.execute(scanner);
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            }
        }
        scanner.close();
        System.out.println("Thank you for using the Bank Console App.");
        System.exit(0);
    }
}
