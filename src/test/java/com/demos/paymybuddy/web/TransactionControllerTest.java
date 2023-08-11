package com.demos.paymybuddy.web;

import static org.mockito.Mockito.mock;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.dto.PaymentFormDto;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import com.demos.paymybuddy.mapper.CustomUserMapperImpl;
import com.demos.paymybuddy.mapper.TransactionMapper;
import com.demos.paymybuddy.repository.AccountRepository;
import com.demos.paymybuddy.repository.CustomUserRepository;
import com.demos.paymybuddy.repository.TransactionRepository;
import com.demos.paymybuddy.service.impl.AccountServiceImpl;
import com.demos.paymybuddy.service.impl.CustomUserServiceIpml;
import com.demos.paymybuddy.service.impl.TransactionServiceImpl;

import javax.servlet.http.HttpSession;

import org.apache.catalina.session.PersistentManager;
import org.apache.catalina.session.StandardSession;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;


class TransactionControllerTest {

    @Test
    void testMakePayment() {

        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        TransactionMapper transactionMapper = mock(TransactionMapper.class);
        CustomUserRepository userRepository = mock(CustomUserRepository.class);
        AccountServiceImpl accountService = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper = new CustomUserMapperImpl();
        CustomUserServiceIpml userService = new CustomUserServiceIpml(userRepository, accountService, userMapper,
                new Argon2PasswordEncoder());

        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionRepository, transactionMapper,
                userService, new AccountServiceImpl(mock(AccountRepository.class)));

        CustomUserRepository userRepository2 = mock(CustomUserRepository.class);
        AccountServiceImpl accountService2 = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper2 = new CustomUserMapperImpl();
        TransactionController transactionController = new TransactionController(transactionService,
                new CustomUserServiceIpml(userRepository2, accountService2, userMapper2, new Argon2PasswordEncoder()));

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("username", "nvanbruggen0");


        PaymentFormDto paymentFormDto = new PaymentFormDto();
        paymentFormDto.setAmount(10.0d);
        paymentFormDto.setDestinationAccountEmail("ddzenisenka2@ed.gov");
        transactionController.makePayment(paymentFormDto, session, new ConcurrentModel(), 1, 10);
    }

    @Test
    @Disabled("TODO: Complete this test")
    void testMakePayment2() {
        TransactionRepository transactionRepository = mock(TransactionRepository.class);
        TransactionMapper transactionMapper = mock(TransactionMapper.class);
        CustomUserRepository userRepository = mock(CustomUserRepository.class);
        AccountServiceImpl accountService = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper = new CustomUserMapperImpl();
        CustomUserServiceIpml userService = new CustomUserServiceIpml(userRepository, accountService, userMapper,
                new Argon2PasswordEncoder());

        TransactionServiceImpl transactionService = new TransactionServiceImpl(transactionRepository, transactionMapper,
                userService, new AccountServiceImpl(mock(AccountRepository.class)));

        CustomUserRepository userRepository2 = mock(CustomUserRepository.class);
        AccountServiceImpl accountService2 = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper2 = new CustomUserMapperImpl();
        TransactionController transactionController = new TransactionController(transactionService,
                new CustomUserServiceIpml(userRepository2, accountService2, userMapper2, new Argon2PasswordEncoder()));

        PaymentFormDto paymentFormDto = new PaymentFormDto();
        paymentFormDto.setAmount(10.0d);
        paymentFormDto.setDestinationAccountEmail("3");
        StandardSession session = new StandardSession(new PersistentManager());
        transactionController.makePayment(paymentFormDto, session, new ConcurrentModel(), 1, 3);

    }
}

