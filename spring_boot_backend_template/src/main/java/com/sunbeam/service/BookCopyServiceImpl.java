package com.sunbeam.service;

import com.sunbeam.DTO.BookCopyDTO;
import com.sunbeam.dao.BookCopyDao;
import com.sunbeam.dao.BookDao;
import com.sunbeam.dao.IssueDao;
import com.sunbeam.dao.RackDao;
import com.sunbeam.entity.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class BookCopyServiceImpl implements BookCopyService {

    private BookDao bookDao;
    private RackDao rackDao;
    private BookCopyDao bookCopyDao;
    private IssueDao issueDao;
    private ModelMapper mapper;

    public BookCopyDTO addCopy(Long bookId, BookCopyDTO dto) {
        Book book = bookDao.findById(bookId).orElseThrow();
        Rack rack = rackDao.findById(dto.getRackId()).orElseThrow();

        BookCopy copy = mapper.map(dto, BookCopy.class);
        copy.setBook(book);
        copy.setRack(rack);

        return mapper.map(bookCopyDao.save(copy), BookCopyDTO.class);
    }

    public List<BookCopyDTO> getCopiesByBook(Long bookId) {
        return bookCopyDao.findByBookId(bookId).stream()
                .map(copy -> mapper.map(copy, BookCopyDTO.class))
                .collect(Collectors.toList());
    }

    public BookCopyDTO updateCopy(Long copyId, BookCopyDTO dto) {
        BookCopy copy = bookCopyDao.findById(copyId).orElseThrow();
        Rack rack = rackDao.findById(dto.getRackId()).orElseThrow();

        copy.setCondition(dto.getCondition());
        copy.setStatus(dto.getStatus());
        copy.setNotes(dto.getNotes());
        copy.setRack(rack);

        return mapper.map(bookCopyDao.save(copy), BookCopyDTO.class);
    }

    public void deleteCopy(Long copyId) {
        if (issueDao.existsByCopyIdAndStatus(copyId, IssueStatus.ISSUED)) {
            throw new IllegalStateException("Cannot delete an issued book copy");
        }
        bookCopyDao.deleteById(copyId);
    }

    public BookCopyDTO changeStatus(Long copyId, CopyStatus status) {
        BookCopy copy = bookCopyDao.findById(copyId).orElseThrow();
        copy.setStatus(status);
        return mapper.map(bookCopyDao.save(copy), BookCopyDTO.class);
    }

}
