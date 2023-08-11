package com.demos.paymybuddy.service;

import com.demos.paymybuddy.domain.BankTransfer;
import com.demos.paymybuddy.dto.BankTransferDto;
import com.demos.paymybuddy.dto.BankTransferRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BankTransferService {

    BankTransfer save(BankTransfer bankTransfer);
    List<BankTransfer> findAllByAccountIdOrderByIdAsc(Long accountId);

    Page<BankTransferDto> findAllByAccountId(Long accountId, Pageable pageable);

    BankTransfer makeTransfer(BankTransferRequestDto bankTransferRequestDto);


}
