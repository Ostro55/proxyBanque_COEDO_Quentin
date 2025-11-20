package org.formation.proxyBanque.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.entity.BankAccount;
import org.formation.proxyBanque.service.BankAccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountServiceImpl bankAccountService;

    @PostMapping("bankAccount/save")
    public ResponseEntity<BankAccount> saveAccount(@RequestBody BankAccount bankAccount){
        BankAccount bankAccountSaved = bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(bankAccountSaved,  HttpStatus.OK);
    }

    @GetMapping("bankAccounts")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(){
        List<BankAccount> bankAccounts = bankAccountService.getBankAccounts();
        if (!bankAccounts.isEmpty()) {
            return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("bankAccount/{id}")
    public ResponseEntity<BankAccount> getBankAccount(@PathVariable Long id){
        Optional<BankAccount> bankAccount = bankAccountService.getBankAccountById(id);
        if (bankAccount.isPresent()) {
            return new ResponseEntity<>(bankAccount.get(), HttpStatus.OK);
        }  else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("bankAccount/delete/{id}")
    public ResponseEntity<BankAccount> deleteBankAccount(@PathVariable Long id){
        Optional<BankAccount> bankAccount = bankAccountService.deleteBankAccountById(id);

        if (bankAccount.isPresent()) {
            return new ResponseEntity<>(bankAccount.get(), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("bankAccount/update")
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount){
        Optional<BankAccount> bankAccountSaved = bankAccountService.updateBankAccount(bankAccount);
        if (bankAccountSaved.isPresent()) {
            return new ResponseEntity<>(bankAccountSaved.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
