package com.sunbeam.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class RackDTO {
    private Long rackId;
    private String rackName;
    private String location;
    private Integer capacity;
}
