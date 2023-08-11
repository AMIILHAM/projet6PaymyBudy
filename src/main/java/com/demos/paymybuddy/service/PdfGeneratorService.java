package com.demos.paymybuddy.service;

import com.demos.paymybuddy.dto.TransactionDto;

public interface PdfGeneratorService {
    byte[] generateTransactionPdf(String username, Long transactionID);
    byte[] generateAllTransactionsPdf(String username);

}
