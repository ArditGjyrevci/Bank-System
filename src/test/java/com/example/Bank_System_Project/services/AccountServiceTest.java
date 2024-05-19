package com.example.Bank_System_Project.services;

import com.example.Bank_System_Project.daos.AccountRepository;
import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.implementations.AccountServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private AccountServiceImplementation accountService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Account account = new Account();
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account savedAccount = accountService.save(account);

        assertNotNull(savedAccount);
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testFindById() {
        Account account = new Account();
        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));

        Account foundAccount = accountService.findById(1);

        assertNotNull(foundAccount);
        assertEquals(account, foundAccount);
    }

    @Test
    void testFindAll() {
        List<Account> accounts = Arrays.asList(new Account(), new Account());
        when(accountRepository.findAll()).thenReturn(accounts);

        List<Account> foundAccounts = accountService.findAll();

        assertEquals(2, foundAccounts.size());
    }

    @Test
    void testDeleteById() {
        doNothing().when(accountRepository).deleteById(anyInt());

        accountService.deleteById(1);

        verify(accountRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetCurrentAccount() {
        Account currentAccount = new Account();
        accountService.setCurrentAccount(currentAccount);

        assertEquals(currentAccount, accountService.getCurrentAccount());
    }

    @Test
    void testWithdraw() {
        Account account = new Account();
        account.setAccountBalance(new BigDecimal("1000"));
        Bank bank = new Bank();
        bank.setBankId(1);
        bank.setTransactionFlatFeeAmount(new BigDecimal("10"));

        account.setBank(bank);

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));
        doNothing().when(bankRepository).updateTransactionAmounts(anyInt(), any(BigDecimal.class), any(BigDecimal.class));

        accountService.withdraw(1, new BigDecimal("100"), true);

        // Verify the account balance and repository interactions
        assertEquals(new BigDecimal("890"), account.getAccountBalance());
        verify(accountRepository, times(1)).save(account);
    }

    @Test
    void testDeposit() {

        Account account = new Account();
        account.setAccountBalance(new BigDecimal("1000"));

        Bank bank = new Bank();
        bank.setBankId(1);
        bank.setTransactionFlatFeeAmount(new BigDecimal("10"));

        account.setBank(bank);

        when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));
        doNothing().when(bankRepository).updateTransactionAmounts(anyInt(), any(BigDecimal.class), any(BigDecimal.class));

        accountService.deposit(1, new BigDecimal("100"), true);

        assertEquals(new BigDecimal("1090"), account.getAccountBalance());
        verify(accountRepository, times(1)).save(account);
    }
}
