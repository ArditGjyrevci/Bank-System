# Bank System Console Application

## Overview

This is a console application for a bank system that manages multiple user accounts and performs various transactions. The application is built using Spring Boot, MySQL database, Maven for dependencies and Liquibase for database migration. It includes unit tests to ensure functionality and adheres to Object-Oriented Programming principles.

## Features

- Creation of banks with configurable transaction fees
- Account management (creation, withdrawal, deposit)
- Transaction handling (flat fee and percent fee)
- Viewing account transactions and balances
- Listing bank accounts
- Tracking total transaction fees and transfer amounts
- Checking total bank fee amounts and transfer amounts

## Technologies Used

- Java 11
- Spring Boot
- MySQL
- Liquibase
- JUnit 5 for unit testing

## Installation

1. Clone the repository from GitHub: git clone https://github.com/your-username/bank-system.git
2. Navigate to the project directory
3. 3. Configure the MySQL database settings in `application.properties`.
4. Build the application using Maven
5. Run the application

## Usage

1. **Creating a Bank**:
- Provide the bank name and configure transaction fees during creation.

2. **Creating an Account**:
- Enter the account holder's name to create a new account  bank id in which you want to register this user and initial account balance.

3. **Performing Transactions**:
- Choose between flat fee or percent fee transactions.
- Specify the amount, originating account ID, resulting account ID, and transaction reason.

4. **Withdrawing and Depositing**:
- Select the account and specify the amount to withdraw or deposit, also choose between flat fee or percent fee.

5. **Viewing Transactions and Balances**:
- Check the list of transactions for any account.
- See the current balance of any account.

6. **Listing Bank Accounts**:
- View all accounts associated with the bank.

7. **Checking Bank Metrics**:
- Check the total transaction fee amount and total transfer amount for the bank.

## API Endpoints (Optional)

For a RESTful version of this application, you can expose API endpoints using Spring Web. Example endpoints might include:

- `/api/banks` - Supports HTTP methods for banks.
- `/api/accounts` - Supports HTTP methods for accounts.
- `/api/transactions` - Supports HTTP methods for transactions.

## Contributors
- Ardit Gjyrevci
