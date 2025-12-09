package org.formation.proxyBanque.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.*;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.entity.Client;
import org.formation.proxyBanque.service.ClientServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class ClientController {

    public final ClientServiceImpl clientService;

    @PostMapping("client/save")
    public ResponseEntity<ClientDto> save(@RequestBody @Valid ClientCreateDto client) {
        ClientDto newClient = clientService.createClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<List<ClientDto>> getClients() {
        List<ClientDto> clients = clientService.getClients();
        if (!clients.isEmpty()) {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        Optional<ClientDto> client = clientService.getClientById(id);
        return client.map(clientDto -> new ResponseEntity<>(clientDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("client/delete/{id}")
    public ResponseEntity<ClientDto> deleteClient(@PathVariable Long id) {
        Optional<ClientDto> client = clientService.DeleteClientById(id);
        return client.map(clientDto -> new ResponseEntity<>(clientDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("client/{id}/update")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @RequestBody @Valid ClientUpgradeDto client) {
        Optional<ClientDto> clientUpdated = clientService.UpdateClient(id, client);
        return clientUpdated.map(clientDto -> new ResponseEntity<>(clientDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("client/{id}/bankAccounts")
    public ResponseEntity<Set<BankAccountDto>> getBankAccounts(@PathVariable Long id) {
        Set<BankAccountDto> bankAccounts = clientService.getBankAccounts(id);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PostMapping("client/{id}/bankAccount/new")
    public ResponseEntity<BankAccountDto> newBankAccount(@RequestBody BankAccountCreateDto bankAccount,
                                                      @PathVariable Long id) {
        BankAccountDto bankAccount1 = clientService.addNewBankAccount(id, bankAccount);
        if (bankAccount1 != null) {
            return new ResponseEntity<>(bankAccount1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
