package org.formation.proxyBanque.service;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.entity.Client;
import org.formation.proxyBanque.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements IClientService{

    private final ClientRepository clientRepository;

    @Override
    public Client createClient(Client client) {
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