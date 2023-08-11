package com.demos.paymybuddy.domain;

import com.demos.paymybuddy.enums.BankTransferOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class BankTransfer extends AbstractAuditingEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    private BankAccount bankAccount;

    @NotNull
    @ManyToOne
    private Account account;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BankTransferOperation operation;

    @NotNull
    private Double amount;
}
