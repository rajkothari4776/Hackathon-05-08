package com.sunbeam.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PaymentDTO {
    private Long paymentId;
    private Double amount;
    private String paymentType;  // "MEMBERSHIP" or "FINE"
    private String method;       // "Cash", "UPI", etc.
    private LocalDate paymentDate;
    private String receiptNumber;
}
