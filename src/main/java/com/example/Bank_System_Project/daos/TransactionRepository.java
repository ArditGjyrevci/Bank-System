package com.example.Bank_System_Project.daos;

import com.example.Bank_System_Project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
