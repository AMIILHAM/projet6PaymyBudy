package com.demos.paymybuddy.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractAuditingEntity {
    @CreatedBy
    @JsonIgnore
    @Column(name = "createdBy", length = 50, updatable = false)
    protected String createdBy;

    @CreatedDate
    @JsonIgnore
    @Column(name = "createdAt", updatable = false)
    protected Instant createdAt;

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "lastModifiedBy", length = 50, updatable = false)
    protected String lastModifiedBy;

    @LastModifiedDate
    @JsonIgnore
    @Column(name = "lastModifiedAt", updatable = false)
    protected Instant lastModifiedAt;
}
