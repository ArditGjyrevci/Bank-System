package com.example.Bank_System_Project.services.implementations;

import com.example.Bank_System_Project.daos.TransactionRepository;
import com.example.Bank_System_Project.entities.Account;
import com.example.Bank_System_Project.entities.Bank;
import com.example.Bank_System_Project.entities.Transaction;
import com.example.Bank_System_Project.services.interfaces.AccountService;
import com.example.Bank_System_Project.services.interfaces.BankService;
import com.example.Bank_System_Project.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImplementation implements TransactionService {
    private TransactionRepository transactionRepository;
    private AccountService accountService;
    private BankService bankService;
    @Autowired
    public TransactionServiceImplementation (TransactionRepository transactionRepository, AccountService accountService, BankService bankService)
    {
        this.transactionRepository=transactionRepository;
        this.accountService=accountService;
        this.bankService=bankService;
    }
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction findById(Integer id) {
        Optional<Transaction> result = transactionRepository.findById(id);
        Transaction transaction=null;
        if(result.isPresent()){
            transaction = result.get();
        }else{
            throw new RuntimeException("Did not found service id - "+id);
        }
        return transaction;
    }

    @Override
    public void performTransaction(Transaction transaction, boolean isFlatFee) throws Exception {
        Account originatingAccount = accountService.findById(transaction.getOriginatingAccount().getAccountId());
        Account resultingAccount = accountService.findById(transaction.getResultingAccount().getAccountId());

//        if (originatingAccount == null || resultingAccount == null) {
//            throw new IllegalArgumentException("Invalid account ID(s).");
//        }

        BigDecimal amount=transaction.getAmount();

        if (originatingAccount.getAccountBalance().compareTo(amount) < 0) {
            throw new IllegalArgumentException("Insufficient balance in the originating account.");
        }

        BigDecimal transactionFee = isFlatFee ? originatingAccount.getBank().getTransactionFlatFeeAmount() :
                amount.multiply(originatingAccount.getBank().getTransactionPercentFeeValue().divide(BigDecimal.valueOf(100)));

        originatingAccount.setAccountBalance(originatingAccount.getAccountBalance().subtract(amount).subtract(transactionFee));
        resultingAccount.setAccountBalance(resultingAccount.getAccountBalance().add(amount));

        Bank bank = originatingAccount.getBank();
        bankService.save(bank.getBankId(), transactionFee, amount);

        accountService.save(originatingAccount);
        accountService.save(resultingAccount);


    }
    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> findByAccountId(Integer accountId) {
        Account account = accountService.findById(accountId);
        if (account == null ) {
            throw new IllegalArgumentException("Invalid account ID(s).");
        }
        List<Transaction>transactions=findAll();
        for (Transaction transaction : transactions) {
            if(transaction.getResultingAccount().getAccountId()==accountId||transaction.getOriginatingAccount().getAccountId()==accountId)
            System.out.println(transaction);
        }
        return transactions;
    }

    @Override
    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }
}
