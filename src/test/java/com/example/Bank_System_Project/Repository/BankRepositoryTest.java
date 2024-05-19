package com.example.Bank_System_Project.Repository;

import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Bank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestPropertySource("classpath:application-test.properties")
public class BankRepositoryTest{
    @Autowired
    private BankRepository bankRepository;
    @Test
    public void BankRepository_SaveAll_ReturnSavedBank() {

        Bank bank =  Bank.builder().bankName("KosovoBank").
                totalTransactionFeeAmount(BigDecimal.ZERO).
                totalTransferAmount(BigDecimal.ZERO).
                transactionFlatFeeAmount(BigDecimal.valueOf(5)).
                transactionPercentFeeValue(BigDecimal.valueOf(5)).build();

        Bank savedBank = bankRepository.save(bank);

        Assertions.assertThat(savedBank).isNotNull();
        Assertions.assertThat(savedBank.getBankId()).isGreaterThan(0);
    }

}