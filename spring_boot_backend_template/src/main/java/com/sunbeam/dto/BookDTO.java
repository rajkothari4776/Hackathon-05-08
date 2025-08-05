package com.sunbeam.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String subject;
    private BigDecimal price;
    private String description;
}
