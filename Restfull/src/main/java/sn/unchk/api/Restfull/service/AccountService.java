package sn.unchk.api.Restfull.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sn.unchk.api.Restfull.entity.Account;
import sn.unchk.api.Restfull.entity.Transaction;
import sn.unchk.api.Restfull.exceptions.AccountNotFoundException;
import sn.unchk.api.Restfull.exceptions.InsufficientFundsException;
import sn.unchk.api.Restfull.repositories.AccountRepository;
import sn.unchk.api.Restfull.repositories.TransactionRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public double getAccountBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Compte non trouve"));
        return account.getBalance();
    }

    public Page<Transaction> getAccountTransactions(Long accountId, Pageable pageable) {
        return transactionRepository.findByAccountId(accountId, pageable);
    }

    @Transactional
    public void transfer(Long accountId, double amount, Long targetAccountId) {
        Account sourceAccount = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Source compte non trouve"));
        Account targetAccount = accountRepository.findById(targetAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Compte beneficiaire non trouvee"));

        if (sourceAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Fond insuffisant");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        targetAccount.setBalance(targetAccount.getBalance() + amount);

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        Transaction transaction = new Transaction();
        transaction.setAccount(sourceAccount);
        transaction.setAmount(amount);
        transaction.setType("TRANSFER");
        transaction.setTransactionDate(LocalDateTime.now());

        transactionRepository.save(transaction);
    }
}
