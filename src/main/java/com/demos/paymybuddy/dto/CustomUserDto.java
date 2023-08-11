package com.demos.paymybuddy.dto;

import com.demos.paymybuddy.domain.Account;
import com.demos.paymybuddy.domain.BankAccount;
import com.demos.paymybuddy.domain.CustomUser;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;
    private String username;
    private String password;
    @JsonIgnore
    private Account account;
    @JsonIgnore
    private BankAccount bankAccount;
    @JsonIgnore
    private List<CustomUser> friendsList;
}
