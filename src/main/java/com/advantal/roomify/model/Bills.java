package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bills")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roomCharges;

    private Integer numberOfDays;

    private Integer totalAmount;

    private Integer status = 1; // ACTIVE

    private LocalDateTime generatedAt = LocalDateTime.now();
}
