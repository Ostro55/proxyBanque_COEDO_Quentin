package org.formation.proxyBanque.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountCreateDto;
import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.Dto.BankAccountUpgradeDto;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.mapper.BankAccountMapper;
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
    private final BankAccountMapper bankAccountMapper;

    @Override
    public BankAccountDto createBankAccount(BankAccountCreateDto bankAccount) {
        BankAccount bankAccount1 = bankAccountRepository.save(bankAccountMapper.toEntity(bankAccount));
        return bankAccountMapper.toDto(bankAccount1);
    }

    @Override
    public List<BankAccountDto> getBankAccounts() {
        return bankAccountRepository.findAll().stream().map(bankAccountMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<BankAccountDto> getBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);
        if (bankAccount == null){
            return Optional.empty();
        } else {
            return Optional.of(bankAccountMapper.toDto(bankAccount));
        }
    }

    @Override
    public Optional<BankAccountDto> deleteBankAccountById(Long id) {
        BankAccount bankAccount = bankAccountRepository.findById(id).orElse(null);

        if (bankAccount != null) {
            bankAccountRepository.deleteById(id);
            return Optional.of(bankAccountMapper.toDto(bankAccount));
        }
        return Optional.empty();
    }

    @Transactional
    @Override
    public Optional<BankAccountDto> updateBankAccount(Long id, BankAccountUpgradeDto bankAccount) {
        Optional<BankAccount> bankAccountSaved = bankAccountRepository.findById(id);

        return  bankAccountSaved.map(v -> {
            bankAccountMapper.updateEntity(v, bankAccount);
            return bankAccountMapper.toDto(v);
        });
    }
}
