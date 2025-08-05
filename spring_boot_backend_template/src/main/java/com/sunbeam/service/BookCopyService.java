package com.sunbeam.service;

import com.sunbeam.DTO.BookCopyDTO;
import com.sunbeam.entity.CopyStatus;
import com.sunbeam.entity.IssueStatus;

import java.util.List;

public interface BookCopyService {
    BookCopyDTO addCopy(Long bookId, BookCopyDTO dto);
    List<BookCopyDTO> getCopiesByBook(Long bookId);
    BookCopyDTO updateCopy(Long copyId, BookCopyDTO dto);
    void deleteCopy(Long copyId);
    BookCopyDTO changeStatus(Long copyId, CopyStatus status);
}
