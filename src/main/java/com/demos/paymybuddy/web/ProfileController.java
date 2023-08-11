package com.demos.paymybuddy.web;

import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.dto.UpdateUserRequestDto;
import com.demos.paymybuddy.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final CustomUserService userService;

    @GetMapping("/")
    public String profilePage(HttpSession session, Model model) {

        model.addAttribute("pageName", "Profile");
        model.addAttribute("user", this.userService.getConnectedUser(session));
        model.addAttribute("updateUserRequestDto", this.userService.getConnectedUserAsUpdateUserRequestDto(session));

        return "profile";
    }


    @PostMapping("/update")
    public String updateProfile(@Valid @ModelAttribute("updateUserRequestDto") UpdateUserRequestDto updateUserRequestDto, HttpSession session) {
        CustomUserDto connectedUserDto = this.userService.getConnectedUser(session);
        String oldPassword = this.userService.findByEmail(connectedUserDto.getEmail()).getPassword();
        if (this.userService.isPasswordValid(oldPassword, this.userService.encodePassword(updateUserRequestDto.getOldPassword()))) {
            log.info("== Old Password Valid==");
            this.userService.update(connectedUserDto,updateUserRequestDto);
            return "redirect:/home";
        }else
            return "redirect:/profile/";
    }
}
