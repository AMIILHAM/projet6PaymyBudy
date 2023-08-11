package com.demos.paymybuddy.web;

import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.PaymentFormDto;
import com.demos.paymybuddy.dto.PaymentRequestDto;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;
    private final CustomUserService userService;


    @PostMapping("/payFriend")
    public String makePayment(@ModelAttribute("paymentForm") PaymentFormDto paymentFormDto, HttpSession session, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        CustomUserDto connectedUser = this.userService.getCustomUserDtoByEmail(session.getAttribute("username").toString());

        PaymentRequestDto paymentRequest = new PaymentRequestDto();

        paymentRequest.setDestinationAccountEmail(paymentFormDto.getDestinationAccountEmail());
        paymentRequest.setSourceAccountId(connectedUser.getId());
        paymentRequest.setAmount(paymentFormDto.getAmount());

        this.transactionService.makePayment(paymentRequest);

        return "redirect:/home";
    }

}
