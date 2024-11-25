package com.example.demo.domain.service;


import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    public boolean memberJoin(UserDto userDto) throws Exception{
        //userDto -> User Entity
        userDto.setRole("ROLE_USER");
        User user = UserDto.dtoToEntity(userDto);
        userRepository.save(user);
        return true;
    }

}
