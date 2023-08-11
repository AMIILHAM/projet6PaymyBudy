package com.demos.paymybuddy.mapper.impl;

import com.demos.paymybuddy.domain.BankTransfer;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.BankTransferDto;
import com.demos.paymybuddy.dto.TransactionDto;
import com.demos.paymybuddy.mapper.TransactionMapper;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TransactionMapperImpl implements TransactionMapper {

    public TransactionDto transactionToTransactionDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        } else {
            Long id = transaction.getId();
            Double amount = transaction.getAmount();
            Double fee = transaction.getFee();
            String destination = transaction.getDestination().getOwner().getEmail();
            String source = transaction.getSource().getOwner().getEmail();

            TransactionDto transactionDto = new TransactionDto(id, amount, fee, destination, source);
            transactionDto.setCreatedBy(transaction.getCreatedBy());
            transactionDto.setCreatedAt(transaction.getCreatedAt());
            transactionDto.setLastModifiedBy(transaction.getLastModifiedBy());
            transactionDto.setLastModifiedAt(transaction.getLastModifiedAt());
            return transactionDto;
        }
    }

    @Override
    public BankTransferDto bankTransferToBankTransferDto(BankTransfer bankTransfer) {
        if (bankTransfer == null) {
            return null;
        } else {
            Long id = bankTransfer.getId();
            Double amount = bankTransfer.getAmount();

            BankTransferDto bankTransferDto = new BankTransferDto(id, amount, bankTransfer.getOperation());
            bankTransferDto.setCreatedBy(bankTransfer.getCreatedBy());
            bankTransferDto.setCreatedAt(Instant.now());

            return bankTransferDto;
        }
    }
}
