package sn.unchk.api.Restfull.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sn.unchk.api.Restfull.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}