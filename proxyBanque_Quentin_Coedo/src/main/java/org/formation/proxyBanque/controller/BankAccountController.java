package org.formation.proxyBanque.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountDto;
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
    public ResponseEntity<BankAccountDto> saveAccount(@RequestBody BankAccount bankAccount){
        BankAccountDto bankAccountSaved = bankAccountService.createBankAccount(bankAccount);
        return new ResponseEntity<>(bankAccountSaved,  HttpStatus.OK);
    }

    @GetMapping("bankAccounts")
    public ResponseEntity<List<BankAccountDto>> getAllBankAccounts(){
        List<BankAccountDto> bankAccounts = bankAccountService.getBankAccounts();
        if (!bankAccounts.isEmpty()) {
            return new ResponseEntity<>(bankAccounts, HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("bankAccount/{id}")
    public ResponseEntity<BankAccountDto> getBankAccount(@PathVariable Long id){
        Optional<BankAccountDto> bankAccount = bankAccountService.getBankAccountById(id);
        if (bankAccount.isPresent()) {
            return new ResponseEntity<>(bankAccount.get(), HttpStatus.OK);
        }  else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("bankAccount/delete/{id}")
    public ResponseEntity<BankAccountDto> deleteBankAccount(@PathVariable Long id){
        Optional<BankAccountDto> bankAccount = bankAccountService.deleteBankAccountById(id);

        if (bankAccount.isPresent()) {
            return new ResponseEntity<>(bankAccount.get(), HttpStatus.OK);
        } else  {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("bankAccount/update")
    public ResponseEntity<BankAccountDto> updateBankAccount(@RequestBody BankAccount bankAccount){
        Optional<BankAccountDto> bankAccountSaved = bankAccountService.updateBankAccount(bankAccount);
        if (bankAccountSaved.isPresent()) {
            return new ResponseEntity<>(bankAccountSaved.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
