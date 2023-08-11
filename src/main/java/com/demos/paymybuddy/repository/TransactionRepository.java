package com.demos.paymybuddy.repository;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllBySourceOrDestinationOrderByCreatedAtDesc(Account sourceAccount,Account destinationAccount);
    Page<Transaction> findAllBySourceOrDestinationOrderByCreatedAtDesc(Account sourceAccount,Account destinationAccount,Pageable pageable);
}
