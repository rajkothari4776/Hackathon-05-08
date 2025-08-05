package com.sunbeam.service;

import com.sunbeam.DTO.BookDTO;
import java.util.List;

public interface BookService {
    BookDTO addBook(BookDTO bookDto);
    BookDTO updateBook(Long bookId, BookDTO bookDto);
    void deleteBook(Long bookId);
    BookDTO getBookById(Long bookId);
    List<BookDTO> getAllBooks();
    List<BookDTO> searchBooks(String keyword); // Optional: for search by title/author
}
