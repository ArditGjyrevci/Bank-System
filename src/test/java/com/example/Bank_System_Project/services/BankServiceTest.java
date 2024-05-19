package com.example.Bank_System_Project.services;

import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.implementations.BankServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BankServiceTest{
    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankServiceImplementation bankService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        Bank bank = new Bank();
        when(bankRepository.save(bank)).thenReturn(bank);

        Bank savedBank = bankService.save(bank);

        assertNotNull(savedBank);
        assertEquals(bank, savedBank);
        verify(bankRepository, times(1)).save(bank);
    }

    @Test
    public void testSaveTransactionAmounts() {
        int bankId = 1;
        BigDecimal transactionFee = BigDecimal.TEN;
        BigDecimal transferAmount = BigDecimal.valueOf(100);

        bankService.save(bankId, transactionFee, transferAmount);

        verify(bankRepository, times(1)).updateTransactionAmounts(bankId, transactionFee, transferAmount);
    }

    @Test
    public void testFindById() {
        Integer id = 1;
        Bank bank = new Bank();
        when(bankRepository.findById(id)).thenReturn(Optional.of(bank));

        Optional<Bank> foundBank = bankService.findById(id);

        assertTrue(foundBank.isPresent());
        assertEquals(bank, foundBank.get());
        verify(bankRepository, times(1)).findById(id);
    }

    @Test
    public void testFindById_NotFound() {
        Integer id = 1;
        when(bankRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bankService.getBankById(id));

        verify(bankRepository, times(1)).findById(id);
    }

    @Test
    public void testGetBankById() {
        Integer id = 1;
        Bank bank = new Bank();
        when(bankRepository.findById(id)).thenReturn(Optional.of(bank));

        Bank foundBank = bankService.getBankById(id);

        assertNotNull(foundBank);
        assertEquals(bank, foundBank);
        verify(bankRepository, times(1)).findById(id);
    }

    @Test
    public void testGetBankById_NotFound() {
        Integer id = 1;
        when(bankRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> bankService.getBankById(id));

        verify(bankRepository, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        Bank bank1 = new Bank();
        Bank bank2 = new Bank();
        when(bankRepository.findAll()).thenReturn(Arrays.asList(bank1, bank2));

        List<Bank> banks = bankService.findAll();

        assertNotNull(banks);
        assertEquals(2, banks.size());
        assertTrue(banks.contains(bank1));
        assertTrue(banks.contains(bank2));
        verify(bankRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        Integer id = 1;

        bankService.deleteById(id);

        verify(bankRepository, times(1)).deleteById(id);
    }

    @Test
    public void testSetCurrentBank() {
        Bank bank = new Bank();

        bankService.setCurrentBank(bank);

        assertEquals(bank, bankService.getCurrentBank());
    }
}
