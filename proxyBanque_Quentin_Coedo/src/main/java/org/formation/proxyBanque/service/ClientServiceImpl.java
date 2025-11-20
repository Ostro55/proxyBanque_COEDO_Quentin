package org.formation.proxyBanque.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;
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
    @Override
    public Set<BankAccountDto> getBankAccounts(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            Set<BankAccountDto> bankAccountDtos = client.get().getBank_accounts()
                    .stream().map(v -> new BankAccountDto(v.getAccountNumber()))
                    .collect(Collectors.toSet());
            return bankAccountDtos;
        } else {
            return null;
        }
    }

    @Override
    public BankAccountDto addNewBankAccount(Long id, BankAccount bankAccount) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            bankAccount.setOwner(client.get());
            Client cli = client.get();
            cli.getBank_accounts().add(bankAccount);
            clientRepository.save(cli);
            return new  BankAccountDto(bankAccount.getAccountNumber());
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
                return new BankAccountDto(bankAccount.getAccountNumber());
            }
        }
        return null;
    }

    private final ClientRepository clientRepository;
    private final BankAccountRepository bankAccountRepository;

    @Override
    public Client createClient(Client client) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setBalance(0l);
        bankAccount.setOpening_date(LocalDate.now());
        bankAccount.setOwner(client);

        client.getBank_accounts().add(bankAccount);
        bankAccountRepository.save(bankAccount);
        clientRepository.save(client);
        return client;
    }

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Optional<Client> DeleteClientById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
        }

        return client;
    }

    @Override
    public Optional<Client> UpdateClient(Client client) {
        Optional<Client> clientSaved = clientRepository.findById(client.getId());
        if (clientSaved.isPresent()) {
            clientRepository.save(client);
            return Optional.of(client);
        } else  {
            return Optional.empty();
        }
    }
}