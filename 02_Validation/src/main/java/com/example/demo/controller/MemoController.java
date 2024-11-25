package com.example.demo.controller;

import com.example.demo.domain.dto.MemoDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {

	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		log.info("MemoController's dataBinder..." + webDataBinder);
		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest", new DateTestEditor());
	}

	@Slf4j
	private static class DateTestEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String dateTest) throws IllegalArgumentException {

			//log.info("DateTestEditor's setAsText invoke...." + dateTest);
			if(dateTest.isEmpty()) {
				dateTest = LocalDate.now().toString();
			}else {
				dateTest = dateTest.replaceAll("#", "-");
				//log.info("dateTest #->'-': " + dateTest);
			}
			//dateTest -> "2024-01-01"
			LocalDate date = LocalDate.parse(dateTest, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//log.info("parsed date : " + date);

			setValue(date);

		}

	}

	@GetMapping("/add")
	public void memo_get() {
		///메모추가 페이지 포워딩 핸들러
		log.info("GET /memo/add...");
	}

	@PostMapping("/add")
	public void memo_post(@ModelAttribute @Valid MemoDto memoDto, BindingResult bindingResult, Model model) {
		log.info("POST /memo/post..." + memoDto);

		if(bindingResult.hasErrors()) {
//			log.info("데이터 유효성 체크 오류 : " + bindingResult.getFieldError("id").getDefaultMessage()  );
//			model.addAttribute("id",bindingResult.getFieldError("id").getDefaultMessage());
			for(FieldError error : bindingResult.getFieldErrors()) {
				log.info("Error Field : "+error.getField()+" Error Msg : "+error.getDefaultMessage());
				model.addAttribute(error.getField(),error.getDefaultMessage());
			}

		}

	}


}
