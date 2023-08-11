package com.demos.paymybuddy.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.UpdateUserRequestDto;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import com.demos.paymybuddy.mapper.CustomUserMapper;
import com.demos.paymybuddy.repository.CustomUserRepository;
import com.demos.paymybuddy.service.AccountService;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserServiceIpml.class})
@ExtendWith(SpringExtension.class)
class CustomUserServiceIpmlTest {
    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomUserMapper customUserMapper;

    @MockBean
    private CustomUserRepository customUserRepository;

    @Autowired
    private CustomUserServiceIpml customUserServiceIpml;

    @MockBean
    private PasswordEncoder passwordEncoder;

    /**
     * Method under test: {@link CustomUserServiceIpml#findById(Long)}
     */
    @Test
    void testFindById() {
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
        Optional<CustomUser> ofResult = Optional.of(customUser);
        when(customUserRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        Optional<CustomUser> actualFindByIdResult = customUserServiceIpml.findById(1L);
        assertSame(ofResult, actualFindByIdResult);
        assertTrue(actualFindByIdResult.isPresent());
        verify(customUserRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#getCustomUserDtoByEmail(String)}
     */
    @Test
    void testGetCustomUserDtoByEmail() {
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
        when(customUserRepository.findByEmail(Mockito.<String>any())).thenReturn(customUser);
        CustomUserDto customUserDto = new CustomUserDto();
        when(customUserMapper.customUserToCustomUserDto(Mockito.<CustomUser>any())).thenReturn(customUserDto);
        assertSame(customUserDto, customUserServiceIpml.getCustomUserDtoByEmail("jane.doe@example.org"));
        verify(customUserRepository).findByEmail(Mockito.<String>any());
        verify(customUserMapper).customUserToCustomUserDto(Mockito.<CustomUser>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#getConnectedUser(HttpSession)}
     */
    @Test
    void testGetConnectedUser() {
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
        when(customUserRepository.findByEmail(Mockito.<String>any())).thenReturn(customUser);
        CustomUserDto customUserDto = new CustomUserDto();
        when(customUserMapper.customUserToCustomUserDto(Mockito.<CustomUser>any())).thenReturn(customUserDto);
        assertSame(customUserDto, customUserServiceIpml.getConnectedUser(new MockHttpSession()));
        verify(customUserRepository).findByEmail(Mockito.<String>any());
        verify(customUserMapper).customUserToCustomUserDto(Mockito.<CustomUser>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#getConnectedUserAsUpdateUserRequestDto(HttpSession)}
     */
    @Test
    void testGetConnectedUserAsUpdateUserRequestDto() {
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
        when(customUserRepository.findByEmail(Mockito.<String>any())).thenReturn(customUser);
        when(customUserMapper.customUserToCustomUserDto(Mockito.<CustomUser>any())).thenReturn(new CustomUserDto());
        UpdateUserRequestDto updateUserRequestDto = new UpdateUserRequestDto("Jane", "Doe", "jane.doe@example.org",
                "42 Main St", "6625550144", "janedoe", "iloveyou", "iloveyou");

        when(customUserMapper.customUserDtoToUpdateUserRequestDto(Mockito.<CustomUserDto>any()))
                .thenReturn(updateUserRequestDto);
        assertSame(updateUserRequestDto,
                customUserServiceIpml.getConnectedUserAsUpdateUserRequestDto(new MockHttpSession()));
        verify(customUserRepository).findByEmail(Mockito.<String>any());
        verify(customUserMapper).customUserToCustomUserDto(Mockito.<CustomUser>any());
        verify(customUserMapper).customUserDtoToUpdateUserRequestDto(Mockito.<CustomUserDto>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#findByEmail(String)}
     */
    @Test
    void testFindByEmail() {
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
        when(customUserRepository.findByEmail(Mockito.<String>any())).thenReturn(customUser);
        assertSame(customUser, customUserServiceIpml.findByEmail("jane.doe@example.org"));
        verify(customUserRepository).findByEmail(Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#save(CustomUserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSave() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException
        //       at com.demos.paymybuddy.service.impl.CustomUserServiceIpml.save(CustomUserServiceIpml.java:53)
        //   See https://diff.blue/R013 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        CustomUserDto userDto = null;

        // Act
        this.customUserServiceIpml.save(userDto);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#update(CustomUserDto, UpdateUserRequestDto)}
     */
    @Test
    void testUpdate() {
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
        when(customUserRepository.save(Mockito.<CustomUser>any())).thenReturn(customUser);

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

        Account account3 = new Account();
        account3.setAccountNumber("42");
        account3.setBalance(10.0d);
        account3.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account3.setCurrency(Currency.Euro);
        account3.setId(1L);
        account3.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account3.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account3.setOwner(owner3);
        account3.setStatus(Status.ACTIVE);

        CustomUser owner4 = new CustomUser();
        owner4.setAccount(account3);
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

        Account account4 = new Account();
        account4.setAccountNumber("42");
        account4.setBalance(10.0d);
        account4.setCreatedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        account4.setCurrency(Currency.Euro);
        account4.setId(1L);
        account4.setLastModifiedAt(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        account4.setLastModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        account4.setOwner(owner4);
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
        when(customUserMapper.customUserDtoToCustomUser(Mockito.<CustomUserDto>any())).thenReturn(customUser2);
        CustomUserDto customUserDto = new CustomUserDto();
        when(customUserMapper.updateUserRequestDtoToCustomUser(Mockito.<UpdateUserRequestDto>any()))
                .thenReturn(customUserDto);
        CustomUserDto userDto = new CustomUserDto();
        CustomUserDto actualUpdateResult = customUserServiceIpml.update(userDto, new UpdateUserRequestDto("Jane", "Doe",
                "jane.doe@example.org", "42 Main St", "6625550144", "janedoe", "iloveyou", "iloveyou"));
        assertSame(customUserDto, actualUpdateResult);
        assertNull(actualUpdateResult.getAccount());
        assertEquals("iloveyou", actualUpdateResult.getPassword());
        assertNull(actualUpdateResult.getFriendsList());
        verify(customUserRepository).save(Mockito.<CustomUser>any());
        verify(customUserMapper).customUserDtoToCustomUser(Mockito.<CustomUserDto>any());
        verify(customUserMapper).updateUserRequestDtoToCustomUser(Mockito.<UpdateUserRequestDto>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#encodePassword(String)}
     */
    @Test
    void testEncodePassword() {
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        assertEquals("secret", customUserServiceIpml.encodePassword("iloveyou"));
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#isPasswordValid(String, String)}
     */
    @Test
    void testIsPasswordValid() {
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(true);
        assertTrue(customUserServiceIpml.isPasswordValid("iloveyou", "iloveyou"));
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }

    /**
     * Method under test: {@link CustomUserServiceIpml#isPasswordValid(String, String)}
     */
    @Test
    void testIsPasswordValid2() {
        when(passwordEncoder.matches(Mockito.<CharSequence>any(), Mockito.<String>any())).thenReturn(false);
        assertFalse(customUserServiceIpml.isPasswordValid("iloveyou", "iloveyou"));
        verify(passwordEncoder).matches(Mockito.<CharSequence>any(), Mockito.<String>any());
    }
}

