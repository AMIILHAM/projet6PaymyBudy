package com.demos.paymybuddy.dto;

import com.demos.paymybuddy.domain.AbstractAuditingEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TransactionDto extends AbstractAuditingEntity {

    private Long id;

    private Double amount;

    private Double fee;

    private String destination;

    private String source;

}
