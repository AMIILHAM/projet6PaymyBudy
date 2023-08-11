package com.demos.paymybuddy.web;

import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.PaymentFormDto;
import com.demos.paymybuddy.dto.TransactionDto;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {

    private final CustomUserService userService;
    private final TransactionService transactionService;

    @GetMapping({"home","home/"})
    public String index(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        CustomUserDto userDto = this.userService.getConnectedUser(session);
        model.addAttribute("pageName", "Home");
        model.addAttribute("user", userDto);
        model.addAttribute("connectionList", userDto.getFriendsList());


        Page<TransactionDto> transactionDtoPage = this.transactionService.getPageTransactionsByAccount(userDto.getAccount(),PageRequest.of(page - 1, size));
        model.addAttribute("transactions", transactionDtoPage.getContent());
        model.addAttribute("currentPage", transactionDtoPage.getNumber() + 1);
        model.addAttribute("totalItems", transactionDtoPage.getTotalElements());
        model.addAttribute("totalPages", transactionDtoPage.getTotalPages());
        model.addAttribute("pageSize", size);

        PaymentFormDto paymentForm = new PaymentFormDto();
        model.addAttribute("paymentForm", paymentForm);

        return "dashboard";
    }
}
