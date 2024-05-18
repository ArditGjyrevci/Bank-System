package com.example.Bank_System_Project;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class BankSystemProjectApplication implements CommandLineRunner {

	private final BankService bankService;

	public BankSystemProjectApplication(BankService bankService) {
		this.bankService = bankService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankSystemProjectApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the Bank Console App");

		boolean bankSelected = false;

		while (!bankSelected) {
			System.out.println("\nOptions:");
			System.out.println("1. Create a new bank");
			System.out.println("2. Use an existing bank");
			System.out.print("Select an option: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // Consume newline

			switch (option) {
				case 1:
					createBank(scanner);
					bankSelected = true;
					break;
				case 2:
					if (selectExistingBank(scanner)) {
						bankSelected = true;
					}
					break;
				default:
					System.out.println("Invalid option. Please try again.");
			}
		}


	}

	private void createBank(Scanner scanner) {
		System.out.print("Enter bank name: ");
		String bankName = scanner.nextLine();

		System.out.print("Enter transaction flat fee amount: ");
		Double flatFee = scanner.nextDouble();

		System.out.print("Enter transaction percent fee value: ");
		Double percentFee = scanner.nextDouble();

		Bank bank = new Bank(bankName, flatFee, percentFee);
		bankService.save(bank);
		System.out.println("Bank created successfully.");
	}

	private boolean selectExistingBank(Scanner scanner) {
		List<Bank> banks = bankService.findAll();
		if (banks.isEmpty()) {
			System.out.println("No banks found. Please create a new bank.");
			return false;
		}

		System.out.println("Select a bank from the list:");
		for (Bank bank : banks) {
			System.out.println("ID: "+ bank.getBankId() + " Name: " + bank.getBankName());
		}

		System.out.print("Enter bank ID: ");
		int bankId = scanner.nextInt();
		scanner.nextLine(); // Consume newline

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