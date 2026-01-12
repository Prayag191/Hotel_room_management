package com.advantal.roomify.dto;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BillsResponseDto {

    private Integer id;
    private Integer roomCharges;
    private Integer numberOfDays;
    private Integer totalAmount;
    private LocalDateTime generatedAt;
}
