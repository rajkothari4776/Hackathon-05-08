package com.sunbeam.service;

public interface PaymentService {
    PaymentDTO makePayment(Long memberId, PaymentDTO dto);
    List<PaymentDTO> getPaymentsByMember(Long memberId);
    List<PaymentDTO> filterPayments(LocalDate from, LocalDate to, String type);
}
