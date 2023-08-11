package com.demos.paymybuddy.service;

import com.demos.paymybuddy.domain.Account;

public interface AccountService {
    Account save(Account account);
    Account generateAccount();
    public String generateAccountNumber();
}
