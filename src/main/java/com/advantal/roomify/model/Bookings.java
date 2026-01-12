package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
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

    // Many bookings → One guest
    @ManyToOne
    @JoinColumn(name = "guest_id", nullable = false)
    private Guests guest;

    // Many bookings → One room
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Rooms room;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Integer totalDays;
    private Integer totalAmount;

    private Integer status = 1; // 1=ACTIVE, 2=CANCELLED, 3=DELETED

    private LocalDateTime createdAt = LocalDateTime.now();
}

