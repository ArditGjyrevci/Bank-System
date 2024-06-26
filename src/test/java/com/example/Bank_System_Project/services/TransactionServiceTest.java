package com.example.Bank_System_Project.services;

import com.example.Bank_System_Project.daos.TransactionRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.implementations.TransactionServiceImplementation;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private BankService bankService;

    @InjectMocks
    private TransactionServiceImplementation transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Transaction transaction = new Transaction();
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction savedTransaction = transactionService.save(transaction);

        assertNotNull(savedTransaction);
        assertEquals(transaction, savedTransaction);
        verify(transactionRepository, times(1)).save(transaction);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Transaction transaction = new Transaction();
        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        Transaction foundTransaction = transactionService.findById(id);

        assertNotNull(foundTransaction);
        assertEquals(transaction, foundTransaction);
        verify(transactionRepository, times(1)).findById(id);
    }

    @Test
    public void testFindById_NotFound() {
        Integer id = 1;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> transactionService.findById(id));

        verify(transactionRepository, times(1)).findById(id);
    }
    @Test
    public void testFindAll() {
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction());
        transactionList.add(new Transaction());
        when(transactionRepository.findAll()).thenReturn(transactionList);

        List<Transaction> foundTransactions = transactionService.findAll();

        assertNotNull(foundTransactions);
        assertEquals(2, foundTransactions.size());
        verify(transactionRepository, times(1)).findAll();
    }

    @Test
    void testFindByAccountId() {
        Integer accountId = 1;
        Account account = new Account();
        account.setAccountId(accountId);

        Transaction transaction = new Transaction();
        Account originatingAccount = new Account();
        originatingAccount.setAccountId(accountId);
        Account resultingAccount = new Account();
        resultingAccount.setAccountId(accountId);
        transaction.setOriginatingAccount(originatingAccount);
        transaction.setResultingAccount(resultingAccount);

        when(accountService.findById(accountId)).thenReturn(account);
        when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        List<Transaction> foundTransactions = transactionService.findByAccountId(accountId);

        assertNotNull(foundTransactions);
        assertEquals(1, foundTransactions.size());
        assertEquals(transaction, foundTransactions.get(0));
    }



    @Test
    public void testFindByAccountId_InvalidAccount() {
        Integer accountId = 1;
        when(accountService.findById(accountId)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> transactionService.findByAccountId(accountId));

        verify(accountService, times(1)).findById(accountId);
        verify(transactionRepository, never()).findAll();
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        transactionService.deleteById(id);

        verify(transactionRepository, times(1)).deleteById(id);
    }
}
