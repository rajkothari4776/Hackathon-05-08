package com.sunbeam.service;

import com.sunbeam.DTO.BookDTO;
import com.sunbeam.dao.BookDao;
import com.sunbeam.entity.Book;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final ModelMapper mapper;

    @Override
    public BookDTO addBook(BookDTO bookDto) {
        Book book = mapper.map(bookDto, Book.class);
        return mapper.map(bookDao.save(book), BookDTO.class);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDto) {
        Book book = bookDao.findById(bookId).orElseThrow(() ->
                new RuntimeException("Book not found with ID: " + bookId));

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPublisher(bookDto.getPublisher());
        book.setSubject(bookDto.getSubject());
        book.setPrice(bookDto.getPrice());
        book.setDescription(bookDto.getDescription());

        return mapper.map(bookDao.save(book), BookDTO.class);
    }

    @Override
    public void deleteBook(Long bookId) {
        if (!bookDao.existsById(bookId)) {
            throw new RuntimeException("Book not found with ID: " + bookId);
        }
        bookDao.deleteById(bookId);
    }

    @Override
    public BookDTO getBookById(Long bookId) {
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
        return mapper.map(book, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookDao.findAll().stream()
                .map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BookDTO> searchBooks(String keyword) {
        return bookDao.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword).stream()
                .map(book -> mapper.map(book, BookDTO.class))
                .collect(Collectors.toList());
    }
}
