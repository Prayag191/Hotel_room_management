package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime checkInDate;

    private LocalDateTime checkOutDate;

    private Integer status = 1; // active / cancelled / deleted

    private LocalDateTime createdAt = LocalDateTime.now();
}
