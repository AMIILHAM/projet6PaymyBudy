package com.demos.paymybuddy.web;

import com.demos.paymybuddy.dto.CustomUserDto;
import com.demos.paymybuddy.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final CustomUserService userService;

    @GetMapping("/contact")
    public String contactPage(HttpSession session, Model model) {
        CustomUserDto userDto = this.userService.getConnectedUser(session);

        model.addAttribute("pageName", "Contact");
        model.addAttribute("user", userDto);
        model.addAttribute("connectionList", userDto.getFriendsList());

        return "contact";
    }

    @PostMapping("/contact/add")
    public String addConnexion(@RequestParam String email,HttpSession session){
        CustomUserDto userDto = this.userService.getConnectedUser(session);
        this.userService.addFriend(userDto.getId(), email);
        return "redirect:/contact";
    }

    @DeleteMapping("/contact/deleteContact/{idFriend}")
    public String deleteContact(@PathVariable Long idFriend,HttpSession session){
        CustomUserDto userDto = this.userService.getConnectedUser(session);
        this.userService.deleteFriend(userDto.getId(),idFriend);
        return "redirect:/contact";
    }
}
