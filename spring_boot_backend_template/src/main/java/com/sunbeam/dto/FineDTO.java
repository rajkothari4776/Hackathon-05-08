package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FineDTO {
    private Long memberId;
    private Double amount;
    private String reason;
    private boolean isPaid;
}

