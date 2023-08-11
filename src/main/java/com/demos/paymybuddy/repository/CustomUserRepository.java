package com.demos.paymybuddy.repository;

import com.demos.paymybuddy.domain.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends JpaRepository<CustomUser, Long> {

    CustomUser findByUsername(String username);
    CustomUser findByEmail(String email);

    boolean existsByEmail(String email);


}
