package service;

import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "service.BankAccountService")
public class BankAccountServiceImpl implements BankAccountService {

    @Override
    public double getBalance(String accountId) {
        // Logique pour obtenir le solde du compte
        return 1000.0; // Valeur fictive pour l'exemple
    }

    @Override
    public List<Transaction> getTransactions(String accountId, int pageNumber, int pageSize) {
        // Exemple de création de transactions fictives
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("txn001", accountId, 10000, "FCFA", "credit", "2024-09-09"));
        transactions.add(new Transaction("txn002", accountId, 5000, "FCFA", "debit", "2024-09-08"));
        
        // Logique de pagination
        int fromIndex = Math.min(pageNumber * pageSize, transactions.size());
        int toIndex = Math.min(fromIndex + pageSize, transactions.size());
        return transactions.subList(fromIndex, toIndex);
    }

    @Override
    public boolean transferFunds(String creditor, String debtor, double amount, String currency) {
        // Logique pour effectuer un virement
        return true; // Valeur fictive pour l'exemple
    }
}
