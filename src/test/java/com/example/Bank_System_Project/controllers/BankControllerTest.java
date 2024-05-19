package com.example.Bank_System_Project.controllers;

import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.BankService;
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

class BankControllerTest {

    @Mock
    private BankService bankService;

    @InjectMocks
    private BankController bankController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllBanks() {
        // Mock data
        Bank bank1 = new Bank("Bank A", BigDecimal.valueOf(10),BigDecimal.valueOf(5));
        Bank bank2 = new Bank("Bank A", BigDecimal.valueOf(10),BigDecimal.valueOf(5));
        List<Bank> banks = Arrays.asList(bank1, bank2);

        // Mock service method
        when(bankService.findAll()).thenReturn(banks);

        // Call controller method
        ResponseEntity<List<Bank>> responseEntity = bankController.getAllBanks();

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(banks, responseEntity.getBody());
    }

    @Test
    void getBankById() {
        // Mock data
        Bank bank = new Bank("Bank A", BigDecimal.valueOf(10),BigDecimal.valueOf(5));

        // Mock service method
        when(bankService.getBankById(1)).thenReturn(bank);

        // Call controller method
        ResponseEntity<Bank> responseEntity = bankController.getBankById(1);

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(bank, responseEntity.getBody());
    }

    @Test
    void createBank() {
        // Mock data
        Bank bank = new Bank("Bank A", BigDecimal.valueOf(10),BigDecimal.valueOf(5));

        // Mock service method
        when(bankService.save(bank)).thenReturn(bank);

        // Call controller method
        ResponseEntity<Bank> responseEntity = bankController.createBank(bank);

        // Verify
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(bank, responseEntity.getBody());
    }

    @Test
    void deleteBank() {
        // Call controller method
        ResponseEntity<Void> responseEntity = bankController.deleteBank(1);

        // Verify
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(bankService, times(1)).deleteById(1);
    }

    @Test
    void updateTransactionAmounts() {
        // Call controller method
        ResponseEntity<Void> responseEntity = bankController.updateTransactionAmounts(1, BigDecimal.valueOf(10), BigDecimal.valueOf(100));

        // Verify
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(bankService, times(1)).save(1, BigDecimal.valueOf(10), BigDecimal.valueOf(100));
    }
}

