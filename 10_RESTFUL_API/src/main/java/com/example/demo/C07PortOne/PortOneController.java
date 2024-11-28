package com.example.demo.C07PortOne;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/portOne")
public class PortOneController {

    @GetMapping("/main")
    public void main(){
        log.info("GET /portOne/main...");
    }



}
