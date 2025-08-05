package com.sunbeam.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "books")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private String subject;
    private BigDecimal price;
    private String description;

    private Timestamp createdDate;
    private Timestamp updatedDate;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BookCopy> copies;
}
