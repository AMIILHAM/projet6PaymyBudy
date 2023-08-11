package com.demos.paymybuddy.repository;

import com.demos.paymybuddy.domain.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {
    Bank findByName(String name);
}
