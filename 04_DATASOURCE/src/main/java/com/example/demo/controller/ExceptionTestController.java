package com.example.demo.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/exTest")
public class ExceptionTestController {
	
//	@ExceptionHandler(FileNotFoundException.class)
//	public String fileNotFoundExceptionHandler(Exception e, Model model) {
//		log.info("error : " + e);
//		model.addAttribute("ex",e);
//
//		return "exTest/error";
//	}
//	@ExceptionHandler(ArithmeticException.class)
//	public String ArithmeticExceptionExceptionHandler(Exception e, Model model) {
//		log.info("error : " + e);
//		model.addAttribute("ex",e);
//
//		return "exTest/error";
//	}


//	@ExceptionHandler(Exception.class)
//	public String AllExceptionExceptionHandler(Exception e, Model model) {
//		log.info("error : " + e);
//		model.addAttribute("ex",e);
//
//		return "exTest/error";
//	}
	
	
	@GetMapping("/ex1")
	public void ex1() throws FileNotFoundException{
		log.info("GET /exTest/ex1");
		throw new FileNotFoundException("파일이 존재하지 않습니다...");
	}
	
	@GetMapping("/ex2/{num}/{div}")
	public String ex2(
				@PathVariable("num") int num,
				@PathVariable("div") int div,
				Model model
			) throws ArithmeticException{
	
		log.info("GET /ex2....num,div.."+num +" " + div);
		model.addAttribute("result", num/div);
	
		
		
		return "exTest/ex2";
	}
	
	

}












