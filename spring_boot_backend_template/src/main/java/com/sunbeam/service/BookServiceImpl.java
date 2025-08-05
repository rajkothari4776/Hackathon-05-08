package com.sunbeam.service;

import com.sunbeam.dao.BookCopyDao;
import com.sunbeam.dao.BookDao;
import com.sunbeam.dao.IssueDao;
import com.sunbeam.dao.RackDao;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Transactional
public class BookServiceImpl implements BookService{
    private BookDao bookDao;
    private BookCopyDao bookCopyDao;
    private IssueDao issueDao;
    private RackDao rackDao;
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
        copy.setCondition(dto.getCondition());
        copy.setShelfPosition(dto.getShelfPosition());
        Rack rack = rackDao.findById(dto.getRackId()).orElseThrow();
        copy.setRack(rack);
        return mapper.map(bookCopyDao.save(copy), BookCopyDTO.class);
    }

    public void deleteCopy(Long copyId) {
        if (issueDao.existsByCopyIdAndStatus(copyId, "ISSUED")) {
            throw new IllegalStateException("Cannot delete an issued book copy");
        }
        bookCopyDao.deleteById(copyId);
    }

    public BookCopyDTO changeStatus(Long copyId, String status) {
        BookCopy copy = bookCopyDao.findById(copyId).orElseThrow();
        copy.setStatus(BookCopyStatus.valueOf(status));
        return mapper.map(bookCopyDao.save(copy), BookCopyDTO.class);
    }
}
