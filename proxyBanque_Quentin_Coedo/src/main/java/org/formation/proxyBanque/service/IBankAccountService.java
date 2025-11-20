package org.formation.proxyBanque.service;

import org.formation.proxyBanque.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountService {

    BankAccount createBankAccount(BankAccount bankAccount);

    List<BankAccount> getBankAccounts();

    Optional<BankAccount> getBankAccountById(Long id);

    Optional<BankAccount> deleteBankAccountById(Long id);

    Optional<BankAccount> updateBankAccount(BankAccount bankAccount);
}
