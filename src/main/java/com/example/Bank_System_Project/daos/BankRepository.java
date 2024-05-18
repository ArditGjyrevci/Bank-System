package com.example.Bank_System_Project.daos;

import com.example.Bank_System_Project.entities.Bank;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface BankRepository extends JpaRepository<Bank, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Bank b SET b.totalTransactionFeeAmount = b.totalTransactionFeeAmount + :totalTransactionFeeAmount, b.totalTransferAmount = b.totalTransferAmount + :totalTransferAmount WHERE b.bankId = :bankId")
    void updateTransactionAmounts(@Param("bankId") int bankId, @Param("totalTransactionFeeAmount") BigDecimal transactionFee, @Param("totalTransferAmount") BigDecimal transferAmount);
}
