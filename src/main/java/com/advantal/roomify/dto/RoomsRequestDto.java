package com.advantal.roomify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomsRequestDto {

    private String roomNumber;
    private String roomType;
    private Integer pricePerDay;
}
