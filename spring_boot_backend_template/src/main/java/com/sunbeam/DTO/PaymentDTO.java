package com.sunbeam.DTO;

import com.sunbeam.entity.PaymentType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter

public class PaymentDTO {
    private Long paymentId;
    private BigDecimal amount;
    private PaymentType paymentType;  // "MEMBERSHIP" or "FINE"
    private String method;       // "Cash", "UPI", etc.
    private LocalDate paymentDate;
    private String receiptNumber;
}
