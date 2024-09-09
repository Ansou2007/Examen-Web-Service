package sn.unchk.api.Restfull.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import sn.unchk.api.Restfull.entity.Transaction;
import sn.unchk.api.Restfull.repositories.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Page<Transaction> getTransactions(Long accountId, Pageable pageable) {
        if (accountId == null || pageable == null) {
            throw new IllegalArgumentException("Numero de compte et Page Obligatoire");
        }
        
        
        
        return transactionRepository.findByAccountId(accountId, pageable);
    }
}
