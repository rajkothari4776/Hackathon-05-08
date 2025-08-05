package com.sunbeam.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookDTO {
    private Long bookId;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String subject;         // or `category` if you're using that field name
    private String description;
    private BigDecimal price;

    private Integer totalCopies;
    private Integer availableCopies;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

