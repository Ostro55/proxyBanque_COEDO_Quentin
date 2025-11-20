package org.formation.proxyBanque.service;

import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IClientService {

    Client createClient(Client client);

    List<Client> getClients();

    Optional<Client> getClientById(Long id);

    Optional<Client> DeleteClientById(Long id);

    Optional<Client> UpdateClient(Client client);

    Set<BankAccountDto> getBankAccounts(Long id);

    BankAccountDto addNewBankAccount(Long id, BankAccount bankAccount);

    BankAccountDto remove(Long id, Long bankAccountId);
}
