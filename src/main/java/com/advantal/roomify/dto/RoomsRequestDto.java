package com.advantal.roomify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsRequestDto {

    private Integer roomNumber;
    private String roomType;
    private Integer pricePerDay;
}

