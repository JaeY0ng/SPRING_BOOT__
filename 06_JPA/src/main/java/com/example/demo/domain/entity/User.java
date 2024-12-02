package com.example.demo.domain.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(length=255,nullable = false)
    private String username;
    @Column(length=255,nullable = false)
    private String password;
    @Column(length=255)
    private String role;
    @Column(name = "birth_date", columnDefinition = "DATE")
    private LocalDate birthDate;
    @Column(nullable = false, updatable = false)
    private boolean lended = false;
}
