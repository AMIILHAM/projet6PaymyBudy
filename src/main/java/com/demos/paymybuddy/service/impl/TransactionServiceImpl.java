package com.demos.paymybuddy.service.impl;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.PaymentRequestDto;
import com.demos.paymybuddy.dto.TransactionDto;
import com.demos.paymybuddy.exception.ServiceException;
import com.demos.paymybuddy.mapper.TransactionMapper;
import com.demos.paymybuddy.repository.TransactionRepository;
import com.demos.paymybuddy.service.AccountService;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.TransactionService;
import com.demos.paymybuddy.utils.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

     private final TransactionRepository transactionRepository;
     private final TransactionMapper transactionMapper;
     private final CustomUserService userService;
     private final AccountService accountService;

    @Override
    public TransactionDto findById(Long id) {
        Optional<Transaction> transaction = this.transactionRepository.findById(id);
        return transaction.map(this.transactionMapper::transactionToTransactionDto).orElse(null);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return this.transactionRepository.save(transaction);
    }

    @Transactional(rollbackOn = ServiceException.class)
    @Override
    public Transaction makePayment(PaymentRequestDto paymentRequestDto) {
        try {
            Optional<CustomUser> userSender = this.userService.findById(paymentRequestDto.getSourceAccountId());
            CustomUser userReceiver = this.userService.findByEmail(paymentRequestDto.getDestinationAccountEmail());

            if (userSender.isPresent()) {

                Account sourceAccount = userSender.get().getAccount();
                Account destinationAccount = userReceiver.getAccount();

                // Calcule de frais du transfers.
                Double fee = paymentRequestDto.getAmount() * Constant.COMMESSION;

                // Test si le solde du compte est supérieur au (Montant transferé * le cout du transfer de l'app)
                if (sourceAccount.getBalance() >= (paymentRequestDto.getAmount()) + fee) {
                    // Ici on diminue le cout du transfert
                    sourceAccount.setBalance(sourceAccount.getBalance() - fee);
                    // Ici on diminue le montant depuis le compte qui a effectue le transfer
                    sourceAccount.setBalance(sourceAccount.getBalance() - paymentRequestDto.getAmount());

                    // Ici on ajout le montant au compte destinataire
                    destinationAccount.setBalance(destinationAccount.getBalance() + paymentRequestDto.getAmount());


                    // Après l'operation s'effectue correctement on cree un transaction pour l'enregistrer
                    // au niveau de la base de donnees
                    Transaction transaction = new Transaction();
                    transaction.setAmount(paymentRequestDto.getAmount());
                    transaction.setSource(sourceAccount);
                    transaction.setDestination(destinationAccount);
                    transaction.setFee(fee);

                    transaction.setCreatedAt(Instant.now());
                    transaction.setCreatedBy(sourceAccount.getOwner().getUsername());
                    transaction.setLastModifiedAt(Instant.now());
                    transaction.setLastModifiedBy(sourceAccount.getOwner().getUsername());


                    // ici on fait la persistance des enregistrements au niveau de la base de données.
                    this.accountService.save(sourceAccount);
                    this.accountService.save(destinationAccount);
                    this.transactionRepository.save(transaction);

                    return transaction;
                }else {
                    throw new ServiceException(Constant.TRANSACTION_ERROR_INSUFFICIENT_BALANCE);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TransactionDto> getAllTransactionsByAccount(Account account) {
        return this.transactionRepository.findAllBySourceOrDestinationOrderByCreatedAtDesc(account,account)
                .stream().map(this.transactionMapper::transactionToTransactionDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<TransactionDto> getPageTransactionsByAccount(Account account, Pageable pageable) {
        return this.transactionRepository.findAllBySourceOrDestinationOrderByCreatedAtDesc(account,account,pageable)
                .map(this.transactionMapper::transactionToTransactionDto);
    }
}
