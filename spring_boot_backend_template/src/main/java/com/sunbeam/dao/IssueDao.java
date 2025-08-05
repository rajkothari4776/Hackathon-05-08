package com.sunbeam.dao;

import com.sunbeam.entity.IssueRecord;
import com.sunbeam.entity.IssueStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueDao extends JpaRepository<IssueRecord, Long> {
    List<IssueRecord> findByStatus(IssueStatus status);
    List<IssueRecord> findByMemberId(Long memberId);
    List<IssueRecord> findByBookCopyBookId(Long bookId);
    boolean existsByCopyIdAndStatus(Long copyId, IssueStatus status);

}
