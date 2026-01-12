
package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "guests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Guests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String mobile;

    @Column(nullable = false, unique = true)
    private String email;

    private String address;

    // 1 = ACTIVE, 2 = INACTIVE, 3 = DELETED
    private Integer status = 1;

    private LocalDateTime createdAt = LocalDateTime.now();
    
 // Guests.java
    @OneToMany(mappedBy = "guest", cascade = CascadeType.ALL)
    private List<Bookings> bookings;

}
