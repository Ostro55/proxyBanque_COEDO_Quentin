package org.formation.proxyBanque.service;

import org.formation.proxyBanque.entity.Client;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    Client createClient(Client client);

    List<Client> getClients();

    Optional<Client> getClientById(Long id);

    Optional<Client> DeleteClientById(Long id);

    Optional<Client> UpdateClient(Client client);
}
