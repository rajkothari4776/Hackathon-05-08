package com.sunbeam.DTO;

import com.sunbeam.entity.Condition;
import com.sunbeam.entity.CopyStatus;
import com.sunbeam.entity.IssueStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class BookCopyDTO {
    private Long copyId;
    private Long bookId;        // Reference to Book
    private Long rackId;        // Reference to Rack

    private Condition condition;   // e.g., GOOD, DAMAGED
    private CopyStatus status;      // e.g., AVAILABLE, ISSUED
    private String shelfPosition;

    private String notes;
    private LocalDate addedDate;
}