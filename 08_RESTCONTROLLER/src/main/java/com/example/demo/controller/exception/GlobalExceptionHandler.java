package com.example.demo.controller.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String NoHandlerFoundExceptionHandler(Exception e, Model model) {
		log.info("NoHandlerFoundExceptionHandler : " + e);
		model.addAttribute("ex",e);
		
		return "globalError";
	}
	
	@ExceptionHandler(Exception.class)
	public String AllExceptionExceptionHandler(Exception e, Model model) {
		log.info("error : " + e);
		model.addAttribute("ex",e);
		
		return "globalError";
	}
}
