package com.example.Bank_System_Project;

import com.example.Bank_System_Project.user_abilities.BankConsoleApp;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;
import com.example.Bank_System_Project.services.interfaces.TransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.Bank_System_Project")
public class BankSystemProjectApplication implements CommandLineRunner {

	private final BankService bankService;
	private final AccountService accountService;
	private final TransactionService transactionService;

	public BankSystemProjectApplication(BankService bankService, AccountService accountService, TransactionService transactionService) {
		this.bankService = bankService;
		this.accountService = accountService;
		this.transactionService = transactionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankSystemProjectApplication.class, args);
	}

	@Override
	public void run(String... args) {
		BankConsoleApp consoleApp = new BankConsoleApp(bankService, accountService, transactionService);
		consoleApp.start();
	}
}