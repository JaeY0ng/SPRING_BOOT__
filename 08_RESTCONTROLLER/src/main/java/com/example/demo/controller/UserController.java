package com.example.demo.controller;

import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/join")
    public void join(){
        log.info("GET /join");
    }
    @PostMapping("/join")
    public void join_post(UserDto userDto) throws Exception {
        log.info("POST /join");
        userServiceImpl.memberJoin(userDto);
    }
}
