
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

    @Column(nullable = false, unique = true)
    private String roomNumber;   // e.g. 101, 202A

    @Column(nullable = false)
    private String roomType;     // DELUXE, AC, NON_AC

    @Column(nullable = false)
    private Integer pricePerDay;

    // 1 = ACTIVE, 2 = INACTIVE, 3 = DELETED
    private Integer status = 1;

    private LocalDateTime createdAt = LocalDateTime.now();
}

