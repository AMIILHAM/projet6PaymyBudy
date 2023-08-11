package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.BankAccount;
import com.demos.paymybuddy.repository.BankAccountRepository;
import com.demos.paymybuddy.service.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        return this.bankAccountRepository.save(bankAccount);
    }
}
