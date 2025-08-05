package com.sunbeam.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookCopyDTO {
    private Long bookId;
    private Long rackId;
    private String condition;  // GOOD, EXCELLENT, DAMAGED, LOST
    private String status;     // AVAILABLE, ISSUED, DAMAGED, LOST
    private LocalDateTime addedDate; // optional
}
