package com.demos.paymybuddy.service;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.PaymentRequestDto;
import com.demos.paymybuddy.dto.TransactionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {

    TransactionDto findById(Long id);
    Transaction save(Transaction transaction);
    Transaction makePayment(PaymentRequestDto paymentRequestDto);
    List<TransactionDto> getAllTransactionsByAccount(Account account);
    Page<TransactionDto> getPageTransactionsByAccount(Account account, Pageable pageable);
}
