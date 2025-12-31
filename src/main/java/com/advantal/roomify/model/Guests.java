package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

    private String name;

    private Long mobileNumber;

    private String address;

    private String idProofType;

    private String idProofNumber;

    private Integer status = 1; // soft delete

    private LocalDateTime createdAt = LocalDateTime.now();
}
