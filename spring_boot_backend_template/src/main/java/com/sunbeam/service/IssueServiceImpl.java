package com.sunbeam.service;

import com.sunbeam.DTO.IssueRecordDTO;
import com.sunbeam.DTO.IssueRequestDTO;
import com.sunbeam.dao.IssueDao;
import com.sunbeam.dao.MemberDao;
import com.sunbeam.dao.BookCopyDao;
import com.sunbeam.entity.BookCopy;
import com.sunbeam.entity.CopyStatus;
import com.sunbeam.entity.IssueRecord;
import com.sunbeam.entity.IssueStatus;
import com.sunbeam.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {

    private final IssueDao issueDao;
    private final MemberDao memberDao;
    private final BookCopyDao bookCopyDao;
    private final ModelMapper mapper;

    private static final BigDecimal DAILY_FINE = new BigDecimal("2.00");

    @Override
    public IssueRecordDTO issueBook(IssueRequestDTO dto) {
        Member member = memberDao.findById(dto.getMemberId())
                .orElseThrow(() -> new RuntimeException("Member not found with ID: " + dto.getMemberId()));
        BookCopy copy = bookCopyDao.findById(dto.getCopyId())
                .orElseThrow(() -> new RuntimeException("Book copy not found with ID: " + dto.getCopyId()));

        if (!copy.getStatus().equals(CopyStatus.AVAILABLE)) {
            throw new IllegalStateException("Book copy is not available.");
        }

        IssueRecord record = new IssueRecord();
        record.setMember(member);
        record.setCopy(copy);
        record.setIssueDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(14));
        record.setStatus(IssueStatus.ISSUED);
        copy.setStatus(CopyStatus.ISSUED);

        issueDao.save(record);
        bookCopyDao.save(copy);
        return mapper.map(record, IssueRecordDTO.class);
    }

    @Override
    public IssueRecordDTO returnBook(Long issueId) {
        IssueRecord record = issueDao.findById(issueId)
                .orElseThrow(() -> new RuntimeException("Issue record not found with ID: " + issueId));
        BookCopy copy = record.getCopy();

        record.setReturnDate(LocalDate.now());

        if (record.getReturnDate().isAfter(record.getDueDate())) {
            long overdueDays = ChronoUnit.DAYS.between(record.getDueDate(), record.getReturnDate());
            record.setFineAmount(DAILY_FINE.multiply(BigDecimal.valueOf(overdueDays)));
            record.setStatus(IssueStatus.OVERDUE);
        } else {
            record.setFineAmount(BigDecimal.ZERO);
            record.setStatus(IssueStatus.RETURNED);
        }

        copy.setStatus(CopyStatus.AVAILABLE);
        bookCopyDao.save(copy);
        return mapper.map(issueDao.save(record), IssueRecordDTO.class);
    }

    @Override
    public List<IssueRecordDTO> getCurrentlyIssuedBooks() {
        return issueDao.findByStatus(IssueStatus.ISSUED).stream()
                .map(record -> mapper.map(record, IssueRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueRecordDTO> getHistoryByMember(Long memberId) {
        return issueDao.findByMemberId(memberId).stream()
                .map(record -> mapper.map(record, IssueRecordDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<IssueRecordDTO> getHistoryByBook(Long bookId) {
        return issueDao.findByBookCopyBookId(bookId).stream()
                .map(record -> mapper.map(record, IssueRecordDTO.class))
                .collect(Collectors.toList());
    }
}