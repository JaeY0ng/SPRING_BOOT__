package com.example.demo.domain.dto;


import com.example.demo.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer userId;
    private String username;
    private String password;
    private String role;
    private LocalDate birthDate;
    private boolean lended;

    public static User dtoToEntity(UserDto userDto){
        return User.builder()
                .userId(Long.valueOf(userDto.getUserId()))
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .birthDate(userDto.getBirthDate())
                .build();
    }
    public static UserDto entityToDto(User user){
        return UserDto.builder()
                .userId(Math.toIntExact((user.getUserId())))
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .birthDate(user.getBirthDate())
                .build();
    }
}
