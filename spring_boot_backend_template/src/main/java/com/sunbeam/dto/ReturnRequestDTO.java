package com.sunbeam.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ReturnRequestDTO {
    private Long issueRecordId;
    private LocalDate returnDate;
}
