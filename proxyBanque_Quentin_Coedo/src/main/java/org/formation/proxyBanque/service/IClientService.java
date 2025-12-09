package org.formation.proxyBanque.service;

import org.formation.proxyBanque.Dto.*;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IClientService {

    ClientDto createClient(ClientCreateDto client);

    List<ClientDto> getClients();

    Optional<ClientDto> getClientById(Long id);

    Optional<ClientDto> DeleteClientById(Long id);

    Optional<ClientDto> UpdateClient(Long id, ClientUpgradeDto client);

    Set<BankAccountDto> getBankAccounts(Long id);

    BankAccountDto addNewBankAccount(Long id, BankAccountCreateDto bankAccount);

    // Did not have time to use
    BankAccountDto remove(Long id, Long bankAccountId);
}
