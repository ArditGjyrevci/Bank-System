package com.example.Bank_System_Project.daos;

import com.example.Bank_System_Project.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}