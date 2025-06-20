package com.example.green_bank.questions.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(value={AuditingEntityListener.class})
@Getter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "regdate", updatable = false)
    private LocalDateTime createdAt;

    @CreatedDate
    @Column(name = "moddate")
    private LocalDateTime modifiedAt;
}
