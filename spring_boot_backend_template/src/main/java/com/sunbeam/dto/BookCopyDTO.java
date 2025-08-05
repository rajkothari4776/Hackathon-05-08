package com.sunbeam.dto;

import java.time.LocalDateTime;

public class BookCopyDTO {
    private Long bookId;
    private Long rackId;
    private String condition;  // GOOD, EXCELLENT, DAMAGED, LOST
    private String status;     // AVAILABLE, ISSUED, DAMAGED, LOST
    private LocalDateTime addedDate; // optional
}
