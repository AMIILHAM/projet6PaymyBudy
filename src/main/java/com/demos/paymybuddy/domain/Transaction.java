package com.demos.paymybuddy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull
    @Column(name = "fee", nullable = false)
    private Double fee;


    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "source_account_id", nullable = false)
    @JsonIgnore
    private Account source;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "destination_account_id", nullable = false)
    @JsonIgnore
    private Account destination;

}
