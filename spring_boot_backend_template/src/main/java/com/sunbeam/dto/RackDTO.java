package com.sunbeam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RackDTO {
    private String rackName;
    private int capacity;
    private String location;
    private boolean isActive;
}
