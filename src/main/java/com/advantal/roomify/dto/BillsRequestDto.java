package com.advantal.roomify.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillsRequestDto {

    private Integer bookingId;
    private Integer roomCharges;
    private Integer numberOfDays;
}