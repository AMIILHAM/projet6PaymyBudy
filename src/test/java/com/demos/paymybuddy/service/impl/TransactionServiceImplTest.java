package com.demos.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.domain.Transaction;
import com.demos.paymybuddy.dto.PaymentRequestDto;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import com.demos.paymybuddy.mapper.TransactionMapper;
import com.demos.paymybuddy.repository.TransactionRepository;
import com.demos.paymybuddy.service.AccountService;
import com.demos.paymybuddy.service.CustomUserService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TransactionServiceImpl.class})
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomUserService customUserService;

    @MockBean
    private TransactionMapper transactionMapper;

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionServiceImpl transactionServiceImpl;

    /**
     * Method under test: {@link TransactionServiceImpl#save(Transaction)}
     */
    @Test
    void testSave() {
        CustomUser owner = new CustomUser();
        owner.setAccount(new Account());
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

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(owner);
        account.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account);
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

        Account destination = new Account();
        destination.setAccountNumber("42");
        destination.setBalance(10.0d);
        destination.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        destination.setCurrency(Currency.Euro);
        destination.setId(1L);
        destination.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        destination.setOwner(owner2);
        destination.setStatus(Status.ACTIVE);

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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner3);
        account2.setStatus(Status.ACTIVE);

        CustomUser owner4 = new CustomUser();
        owner4.setAccount(account2);
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

        Account source = new Account();
        source.setAccountNumber("42");
        source.setBalance(10.0d);
        source.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        source.setCurrency(Currency.Euro);
        source.setId(1L);
        source.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        source.setOwner(owner4);
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
        when(transactionRepository.save(Mockito.<Transaction>any())).thenReturn(transaction);

        Account account3 = new Account();
        account3.setAccountNumber("42");
        account3.setBalance(10.0d);
        account3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account3.setCurrency(Currency.Euro);
        account3.setId(1L);
        account3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account3.setOwner(new CustomUser());
        account3.setStatus(Status.ACTIVE);

        CustomUser owner5 = new CustomUser();
        owner5.setAccount(account3);
        owner5.setAddress("42 Main St");
        owner5.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner5.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner5.setEmail("jane.doe@example.org");
        owner5.setFirstName("Jane");
        owner5.setFriendsList(new ArrayList<>());
        owner5.setId(1L);
        owner5.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner5.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner5.setLastName("Doe");
        owner5.setPassword("iloveyou");
        owner5.setPhone("6625550144");
        owner5.setUsername("janedoe");

        Account destination2 = new Account();
        destination2.setAccountNumber("42");
        destination2.setBalance(10.0d);
        destination2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        destination2.setCurrency(Currency.Euro);
        destination2.setId(1L);
        destination2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        destination2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        destination2.setOwner(owner5);
        destination2.setStatus(Status.ACTIVE);

        Account account4 = new Account();
        account4.setAccountNumber("42");
        account4.setBalance(10.0d);
        account4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account4.setCurrency(Currency.Euro);
        account4.setId(1L);
        account4.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account4.setOwner(new CustomUser());
        account4.setStatus(Status.ACTIVE);

        CustomUser owner6 = new CustomUser();
        owner6.setAccount(account4);
        owner6.setAddress("42 Main St");
        owner6.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner6.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        owner6.setEmail("jane.doe@example.org");
        owner6.setFirstName("Jane");
        owner6.setFriendsList(new ArrayList<>());
        owner6.setId(1L);
        owner6.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        owner6.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        owner6.setLastName("Doe");
        owner6.setPassword("iloveyou");
        owner6.setPhone("6625550144");
        owner6.setUsername("janedoe");

        Account source2 = new Account();
        source2.setAccountNumber("42");
        source2.setBalance(10.0d);
        source2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        source2.setCurrency(Currency.Euro);
        source2.setId(1L);
        source2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        source2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        source2.setOwner(owner6);
        source2.setStatus(Status.ACTIVE);

        Transaction transaction2 = new Transaction();
        transaction2.setAmount(10.0d);
        transaction2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        transaction2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        transaction2.setDestination(destination2);
        transaction2.setFee(10.0d);
        transaction2.setId(1L);
        transaction2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        transaction2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        transaction2.setSource(source2);
        assertSame(transaction, transactionServiceImpl.save(transaction2));
        verify(transactionRepository).save(Mockito.<Transaction>any());
    }

    /**
     * Method under test: {@link TransactionServiceImpl#makePayment(PaymentRequestDto)}
     */
    @Test
    void testMakePayment() {
        CustomUser owner = new CustomUser();
        owner.setAccount(new Account());
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

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(owner);
        account.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account);
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner2);
        account2.setStatus(Status.ACTIVE);

        CustomUser customUser = new CustomUser();
        customUser.setAccount(account2);
        customUser.setAddress("42 Main St");
        customUser.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        customUser.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customUser.setEmail("jane.doe@example.org");
        customUser.setFirstName("Jane");
        customUser.setFriendsList(new ArrayList<>());
        customUser.setId(1L);
        customUser.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        customUser.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        customUser.setLastName("Doe");
        customUser.setPassword("iloveyou");
        customUser.setPhone("6625550144");
        customUser.setUsername("janedoe");

        Account account3 = new Account();
        account3.setAccountNumber("42");
        account3.setBalance(10.0d);
        account3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account3.setCurrency(Currency.Euro);
        account3.setId(1L);
        account3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account3.setOwner(new CustomUser());
        account3.setStatus(Status.ACTIVE);

        CustomUser owner3 = new CustomUser();
        owner3.setAccount(account3);
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

        CustomUser customUser2 = new CustomUser();
        customUser2.setAccount(account4);
        customUser2.setAddress("42 Main St");
        customUser2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        customUser2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        customUser2.setEmail("jane.doe@example.org");
        customUser2.setFirstName("Jane");
        customUser2.setFriendsList(new ArrayList<>());
        customUser2.setId(1L);
        customUser2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        customUser2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        customUser2.setLastName("Doe");
        customUser2.setPassword("iloveyou");
        customUser2.setPhone("6625550144");
        customUser2.setUsername("janedoe");
        Optional<CustomUser> ofResult = Optional.of(customUser2);
        when(customUserService.findByEmail(Mockito.<String>any())).thenReturn(customUser);
        when(customUserService.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertNull(transactionServiceImpl.makePayment(new PaymentRequestDto()));
        verify(customUserService).findByEmail(Mockito.<String>any());
        verify(customUserService).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link TransactionServiceImpl#getAllTransactionsByAccount(Account)}
     */
    @Test
    void testGetAllTransactionsByAccount() {
        when(transactionRepository.findAllBySourceOrDestinationOrderByCreatedAtDesc(Mockito.<Account>any(),
                Mockito.<Account>any())).thenReturn(new ArrayList<>());

        CustomUser owner = new CustomUser();
        owner.setAccount(new Account());
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

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(owner);
        account.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account);
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner2);
        account2.setStatus(Status.ACTIVE);
        assertTrue(transactionServiceImpl.getAllTransactionsByAccount(account2).isEmpty());
        verify(transactionRepository).findAllBySourceOrDestinationOrderByCreatedAtDesc(Mockito.<Account>any(),
                Mockito.<Account>any());
    }

    /**
     * Method under test: {@link TransactionServiceImpl#getPageTransactionsByAccount(Account, Pageable)}
     */
    @Test
    void testGetPageTransactionsByAccount() {
        when(transactionRepository.findAllBySourceOrDestinationOrderByCreatedAtDesc(Mockito.<Account>any(),
                Mockito.<Account>any(), Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));

        CustomUser owner = new CustomUser();
        owner.setAccount(new Account());
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

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(owner);
        account.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account);
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner2);
        account2.setStatus(Status.ACTIVE);
        assertTrue(transactionServiceImpl.getPageTransactionsByAccount(account2, null).toList().isEmpty());
        verify(transactionRepository).findAllBySourceOrDestinationOrderByCreatedAtDesc(Mockito.<Account>any(),
                Mockito.<Account>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link TransactionServiceImpl#getPageTransactionsByAccount(Account, Pageable)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetPageTransactionsByAccount2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.demos.paymybuddy.service.impl.TransactionServiceImpl.getPageTransactionsByAccount(TransactionServiceImpl.java:106)
        //   See https://diff.blue/R013 to resolve this issue.

        when(transactionRepository.findAllBySourceOrDestinationOrderByCreatedAtDesc(Mockito.<Account>any(),
                Mockito.<Account>any(), Mockito.<Pageable>any())).thenReturn(null);

        CustomUser owner = new CustomUser();
        owner.setAccount(new Account());
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

        Account account = new Account();
        account.setAccountNumber("42");
        account.setBalance(10.0d);
        account.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account.setCurrency(Currency.Euro);
        account.setId(1L);
        account.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account.setOwner(owner);
        account.setStatus(Status.ACTIVE);

        CustomUser owner2 = new CustomUser();
        owner2.setAccount(account);
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

        Account account2 = new Account();
        account2.setAccountNumber("42");
        account2.setBalance(10.0d);
        account2.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account2.setCurrency(Currency.Euro);
        account2.setId(1L);
        account2.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account2.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account2.setOwner(owner2);
        account2.setStatus(Status.ACTIVE);
        transactionServiceImpl.getPageTransactionsByAccount(account2, null);
    }
}

