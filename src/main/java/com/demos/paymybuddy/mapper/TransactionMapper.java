package com.demos.paymybuddy.mapper;

import com.demos.paymybuddy.domain.BankTransfer;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.BankTransferDto;
import com.demos.paymybuddy.dto.TransactionDto;

public interface TransactionMapper {

    TransactionDto transactionToTransactionDto(Transaction transaction);

    BankTransferDto bankTransferToBankTransferDto(BankTransfer bankTransfer);
}
