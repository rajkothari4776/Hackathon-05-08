package com.sunbeam.dao;

import com.sunbeam.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberDao extends JpaRepository<Member, Long> {
    List<Member> findByIsActive(Boolean active);
    List<Member> findByIsPaid(Boolean paid);
    List<Member> findByIsActiveAndIsPaid(Boolean active, Boolean paid);
}