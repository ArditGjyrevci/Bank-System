CREATE TABLE IF NOT EXISTS banks(
    bank_id INT AUTO_INCREMENT PRIMARY KEY ,
    bank_name TEXT NOT NULL,
    total_transaction_fee_amount DECIMAL(10, 2) DEFAULT 0,
    total_transfer_amount DECIMAL(10, 2) DEFAULT 0,
    transaction_flat_fee_amount DECIMAL(10, 2),
    transaction_percent_fee_value DECIMAL(5, 2)
);
