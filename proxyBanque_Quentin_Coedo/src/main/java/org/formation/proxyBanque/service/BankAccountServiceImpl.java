package org.formation.proxyBanque.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService{

    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<BankAccount> getBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Optional<BankAccount> getBankAccountById(Long id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Optional<BankAccount> deleteBankAccountById(Long id) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(id);
        if (bankAccount.isPresent()) {
            bankAccountRepository.deleteById(id);
        }
        return bankAccount;
    }

    @Override
    public Optional<BankAccount> updateBankAccount(BankAccount bankAccount) {
        Optional<BankAccount> bankAccountSaved = bankAccountRepository.findById(bankAccount.getAccountNumber());
        if (bankAccountSaved.isPresent()) {
            return Optional.of(bankAccountRepository.save(bankAccount));
        } else {
            return Optional.empty();
        }
    }
}
