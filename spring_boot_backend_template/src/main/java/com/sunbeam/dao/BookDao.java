package com.sunbeam.dao;

import com.sunbeam.entity.Book;
import com.sunbeam.entity.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {
    List<BookCopy> findByBookId(Long bookId);
    boolean existsByCopyIdAndStatus(Long copyId, String status);
}
