package com.example.demo.C04Kakao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/kakao")
public class C03KakaoChannelController {

    @GetMapping("/channel/main")
    public void channel_main(){
        log.info("GET /kakao/channel/main...");
    }
}
