package org.formation.proxyBanque.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.repository.BankAccountRepository;
import org.formation.proxyBanque.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService{

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;

    @Override
    public BankAccountDto createBankAccount(BankAccount bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.save(bankAccount);
        return new BankAccountDto(bankAccount1.getAccountNumber());
    }

    @Override
    public List<BankAccountDto> getBankAccounts() {
        return bankAccountRepository.findAll().stream().map(v -> new BankAccountDto(v.getAccountNumber())).collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccountDto> getBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount == null){
            return Optional.empty();
        } else {
            return Optional.of(new BankAccountDto(bankAccount.getAccountNumber()));
        }
    }

    @Override
    public Optional<BankAccountDto> deleteBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            bankAccountRepository.deleteById(id);
            return Optional.of(new BankAccountDto(bankAccount.getAccountNumber()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<BankAccountDto> updateBankAccount(BankAccount bankAccount) {
        Optional<BankAccount> bankAccountSaved = bankAccountRepository.findById(bankAccount.getAccountNumber());
        if (bankAccountSaved.isPresent()) {
            BankAccount bankAccount1 = bankAccountRepository.save(bankAccount);
            return Optional.of(new BankAccountDto(bankAccount1.getAccountNumber()));
        } else {
            return Optional.empty();
        }
    }
}
