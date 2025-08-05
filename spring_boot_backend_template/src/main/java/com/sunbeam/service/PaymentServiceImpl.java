package com.sunbeam.service;

import com.sunbeam.DTO.PaymentDTO;
import com.sunbeam.dao.MemberDao;
import com.sunbeam.dao.PaymentDao;
import com.sunbeam.entity.Member;
import com.sunbeam.entity.Payment;
import com.sunbeam.entity.PaymentType;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;
    @Autowired private MemberDao memberDao;
    @Autowired private ModelMapper mapper;

    @Override
    public PaymentDTO makePayment(Long memberId, PaymentDTO dto) {
        Member member = memberDao.findById(memberId).orElseThrow();

        Payment payment = new Payment();
        payment.setMember(member);
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(LocalDate.now());
        payment.setPaymentType(dto.getPaymentType());
        payment.setMethod(dto.getMethod());

        String receiptNo = generateReceiptNumber();
        payment.setReceiptNumber(receiptNo);

        if (dto.getPaymentType().equals(PaymentType.MEMBERSHIP)) {
            member.setIsPaid(true);
            memberDao.save(member);
        }

        return mapper.map(paymentDao.save(payment), PaymentDTO.class);
    }

    private String generateReceiptNumber() {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = paymentDao.count();
        return "RCP" + date + String.format("%04d", count + 1);
    }

    @Override
    public List<PaymentDTO> getPaymentsByMember(Long memberId) {
        return paymentDao.findByMemberId(memberId).stream()
                .map(p -> mapper.map(p, PaymentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PaymentDTO> filterPayments(LocalDate from, LocalDate to, String type) {
        return paymentDao.findAll().stream()
                .filter(p -> (from == null || !p.getPaymentDate().isBefore(from)) &&
                        (to == null || !p.getPaymentDate().isAfter(to)) &&
                        (type == null || p.getPaymentType().equalsIgnoreCase(type)))
                .map(p -> mapper.map(p, PaymentDTO.class))
                .collect(Collectors.toList());
    }
}
