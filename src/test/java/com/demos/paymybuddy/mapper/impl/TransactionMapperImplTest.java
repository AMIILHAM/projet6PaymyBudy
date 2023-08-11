package com.demos.paymybuddy.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.TransactionDto;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionMapperImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionMapperImplTest {
    @Autowired
    private TransactionMapperImpl transactionMapperImpl;

    /**
     * Method under test: {@link TransactionMapperImpl#transactionToTransactionDto(Transaction)}
     */
    @Test
    void testTransactionToTransactionDto() {
        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(new CustomUser());
        account.setStatus(Status.ACTIVE);

        CustomUser owner = new CustomUser();
        owner.setAccount(account);
        owner.setAddress("42 Main St");
        owner.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner.setEmail("jane.doe@example.org");
        owner.setFirstName("Jane");
        owner.setFriendsList(new ArrayList<>());
        owner.setId(1L);
        owner.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner.setLastName("Doe");
        owner.setPassword("iloveyou");
        owner.setPhone("6625550144");
        owner.setUsername("janedoe");

        Account destination = new Account();
        destination.setAccountNumber("42");
        destination.setBalance(10.0d);
        destination.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        destination.setCurrency(Currency.Euro);
        destination.setId(1L);
        destination.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        destination.setOwner(owner);
        destination.setStatus(Status.ACTIVE);

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(new CustomUser());
        account2.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account2);
        owner2.setAddress("42 Main St");
        owner2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner2.setEmail("jane.doe@example.org");
        owner2.setFirstName("Jane");
        owner2.setFriendsList(new ArrayList<>());
        owner2.setId(1L);
        owner2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner2.setLastName("Doe");
        owner2.setPassword("iloveyou");
        owner2.setPhone("6625550144");
        owner2.setUsername("janedoe");

        Account source = new Account();
        source.setAccountNumber("42");
        source.setBalance(10.0d);
        source.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        source.setCurrency(Currency.Euro);
        source.setId(1L);
        source.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        source.setOwner(owner2);
        source.setStatus(Status.ACTIVE);

        Transaction transaction = new Transaction();
        transaction.setAmount(10.0d);
        transaction.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        transaction.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        transaction.setDestination(destination);
        transaction.setFee(10.0d);
        transaction.setId(1L);
        transaction.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        transaction.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        transaction.setSource(source);
        TransactionDto actualTransactionToTransactionDtoResult = transactionMapperImpl
                .transactionToTransactionDto(transaction);
        assertEquals(10.0d, actualTransactionToTransactionDtoResult.getAmount().doubleValue());
        assertEquals("jane.doe@example.org", actualTransactionToTransactionDtoResult.getSource());
        assertEquals("Jan 1, 2020 9:00am GMT+0100", actualTransactionToTransactionDtoResult.getLastModifiedBy());
        assertEquals(1L, actualTransactionToTransactionDtoResult.getId().longValue());
        Instant expectedCreatedAt = actualTransactionToTransactionDtoResult.getLastModifiedAt();
        assertSame(expectedCreatedAt, actualTransactionToTransactionDtoResult.getCreatedAt());
        assertEquals("jane.doe@example.org", actualTransactionToTransactionDtoResult.getDestination());
        assertEquals(10.0d, actualTransactionToTransactionDtoResult.getFee().doubleValue());
        assertEquals("Jan 1, 2020 8:00am GMT+0100", actualTransactionToTransactionDtoResult.getCreatedBy());
    }
}

