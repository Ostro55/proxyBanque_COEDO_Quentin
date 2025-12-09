package org.formation.proxyBanque.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.*;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;
import org.formation.proxyBanque.mapper.BankAccountMapper;
import org.formation.proxyBanque.mapper.ClientMapper;
import org.formation.proxyBanque.repository.BankAccountRepository;
import org.formation.proxyBanque.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService{

    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;
    private final BankAccountMapper bankAccountMapper;
    private final ClientMapper clientMapper;

    @Override
    public Set<BankAccountDto> getBankAccounts(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> value.getBank_accounts()
                .stream().map(bankAccountMapper::toDto)
                .collect(Collectors.toSet())).orElse(null);
    }

    @Override
    public BankAccountDto addNewBankAccount(Long id, BankAccountCreateDto bankAccount) {
        Optional<Client> client = clientRepository.findById(id);
        BankAccount newBankAccount = bankAccountMapper.toEntity(bankAccount);

        if (client.isPresent()) {
            newBankAccount.setOwner(client.get());
            Client cli = client.get();
            cli.getBank_accounts().add(newBankAccount);
            clientRepository.save(cli);
            return bankAccountMapper.toDto(newBankAccount);
        } else {
            return null;
        }
    }

    @Override
    public BankAccountDto remove(Long id, Long bankAccountId) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client != null) {
            BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElse(null);
            if (bankAccount != null && client.getBank_accounts().contains(bankAccount)) {
                client.getBank_accounts().remove(bankAccount);
                bankAccountRepository.delete(bankAccount);
                return bankAccountMapper.toDto(bankAccount);
            }
        }
        return null;
    }

    @Override
    public ClientDto createClient(ClientCreateDto client) {

        Client newClient = clientMapper.toEntity(client);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(0l);
        bankAccount.setOpening_date(LocalDate.now());
        bankAccount.setOwner(newClient);

        newClient.getBank_accounts().add(bankAccount);
        bankAccountRepository.save(bankAccount);
        clientRepository.save(newClient);
        return clientMapper.toDto(newClient);
    }

    @Override
    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ClientDto> getClientById(Long id) {
        return clientRepository.findById(id).map(clientMapper::toDto);
    }

    @Override
    public Optional<ClientDto> DeleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        }

        return client.map(clientMapper::toDto);
    }

    @Transactional
    @Override
    public Optional<ClientDto> UpdateClient(Long id, ClientUpgradeDto client) {
        Optional<Client> clientSaved = clientRepository.findById(id);
        return clientSaved.map(v -> {
            clientMapper.updateEntity(v, client);
            return clientMapper.toDto(v);});
    }
}