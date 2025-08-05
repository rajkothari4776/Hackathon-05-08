package com.sunbeam.dto;

import java.util.List;

import com.sunbeam.entity.IssueRecord;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberDetailsDTO {
	private MemberDTO member;
	private List<IssueRecord> currentIssues;
	private List<FineDTO> pendingFines;
	private List<ReservationDTO> reservations;
}
