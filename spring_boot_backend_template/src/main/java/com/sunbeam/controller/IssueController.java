package com.sunbeam.controller;

import com.sunbeam.DTO.IssueRecordDTO;
import com.sunbeam.DTO.IssueRequestDTO;
import com.sunbeam.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/issue")
    public ResponseEntity<?> issueBook(@RequestBody IssueRequestDTO dto) {
        return ResponseEntity.ok(issueService.issueBook(dto));
    }

    @PostMapping("/return/{issueId}")
    public ResponseEntity<IssueRecordDTO> returnBook(@PathVariable Long issueId) {
        return ResponseEntity.ok(issueService.returnBook(issueId));
    }

    @GetMapping("/issued")
    public ResponseEntity<List<IssueRecordDTO>> getCurrentlyIssuedBooks() {
        return ResponseEntity.ok(issueService.getCurrentlyIssuedBooks());
    }

    @GetMapping("/history/member/{memberId}")
    public ResponseEntity<List<IssueRecordDTO>> getHistoryByMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(issueService.getHistoryByMember(memberId));
    }

    @GetMapping("/history/book/{bookId}")
    public ResponseEntity<List<IssueRecordDTO>> getHistoryByBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(issueService.getHistoryByBook(bookId));
    }
}
