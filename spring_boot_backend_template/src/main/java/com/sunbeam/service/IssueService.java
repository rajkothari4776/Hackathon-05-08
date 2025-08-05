package com.sunbeam.service;

import com.sunbeam.DTO.IssueRecordDTO;
import com.sunbeam.DTO.IssueRequestDTO;

import java.util.List;

public interface IssueService {
    IssueRecordDTO issueBook(IssueRequestDTO dto);
    IssueRecordDTO returnBook(Long issueId);
    List<IssueRecordDTO> getCurrentlyIssuedBooks();
    List<IssueRecordDTO> getHistoryByMember(Long memberId);
    List<IssueRecordDTO> getHistoryByBook(Long bookId);
}
