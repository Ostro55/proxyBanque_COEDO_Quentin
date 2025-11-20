package org.formation.proxyBanque.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;
import org.formation.proxyBanque.repository.BankAccountRepository;
import org.formation.proxyBanque.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService{
    @Override
    public Set<BankAccount> getBankAccounts(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return client.get().getBank_accounts();
        } else {
            return null;
        }
    }

    @Override
    public BankAccount addNewBankAccount(Long id, Long bankAccount_id) {
        Optional<Client> client = clientRepository.findById(id);
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankAccount_id);
        if (client.isPresent() &&  bankAccount.isPresent()) {
            Client cli = client.get();
            cli.getBank_accounts().add(bankAccount.get());
            clientRepository.save(cli);
            return bankAccount.get();
        } else {
            return null;
        }
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