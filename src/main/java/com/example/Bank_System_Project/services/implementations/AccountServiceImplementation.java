package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.AccountRepository;
import com.example.Bank_System_Project.daos.BankRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImplementation implements AccountService {
    private AccountRepository accountRepository;
    private BankRepository bankRepository;
    private Account currentAccount;
    @Autowired
    public AccountServiceImplementation(AccountRepository accountRepository, BankRepository bankRepository)
    {
        this.accountRepository=accountRepository;
        this.bankRepository=bankRepository;
    }
    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account findById(Integer id) {
        Optional<Account> result = accountRepository.findById(id);
        Account account=null;
        if(result.isPresent()){
            account = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+id);
        }
        return account;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        accountRepository.deleteById(id);
    }

    @Override
    public Account getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public void withdraw(int accountId, BigDecimal amount, boolean isFlatFee) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Bank bank=new Bank();

        BigDecimal fee = isFlatFee ? account.getBank().getTransactionFlatFeeAmount() :
                amount.multiply(account.getBank().getTransactionPercentFeeValue().divide(BigDecimal.valueOf(100)));
        BigDecimal totalAmount = amount.add(fee);

        if (account.getAccountBalance().compareTo(totalAmount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setAccountBalance(account.getAccountBalance().subtract(totalAmount));
        accountRepository.save(account);

        bankRepository.updateTransactionAmounts(account.getBank().getBankId(), fee, amount);
    }

    @Override
    public void deposit(int accountId, BigDecimal amount, boolean isFlatFee) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Bank bank=new Bank();

        BigDecimal fee = isFlatFee ? account.getBank().getTransactionFlatFeeAmount() :
                amount.multiply(account.getBank().getTransactionPercentFeeValue().divide(BigDecimal.valueOf(100)));

        BigDecimal totalAmount = amount.subtract(fee);

        if (account.getAccountBalance().compareTo(totalAmount) < 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        account.setAccountBalance(account.getAccountBalance().add(totalAmount));
        accountRepository.save(account);

        bankRepository.updateTransactionAmounts(account.getBank().getBankId(), fee, amount);
    }
}
