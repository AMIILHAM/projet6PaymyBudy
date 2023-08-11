package com.demos.paymybuddy.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.mapper.CustomUserMapperImpl;
import com.demos.paymybuddy.repository.AccountRepository;
import com.demos.paymybuddy.repository.CustomUserRepository;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.impl.AccountServiceImpl;
import com.demos.paymybuddy.service.impl.CustomUserServiceIpml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@ContextConfiguration(classes = {LoginController.class})
@ExtendWith(SpringExtension.class)
class LoginControllerTest {
    @MockBean
    private CustomUserService customUserService;

    @Autowired
    private LoginController loginController;

    @Test
    void testLoginForm() {
        CustomUserRepository userRepository = mock(CustomUserRepository.class);
        AccountServiceImpl accountService = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper = new CustomUserMapperImpl();
        assertEquals("login",
                (new LoginController(
                        new CustomUserServiceIpml(userRepository, accountService, userMapper, new Argon2PasswordEncoder())))
                        .loginForm());
    }

    @Test
    void testRegistrationForm() {
        CustomUserRepository userRepository = mock(CustomUserRepository.class);
        AccountServiceImpl accountService = new AccountServiceImpl(mock(AccountRepository.class));
        CustomUserMapperImpl userMapper = new CustomUserMapperImpl();
        LoginController loginController = new LoginController(
                new CustomUserServiceIpml(userRepository, accountService, userMapper, new Argon2PasswordEncoder()));
        assertEquals("registration", loginController.registrationForm(new ConcurrentModel()));
    }

    @Test
    void testLogoutPage() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login?logout=true"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout=true"));
    }

    @Test
    void testLogoutPage2() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/logout");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(loginController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login?logout=true"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login?logout=true"));
    }
}

