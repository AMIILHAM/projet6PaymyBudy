package com.demos.paymybuddy.dto;

import com.demos.paymybuddy.domain.AbstractAuditingEntity;
import com.demos.paymybuddy.enums.BankTransferOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor @NoArgsConstructor
public class BankTransferDto extends AbstractAuditingEntity {

    private Long id;

    private Double amount;

    private BankTransferOperation operation;

}
