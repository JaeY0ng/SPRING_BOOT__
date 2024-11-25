package com.example.demo.domain.dto;


import com.example.demo.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String role;

    public static User dtoToEntity(UserDto userDto){
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .role(userDto.getRole())
                .build();
    }
    public static UserDto entityToDto(User user){
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
