package com.example.Bank_System_Project.Repository;

import com.example.Bank_System_Project.daos.AccountRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestPropertySource("classpath:application-test.properties")
public class AccountRepositoryTest{
    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void AccountRepository_SaveAll_ReturnSavedAccount() {

        Bank bank = new Bank("KB", BigDecimal.valueOf(10), BigDecimal.valueOf(0));
        Account account = Account.builder().username("A").accountBalance(BigDecimal.valueOf(100)).bank(bank).build();
        Account savedAccount = accountRepository.save(account);

        Assertions.assertThat(savedAccount).isNotNull();
        Assertions.assertThat(savedAccount.getAccountId()).isGreaterThan(0);
    }

}

