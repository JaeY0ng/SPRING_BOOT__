package com.example.demo.controller;

import com.example.demo.domain.dto.MemoDto;
import com.example.demo.domain.repository.BookRepository;
import com.example.demo.domain.repository.LendRepository;
import com.example.demo.restController.SimpleRestController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/th")
public class ThymeleafTestController {

    @Autowired
    private SimpleRestController simpleRestController;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LendRepository lendRepository;

    @GetMapping("/test1")
    public void t1(Model model)
    {
        log.info("GET /th/test1...");
        model.addAttribute("name","hong");
        model.addAttribute("memo",new MemoDto(1,"TEXT","WRITER"));
        model.addAttribute("isAuth",true);
        model.addAttribute("list", simpleRestController.t5());

        model.addAttribute("bookList", bookRepository.findAll());
        model.addAttribute("lendList", lendRepository.findLendsByUserAndBook());

    }
    @GetMapping("/param1")
    public void param1(@ModelAttribute MemoDto memoDto){
        log.info("GET /th/param1..." + memoDto);
    }
    @GetMapping("/param2/{id}/{text}/{writer}")
    public void param2(@ModelAttribute MemoDto memoDto){
        log.info("GET /th/param2..." + memoDto);
    }

    @GetMapping("/test2")
    public void test2(){
        log.info("GET /th/test2...");
    }

}
