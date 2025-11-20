package org.formation.proxyBanque.repository;

import org.formation.proxyBanque.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
