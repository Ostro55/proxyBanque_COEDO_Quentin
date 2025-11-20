package org.formation.proxyBanque.service;

import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.entity.BankAccount;

import java.util.List;
import java.util.Optional;

public interface IBankAccountService {

    BankAccountDto createBankAccount(BankAccount bankAccount);

    List<BankAccountDto> getBankAccounts();

    Optional<BankAccountDto> getBankAccountById(Long id);

    Optional<BankAccountDto> deleteBankAccountById(Long id);

    Optional<BankAccountDto> updateBankAccount(BankAccount bankAccount);
}
