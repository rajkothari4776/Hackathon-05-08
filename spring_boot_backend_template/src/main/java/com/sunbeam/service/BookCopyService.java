package com.sunbeam.service;

public interface BookCopyService {
    BookCopyDTO addCopy(Long bookId, BookCopyDTO dto);
    List<BookCopyDTO> getCopiesByBook(Long bookId);
    BookCopyDTO updateCopy(Long copyId, BookCopyDTO dto);
    void deleteCopy(Long copyId);
    BookCopyDTO changeStatus(Long copyId, String status);
}
