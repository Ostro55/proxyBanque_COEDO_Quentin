package org.formation.proxyBanque.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.formation.proxyBanque.Dto.BankAccountCreateDto;
import org.formation.proxyBanque.Dto.BankAccountDto;
import org.formation.proxyBanque.Dto.BankAccountUpgradeDto;
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
    public ResponseEntity<BankAccountDto> saveAccount(@RequestBody BankAccountCreateDto bankAccount){
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
        return bankAccount.map(bankAccountDto -> new ResponseEntity<>(bankAccountDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("bankAccount/delete/{id}")
    public ResponseEntity<BankAccountDto> deleteBankAccount(@PathVariable Long id){
        Optional<BankAccountDto> bankAccount = bankAccountService.deleteBankAccountById(id);

        return bankAccount.map(bankAccountDto -> new ResponseEntity<>(bankAccountDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("bankAccount/{id}/update")
    public ResponseEntity<BankAccountDto> updateBankAccount(@PathVariable Long id, @RequestBody @Valid BankAccountUpgradeDto bankAccount){
        Optional<BankAccountDto> bankAccountSaved = bankAccountService.updateBankAccount(id, bankAccount);
        return bankAccountSaved.map(bankAccountDto -> new ResponseEntity<>(bankAccountDto, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
