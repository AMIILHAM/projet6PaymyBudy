package com.demos.paymybuddy.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentFormDto {
    private String destinationAccountEmail;
    private Double amount;
}
