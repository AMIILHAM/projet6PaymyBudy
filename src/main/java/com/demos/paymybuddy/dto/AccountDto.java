package com.demos.paymybuddy.dto;

import com.demos.paymybuddy.domain.CustomUser;
import com.demos.paymybuddy.enums.Currency;
import com.demos.paymybuddy.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String accountNumber;
    private Double balance;
    private Currency currency;
    private Status status;
    private CustomUser owner;
}
