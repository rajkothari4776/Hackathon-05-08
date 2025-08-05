package com.sunbeam.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    private Double price;

    private Integer totalCopies;
    private Integer availableCopies;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

