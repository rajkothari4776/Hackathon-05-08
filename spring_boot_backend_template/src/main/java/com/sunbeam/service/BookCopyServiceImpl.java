package com.sunbeam.service;


import com.sunbeam.dao.BookCopyDao;
import com.sunbeam.dao.BookDao;
import com.sunbeam.dao.IssueDao;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class BookCopyServiceImpl extends BookCopyService{

    private BookDao bookDao;
    private BookCopyDao bookCopyDao;
    private IssueDao issueDao;
    private ModelMapper mapper;

    public BookCopyDTO addCopy(Long bookId, BookCopyDTO dto) {
        Book book = bookDao.findById(bookId).orElseThrow();
        BookCopy copy = mapper.map(dto, BookCopy.class);
        copy.setBook(book);
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
        copy.setRack(dto.getRack());
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
