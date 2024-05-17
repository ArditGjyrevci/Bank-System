CREATE TABLE IF NOT EXISTS accounts(
    account_id INT  AUTO_INCREMENT PRIMARY KEY,
    user_name TEXT NOT NULL,
    account_balance DECIMAL(10, 2) NOT NULL CHECK(account_balance >= 0),
    bank_id INT NOT NULL,
    FOREIGN KEY (bank_id) REFERENCES banks(bank_id)
);