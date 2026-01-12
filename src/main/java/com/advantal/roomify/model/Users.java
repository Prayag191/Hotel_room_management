
package com.advantal.roomify.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String userName;

    private String passwordHash;

    private String role;

    private Integer status = 1; // soft delete

    private LocalDateTime createdAt = LocalDateTime.now();
}