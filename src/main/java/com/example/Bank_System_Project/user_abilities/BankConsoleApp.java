package com.example.Bank_System_Project.user_abilities;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;
import com.example.Bank_System_Project.services.interfaces.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class BankConsoleApp {
    private final BankService bankService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    public BankConsoleApp(BankService bankService, AccountService accountService, TransactionService transactionService) {
        this.bankService = bankService;
        this.accountService=accountService;
        this.transactionService=transactionService;
    }

    public void start(){
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
            System.out.println("10.Check Bank Total Transfer Amount");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    createBank(scanner);
                    break;
                case 2:
                    createAccount(scanner);
                    break;
                case 3:
                    performTransaction(scanner);
                    break;
                case 4:
                    withdrawMoney(scanner);
                    break;
                case 5:
                    depositMoney(scanner);
                    break;
                case 6:
                    listTransactions(scanner);
                    break;
                case 7:
                    checkAccountBalance(scanner);
                    break;
                case 8:
                    listAllAccounts();
                    break;
                case 9:
                    checkBankTotalTransactionFeeAmount(scanner);
                    break;
                case 10:
                    checkBankTotalTransferAmount(scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
        System.out.println("Thank you for using the Bank Console App.");

    }

    private void createBank(Scanner scanner) {
        System.out.print("Enter bank name: ");
        String bankName = scanner.nextLine();

        System.out.print("Enter transaction flat fee amount: ");
        BigDecimal flatFee = scanner.nextBigDecimal();

        System.out.print("Enter transaction percent fee value: ");
        BigDecimal percentFee = scanner.nextBigDecimal();

        Bank bank = new Bank(bankName, flatFee, percentFee);
        bankService.save(bank);
        System.out.println("Bank created successfully.");
    }

    private boolean showBanks(Scanner scanner) {
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
    private boolean selectBank(Scanner scanner){
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

    private void createAccount(Scanner scanner) {
        showBanks(scanner);
        selectBank(scanner);
        Bank currentBank = bankService.getCurrentBank();
        if (currentBank == null) {
            System.out.println("Error: No bank selected. Please select or create a bank first.");
            return;
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        BigDecimal initialBalance = scanner.nextBigDecimal();
        Account account = new Account(username, initialBalance);
        account.setBank(currentBank);
        accountService.save(account);
        System.out.println("Account created successfully!");
    }
    private void performTransaction(Scanner scanner) {
        try {
            System.out.print("Enter originating account ID: ");
            int originatingAccountId = scanner.nextInt();
            System.out.print("Enter resulting account ID: ");
            int resultingAccountId = scanner.nextInt();
            System.out.print("Enter amount: ");
            BigDecimal amount = scanner.nextBigDecimal();
            scanner.nextLine(); // Consume newline
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

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void withdrawMoney(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            System.out.print("Enter amount: ");
            BigDecimal amount = scanner.nextBigDecimal();
            System.out.print("Is this a flat fee withdrawal? (true/false): ");
            boolean isFlatFee = scanner.nextBoolean();
            accountService.withdraw(accountId, amount, isFlatFee);
            System.out.println("Withdrawal completed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void depositMoney(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            System.out.print("Enter amount you want to deposit: ");
            BigDecimal amount = scanner.nextBigDecimal();
            System.out.print("Is this a flat fee deposit? (true/false): ");
            boolean isFlatFee = scanner.nextBoolean();
            accountService.deposit(accountId, amount, isFlatFee);
            System.out.println("Deposit completed successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void listTransactions(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            transactionService.findByAccountId(accountId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void checkAccountBalance(Scanner scanner) {
        try {
            System.out.print("Enter account ID: ");
            int accountId = scanner.nextInt();
            Account account = accountService.findById(accountId);
            System.out.println("Account balance: " + account.getAccountBalance());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private void listAllAccounts() {
        List<Account> accounts = accountService.findAll();
        accounts.forEach(System.out::println);
    }
    private void checkBankTotalTransactionFeeAmount(Scanner scanner) {
        showBanks(scanner);
        try {
            System.out.print("Enter bank ID: ");
            int accountId = scanner.nextInt();
            Bank bank = bankService.getBankById(accountId);
            System.out.println("Total Transaction Fee Amount: " + bank.getTotalTransactionFeeAmount());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private  void checkBankTotalTransferAmount(Scanner scanner) {
        showBanks(scanner);
        try {
            System.out.print("Enter bank ID: ");
            int accountId = scanner.nextInt();
            Bank bank = bankService.getBankById(accountId);
            System.out.println("Total Transfer Amount: " + bank.getTotalTransactionFeeAmount());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
