package com.sunbeam.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MemberDTO {

    public class MemberDTO {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String address;
        private Boolean isActive;
        private Boolean isPaid;
        private LocalDate membershipDate;
    }

}
