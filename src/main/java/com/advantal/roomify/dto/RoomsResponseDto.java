
package com.advantal.roomify.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomsResponseDto {

    private Integer id;
    private String roomNumber;
    private String roomType;
    private Integer pricePerDay;
    private Integer status;
    private LocalDateTime createdAt;
}