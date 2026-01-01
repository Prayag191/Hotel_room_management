package com.advantal.roomify.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomsResponseDto {

    private Integer id;
    private Integer roomNumber;
    private Integer pricePerDay;
    private String roomType;
    private Integer status;
    private LocalDateTime createdAt;
}
