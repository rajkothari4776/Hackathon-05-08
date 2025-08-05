package com.sunbeam.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueRequestDTO {
    private Long memberId;
    private Long copyId;
    private LocalDate issueDate;
    private LocalDate dueDate;
}

