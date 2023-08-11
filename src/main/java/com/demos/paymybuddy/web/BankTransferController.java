package com.demos.paymybuddy.web;

import com.demos.paymybuddy.dto.*;
import com.demos.paymybuddy.enums.BankTransferOperation;
import com.demos.paymybuddy.service.BankTransferService;
import com.demos.paymybuddy.service.CustomUserService;
import com.demos.paymybuddy.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/transfer")
public class BankTransferController {

    private final CustomUserService userService;
    private final BankTransferService bankTransferService;

    @GetMapping("/all")
    public String index(HttpSession session, Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {
        CustomUserDto userDto = this.userService.getConnectedUser(session);
        model.addAttribute("pageName", "Bank Transfer");
        model.addAttribute("user", userDto);
        model.addAttribute("connectionList", userDto.getFriendsList());


        Page<BankTransferDto> bankTransferDtoPage = this.bankTransferService.findAllByAccountId(userDto.getAccount().getId(),PageRequest.of(page - 1, size));

        model.addAttribute("transactions", bankTransferDtoPage.getContent());
        model.addAttribute("currentPage", bankTransferDtoPage.getNumber() + 1);
        model.addAttribute("totalItems", bankTransferDtoPage.getTotalElements());
        model.addAttribute("totalPages", bankTransferDtoPage.getTotalPages());
        model.addAttribute("pageSize", size);

        List<BankTransferOperation> operations = Arrays.asList(BankTransferOperation.DEPOSIT,BankTransferOperation.WITHDRAWAL);
        model.addAttribute("operations", operations);


        BankTransferDto transferRequest = new BankTransferDto();
        model.addAttribute("transferRequest", transferRequest);

        return "withdrawal";
    }

    @PostMapping("/add")
    public String makePayment(@Valid @ModelAttribute("transferRequest") BankTransferDto transferRequest, HttpSession session, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int size) {

        CustomUserDto connectedUser = this.userService.getCustomUserDtoByEmail(session.getAttribute("username").toString());

        BankTransferRequestDto bankTransferRequestDto = new BankTransferRequestDto();

        bankTransferRequestDto.setBankAccountId(connectedUser.getBankAccount().getId());
        bankTransferRequestDto.setAccountId(connectedUser.getAccount().getId());
        bankTransferRequestDto.setAmount(transferRequest.getAmount());
        bankTransferRequestDto.setOperation(transferRequest.getOperation());

        this.bankTransferService.makeTransfer(bankTransferRequestDto);

        return "redirect:/transfer/all";
    }
}
