package com.demos.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import com.demos.paymybuddy.repository.AccountRepository;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AccountServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AccountServiceImplTest {
    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Test
    void testSave() {
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner);
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

        Account account3 = new Account();
        account3.setAccountNumber("42");
        account3.setBalance(10.0d);
        account3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account3.setCurrency(Currency.Euro);
        account3.setId(1L);
        account3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account3.setOwner(owner2);
        account3.setStatus(Status.ACTIVE);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account3);

        CustomUser owner3 = new CustomUser();
        owner3.setAccount(new Account());
        owner3.setAddress("42 Main St");
        owner3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner3.setEmail("jane.doe@example.org");
        owner3.setFirstName("Jane");
        owner3.setFriendsList(new ArrayList<>());
        owner3.setId(1L);
        owner3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner3.setLastName("Doe");
        owner3.setPassword("iloveyou");
        owner3.setPhone("6625550144");
        owner3.setUsername("janedoe");

        Account account4 = new Account();
        account4.setAccountNumber("42");
        account4.setBalance(10.0d);
        account4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account4.setCurrency(Currency.Euro);
        account4.setId(1L);
        account4.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account4.setOwner(owner3);
        account4.setStatus(Status.ACTIVE);

        CustomUser owner4 = new CustomUser();
        owner4.setAccount(account4);
        owner4.setAddress("42 Main St");
        owner4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner4.setEmail("jane.doe@example.org");
        owner4.setFirstName("Jane");
        owner4.setFriendsList(new ArrayList<>());
        owner4.setId(1L);
        owner4.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner4.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner4.setLastName("Doe");
        owner4.setPassword("iloveyou");
        owner4.setPhone("6625550144");
        owner4.setUsername("janedoe");

        Account account5 = new Account();
        account5.setAccountNumber("42");
        account5.setBalance(10.0d);
        account5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account5.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account5.setCurrency(Currency.Euro);
        account5.setId(1L);
        account5.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account5.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account5.setOwner(owner4);
        account5.setStatus(Status.ACTIVE);
        assertSame(account3, accountServiceImpl.save(account5));
        verify(accountRepository).save(Mockito.<Account>any());
    }

    @Test
    void testGenerateAccount() {
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner);
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

        Account account3 = new Account();
        account3.setAccountNumber("42");
        account3.setBalance(10.0d);
        account3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account3.setCurrency(Currency.Euro);
        account3.setId(1L);
        account3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account3.setOwner(owner2);
        account3.setStatus(Status.ACTIVE);
        when(accountRepository.save(Mockito.<Account>any())).thenReturn(account3);
        assertSame(account3, accountServiceImpl.generateAccount());
        verify(accountRepository).save(Mockito.<Account>any());
    }

    @Test
    void testGenerateAccountNumber() {
        AccountServiceImpl accountServiceImpl = new AccountServiceImpl(mock(AccountRepository.class));
        accountServiceImpl.generateAccountNumber();
        assertNull(accountServiceImpl.generateAccount());
    }
}

