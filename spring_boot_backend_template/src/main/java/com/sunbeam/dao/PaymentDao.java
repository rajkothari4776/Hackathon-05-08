package com.sunbeam.dao;

import com.sunbeam.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment, Long> {
    List<Payment> findByMemberId(Long memberId);
}
