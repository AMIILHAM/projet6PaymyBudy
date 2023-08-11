package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.BankAccount;
import com.demos.paymybuddy.domain.BankTransfer;
import com.demos.paymybuddy.dto.BankTransferDto;
import com.demos.paymybuddy.dto.BankTransferRequestDto;
import com.demos.paymybuddy.enums.BankTransferOperation;
import com.demos.paymybuddy.mapper.TransactionMapper;
import com.demos.paymybuddy.repository.AccountRepository;
import com.demos.paymybuddy.repository.BankAccountRepository;
import com.demos.paymybuddy.repository.BankTransferRepository;
import com.demos.paymybuddy.service.BankTransferService;
import com.demos.paymybuddy.utils.Constant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankTransferServiceImpl implements BankTransferService {

    private final BankTransferRepository bankTransferRepository;
    private final AccountRepository accountRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionMapper transactionMapper;

    @Transactional(rollbackFor = SecurityException.class)
    @Override
    public BankTransfer save(BankTransfer bankTransfer) {
        return this.bankTransferRepository.save(bankTransfer);
    }

    @Override
    public List<BankTransfer> findAllByAccountIdOrderByIdAsc(Long accountId) {
        return this.bankTransferRepository.findAllByAccountIdOrderByIdAsc(accountId);
    }

    @Override
    public Page<BankTransferDto> findAllByAccountId(Long accountId, Pageable pageable) {
        return this.bankTransferRepository.findAllByAccountId(accountId,pageable)
                .map(transactionMapper::bankTransferToBankTransferDto);
    }

    @Transactional
    @Override
    public BankTransfer makeTransfer(BankTransferRequestDto bankTransferRequestDto) {

        Account account = this.accountRepository.findById(bankTransferRequestDto.getAccountId()).orElseThrow();
        BankAccount bankAccount = this.bankAccountRepository.findById(bankTransferRequestDto.getBankAccountId()).orElseThrow();
        BankTransfer bankTransfer = new BankTransfer();
        Double balanceAccount = account.getBalance();
        Double balanceBankAccount = bankAccount.getBalance();

        try {
            // WITHDRAWAL : depuis le compte bancaire vers le compte dans l'application
            if (bankTransferRequestDto.getOperation().equals(BankTransferOperation.WITHDRAWAL) && balanceBankAccount >= bankTransferRequestDto.getAmount()) {

                bankTransfer.setBankAccount(bankAccount);
                bankTransfer.setAccount(account);
                bankTransfer.setAmount(bankTransferRequestDto.getAmount());
                bankTransfer.setCreatedAt(Instant.now());
                bankTransfer.setCreatedBy(account.getOwner().getUsername());
                bankTransfer.setOperation(bankTransferRequestDto.getOperation());

                bankAccount.setBalance(balanceBankAccount - bankTransferRequestDto.getAmount());
                account.setBalance(balanceAccount + bankTransferRequestDto.getAmount());

                this.accountRepository.save(account);
                this.bankTransferRepository.save(bankTransfer);

            } else if (bankTransferRequestDto.getOperation().equals(BankTransferOperation.DEPOSIT) && balanceAccount >= bankTransferRequestDto.getAmount()) {
                    // DEPOSIT : depuis le compte dans l'application vers le compte bancaire

                bankTransfer.setBankAccount(bankAccount);
                bankTransfer.setAccount(account);
                bankTransfer.setAmount(bankTransferRequestDto.getAmount());
                bankTransfer.setCreatedAt(Instant.now());
                bankTransfer.setCreatedBy(account.getOwner().getUsername());
                bankTransfer.setOperation(bankTransferRequestDto.getOperation());

                account.setBalance(balanceBankAccount - bankTransferRequestDto.getAmount());
                bankAccount.setBalance(bankAccount.getBalance() + bankTransferRequestDto.getAmount());

                this.accountRepository.save(account);
                this.bankTransferRepository.save(bankTransfer);

            } else {
                throw new SecurityException(Constant.TRANSACTION_ERROR_INSUFFICIENT_BALANCE);
            }
            return bankTransfer;
        }catch (Exception e) {
            throw new SecurityException(e.getMessage());
        }


    }
}
