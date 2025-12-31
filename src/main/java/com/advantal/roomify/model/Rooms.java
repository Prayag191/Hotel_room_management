package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer roomNumber;

    private Integer pricePerDay;

    private String roomType;

    private Integer status = 1; // soft delete

    private LocalDateTime createdAt = LocalDateTime.now();
}
