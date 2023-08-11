package com.demos.paymybuddy.web;

import static org.mockito.Mockito.when;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.TransactionService;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {HomeController.class})
@ExtendWith(SpringExtension.class)
class HomeControllerTest {
    @MockBean
    private CustomUserService customUserService;

    @Autowired
    private HomeController homeController;

    @MockBean
    private TransactionService transactionService;


    @Test
    void testIndex() throws Exception {
        when(customUserService.getConnectedUser(Mockito.<HttpSession>any())).thenReturn(new CustomUserDto());
        when(transactionService.getPageTransactionsByAccount(Mockito.<Account>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/home");
        MockHttpServletRequestBuilder paramResult = getResult.param("page", String.valueOf(1));
        MockHttpServletRequestBuilder requestBuilder = paramResult.param("size", String.valueOf(1));
        MockMvcBuilders.standaloneSetup(homeController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(9))
                .andExpect(MockMvcResultMatchers.model()
                        .attributeExists("currentPage", "pageName", "pageSize", "paymentForm", "totalItems", "totalPages",
                                "transactions", "user"))
                .andExpect(MockMvcResultMatchers.view().name("dashboard"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("dashboard"));
    }
}

