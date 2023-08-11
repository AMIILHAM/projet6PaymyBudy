package com.demos.paymybuddy.repository;

import com.demos.paymybuddy.domain.BankTransfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankTransferRepository extends JpaRepository<BankTransfer, Integer> {
    List<BankTransfer> findAllByAccountIdOrderByIdAsc(Long accountId);
    Page<BankTransfer> findAllByAccountId(Long accountId, Pageable pageable);
}
