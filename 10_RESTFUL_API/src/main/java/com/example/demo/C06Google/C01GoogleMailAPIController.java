package com.example.demo.C06Google;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/google")
public class C01GoogleMailAPIController {

    @Autowired
    private JavaMailSender javaMailSender;



    @GetMapping("/mail/req")
    @ResponseBody
    public void req(@RequestParam("email") String email, @RequestParam("text") String text) {
        log.info("GET /google/mail/req... email : " + email + ", text : " + text);
        log.info("javaMailSender : " + javaMailSender);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jyhhmj38@gmail.com"); // Replace with your email address.
        message.setTo(email);
        message.setSubject("메일메일");
        message.setText(text);

        javaMailSender.send(message);
    }
}
