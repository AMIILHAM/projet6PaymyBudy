package com.demos.paymybuddy.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class PaymentRequestDto {
    private Long sourceAccountId;
    private String destinationAccountEmail;
    private Double amount;
    private Double fee;
}
