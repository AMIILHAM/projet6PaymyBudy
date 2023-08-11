package com.demos.paymybuddy.dto;

import com.demos.paymybuddy.domain.AbstractAuditingEntity;
import com.demos.paymybuddy.enums.BankTransferOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BankTransferRequestDto extends AbstractAuditingEntity {

    @NotNull
    private Long bankAccountId;
    @NotNull
    private Long accountId;
    @NotNull
    private Double amount;
    @NotNull
    private BankTransferOperation operation;

}
