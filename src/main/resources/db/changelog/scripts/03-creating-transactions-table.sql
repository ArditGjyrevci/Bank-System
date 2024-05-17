CREATE TABLE IF NOT EXISTS transactions(
    transaction_id INT AUTO_INCREMENT PRIMARY KEY ,
    amount DECIMAL(10, 2) NOT NULL CHECK(amount > 0),
    originating_account_id INT,
    resulting_account_id INT,
    transaction_reason TEXT,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (originating_account_id) REFERENCES accounts(account_id),
    FOREIGN KEY (resulting_account_id) REFERENCES accounts(account_id)
);