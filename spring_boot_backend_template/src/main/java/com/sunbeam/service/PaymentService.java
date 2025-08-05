package com.sunbeam.service;

import com.sunbeam.DTO.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    PaymentDTO makePayment(Long memberId, PaymentDTO dto);
    List<PaymentDTO> getPaymentsByMember(Long memberId);
    List<PaymentDTO> filterPayments(LocalDate from, LocalDate to, String type);
}
