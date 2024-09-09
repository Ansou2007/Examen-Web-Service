package sn.unchk.api.Restfull.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import sn.unchk.api.Restfull.entity.Transaction;
import sn.unchk.api.Restfull.service.AccountService;
import sn.unchk.api.Restfull.service.TransactionService;

@RestController

@RequestMapping("/accounts")
public class AccountController {

	/*
	 * @Autowired
	 * 
	 * private AccountService accountService;
	 * 
	 * @Autowired private TransactionService transactionService;
	 * 
	 * @Operation(summary = "Consultation du solde du compte" ,description =
	 * "Cet endpoint permettra de consulter le solde actuel d'un compte bancaire")
	 * 
	 * @ApiResponse(responseCode = "200",description = "solde trouve")
	 * 
	 * @GetMapping("/{accountId}/balance") public ResponseEntity<Double>
	 * getBalance(@PathVariable Long accountId) { Double balance =
	 * accountService.getBalance(accountId); return ResponseEntity.ok(balance); }
	 * 
	 * @Operation(summary = "Historique des transactions",description =
	 * "Cet endpoint permettra de récupérer l'historique des transactions du compte spécifié. Il est important d'implémenter la pagination pour éviter de surcharger les réponses et améliorer les performances."
	 * )
	 * 
	 * @ApiResponse(responseCode = "200",description = "transaction trouvee")
	 * 
	 * @ApiResponse(responseCode = "404",description = "Compte introuvable")
	 * 
	 * @GetMapping("/{accountId}/transactions") public
	 * ResponseEntity<Page<Transaction>> getTransactions(@PathVariable Long
	 * accountId,
	 * 
	 * @RequestParam int page,
	 * 
	 * @RequestParam int size) { Pageable pageable = PageRequest.of(page, size);
	 * Page<Transaction> transactions =
	 * transactionService.getTransactions(accountId, pageable); return
	 * ResponseEntity.ok(transactions); }
	 * 
	 * @Operation(summary = "Effectuer un virement",description =
	 * "Cet endpoint permettra d'effectuer un virement depuis le compte spécifié par accountId vers un autre compte. Vous devrez probablement passer des paramètres tels que le montant du virement et l'identifiant du compte destinataire"
	 * )
	 * 
	 * @ApiResponse(responseCode = "200",description = "virement effectuee")
	 * 
	 * @PostMapping("/{accountId}/transfer") public ResponseEntity<Void>
	 * transfer(@PathVariable Long accountId,
	 * 
	 * @RequestParam String targetAccountNumber,
	 * 
	 * @RequestParam Double amount) { accountService.transfer(accountId,
	 * targetAccountNumber, amount); return ResponseEntity.ok().build(); }
	 */

   
    @Autowired
    private AccountService accountService;

    @Operation(summary = "Consultation du solde du compte",
               description = "Cet endpoint permet de consulter le solde actuel d'un compte bancaire.")
    @ApiResponse(responseCode = "200", description = "Solde trouvé avec succès")
    @GetMapping("/{accountId}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable Long accountId) {
        Double balance = accountService.getAccountBalance(accountId);
        return ResponseEntity.ok(balance);
    }

    @Operation(summary = "Historique des transactions",
               description = "Cet endpoint permet de récupérer l'historique des transactions du compte spécifié. La pagination est implémentée pour améliorer les performances.")
    @ApiResponse(responseCode = "200", description = "Transactions récupérées avec succès")
    @ApiResponse(responseCode = "404", description = "Compte introuvable")
    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<Page<Transaction>> getTransactions(
            @PathVariable Long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Transaction> transactions = accountService.getAccountTransactions(accountId, pageable);
        return ResponseEntity.ok(transactions);
    }

    @Operation(summary = "Effectuer un virement",
               description = "Cet endpoint permet d'effectuer un virement depuis le compte spécifié par accountId vers un autre compte. Les paramètres incluent le montant du virement et l'identifiant du compte destinataire.")
    @ApiResponse(responseCode = "200", description = "Virement effectué avec succès")
    @PostMapping("/{accountId}/transfer")
    public ResponseEntity<Void> transfer(
            @PathVariable Long accountId,
            @RequestParam double amount,
            @RequestParam Long targetAccountId) {
        accountService.transfer(accountId, amount, targetAccountId);
        return ResponseEntity.ok().build();
    }
}
