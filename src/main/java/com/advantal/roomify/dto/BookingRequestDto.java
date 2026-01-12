package com.advantal.roomify.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequestDto {

    private Integer guestId;
    private Integer roomId;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}