package com.advantal.roomify.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomsRequestDto {

    private Integer roomNumber;
    private Integer pricePerDay;
    private String roomType;
}

