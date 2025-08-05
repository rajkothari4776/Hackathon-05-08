package com.sunbeam.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "members")
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String name;
    private String email;
    private String phone;
    private String address;

    private LocalDate membershipDate;
    private boolean isActive;
    private boolean isPaid;

    @OneToMany(mappedBy = "member")
    private List<IssueRecord> issueRecords;

    @OneToMany(mappedBy = "member")
    private List<Payment> payments;
}

