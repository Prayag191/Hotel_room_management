package com.advantal.roomify.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingResponseDto {

    private Integer bookingId;

    private Integer guestId;
    private String guestName;

    private Integer roomId;
    private String roomNumber;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Integer totalDays;
    private Integer totalAmount;

    private Integer status;
    private LocalDateTime createdAt;
}

