package com.example.Bank_System_Project.controllers;

import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllAccounts() {
        Account account1 = new Account("John Doe", BigDecimal.valueOf(1000));
        Account account2 = new Account("Jane Doe", BigDecimal.valueOf(500));
        List<Account> accounts = Arrays.asList(account1, account2);

        when(accountService.findAll()).thenReturn(accounts);

        ResponseEntity<List<Account>> responseEntity = accountController.getAllAccounts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accounts, responseEntity.getBody());
    }

    @Test
    void getAccountById() {
        Account account = new Account("John Doe", BigDecimal.valueOf(1000));

        when(accountService.findById(1)).thenReturn(account);

        ResponseEntity<Account> responseEntity = accountController.getAccountById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(account, responseEntity.getBody());
    }

    @Test
    void createAccount() {
        Account account = new Account("John Doe", BigDecimal.valueOf(1000));

        when(accountService.save(account)).thenReturn(account);

        ResponseEntity<Account> responseEntity = accountController.createAccount(account);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(account, responseEntity.getBody());
    }

    @Test
    void deleteAccount() {
        ResponseEntity<Void> responseEntity = accountController.deleteAccount(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(accountService, times(1)).deleteById(1);
    }

    @Test
    void withdraw() {
        ResponseEntity<Void> responseEntity = accountController.withdraw(1, BigDecimal.valueOf(100), false);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(accountService, times(1)).withdraw(1, BigDecimal.valueOf(100), false);
    }

    @Test
    void deposit() {
        ResponseEntity<Void> responseEntity = accountController.deposit(1, BigDecimal.valueOf(100), false);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(accountService, times(1)).deposit(1, BigDecimal.valueOf(100), false);
    }
}

