package org.formation.proxyBanque.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountDto;
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
    public ResponseEntity<Client> save(@RequestBody Client client) {
        Client newClient = clientService.createClient(client);
        return new ResponseEntity<Client>(newClient, HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.getClients();
        if (!clients.isEmpty()) {
            return new ResponseEntity<List<Client>>(clients, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Long id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("client/delete/{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        Optional<Client> client = clientService.DeleteClientById(id);
        if (client.isPresent()) {
            return new ResponseEntity<Client>(client.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("client/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Optional<Client> clientUpdated = clientService.UpdateClient(client);
        if (clientUpdated.isPresent()) {
            return new ResponseEntity<Client>(clientUpdated.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("client/bankAccounts/{id}")
    public ResponseEntity<Set<BankAccountDto>> getBankAccounts(@PathVariable Long id) {
        Set<BankAccountDto> bankAccounts = clientService.getBankAccounts(id);
        return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
    }

    @PostMapping("client/bankAccount/new/{id}")
    public ResponseEntity<BankAccountDto> newBankAccount(@RequestBody BankAccount bankAccount,
                                                      @PathVariable Long id) {
        BankAccountDto bankAccount1 = clientService.addNewBankAccount(id, bankAccount);
        if (bankAccount1 != null) {
            return new ResponseEntity<>(bankAccount1, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
