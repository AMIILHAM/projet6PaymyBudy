package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.repository.AccountRepository;
import com.demos.paymybuddy.service.AccountService;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    // une dependence (Account DAO - la communication avec la base de donnees)
    private final AccountRepository accountRepository;

    @Override
    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    // Methode pour generer un nouveau compte bancaire lors la creation d'un nouveau compte utilisateur dans l'application
    @Override
    public Account generateAccount() {

        Account newAccount = new Account();

        newAccount.setAccountNumber(this.generateAccountNumber());
        newAccount.setStatus(Status.ACTIVE);
        newAccount.setCurrency(Currency.Euro);
        newAccount.setBalance(0.0);
        newAccount.setCreatedAt(Instant.now());

        return this.accountRepository.save(newAccount);
    }


    // methode qui genere un num du compte
    @Override
    public String generateAccountNumber() {
        String accountNumber = "";
        int length = 10;
        String prefix = "ACCT";
        Random random = new Random();

        do {

            int randomNumber = random.nextInt(10000);
            accountNumber = prefix + String.format("%0" + (length - prefix.length()) + "d", randomNumber);

        }while (this.accountRepository.existsByAccountNumber(accountNumber));

        return accountNumber;
    }

}
