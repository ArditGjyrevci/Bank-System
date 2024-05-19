package com.example.Bank_System_Project.daos;

import com.example.Bank_System_Project.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}