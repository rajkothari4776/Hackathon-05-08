package com.sunbeam.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	    private String name;
	    private String email;
	    private String phone;
	    private String address;
	    private LocalDate membershipDate;
	}



