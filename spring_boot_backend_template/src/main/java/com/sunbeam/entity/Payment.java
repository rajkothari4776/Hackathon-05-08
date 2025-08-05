package com.sunbeam.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    private String method;
    private LocalDate paymentDate;
    private String receiptNumber;

    @OneToOne
    @JoinColumn(name = "issue_record_id")
    private IssueRecord issueRecord;
}
