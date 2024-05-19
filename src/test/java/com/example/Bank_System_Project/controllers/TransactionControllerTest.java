package com.example.Bank_System_Project.controllers;

import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.interfaces.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {
    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    LocalDateTime now = LocalDateTime.now();

    @Test
    void getAllTransactions() {
        Transaction transaction1 = new Transaction(BigDecimal.valueOf(100),"Gift", now);
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(100),"Gift", now);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.findAll()).thenReturn(transactions);

        ResponseEntity<List<Transaction>> responseEntity = transactionController.getAllTransactions();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }

    @Test
    void getTransactionById() {
        Transaction transaction = new Transaction(BigDecimal.valueOf(100),"Gift", now);

        when(transactionService.findById(1)).thenReturn(transaction);

        ResponseEntity<Transaction> responseEntity = transactionController.getTransactionById(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
    }

    @Test
    void createTransaction() {
        Transaction transaction = new Transaction(BigDecimal.valueOf(100),"Gift", now);

        when(transactionService.save(transaction)).thenReturn(transaction);

        ResponseEntity<Transaction> responseEntity = transactionController.createTransaction(transaction);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(transaction, responseEntity.getBody());
    }

    @Test
    void deleteTransaction() {
        ResponseEntity<Void> responseEntity = transactionController.deleteTransaction(1);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(transactionService, times(1)).deleteById(1);
    }

    @Test
    void performTransaction() throws Exception {
        Transaction transaction = new Transaction(BigDecimal.valueOf(100),"Gift", now);

        ResponseEntity<Void> responseEntity = transactionController.performTransaction(transaction, false);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(transactionService, times(1)).performTransaction(transaction, false);
    }

    @Test
    void getTransactionsByAccountId() {
        Transaction transaction1 = new Transaction(BigDecimal.valueOf(100),"Gift", now);
        Transaction transaction2 = new Transaction(BigDecimal.valueOf(100),"Gift", now);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionService.findByAccountId(1)).thenReturn(transactions);

        ResponseEntity<List<Transaction>> responseEntity = transactionController.getTransactionsByAccountId(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(transactions, responseEntity.getBody());
    }
}
