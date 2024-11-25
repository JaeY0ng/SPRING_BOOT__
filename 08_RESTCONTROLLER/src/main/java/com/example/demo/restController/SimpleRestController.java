package com.example.demo.restController;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.dto.MemoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/rest")
@Slf4j
public class SimpleRestController {
	
	
	@GetMapping(value="/getText",produces = MediaType.TEXT_PLAIN_VALUE)
	public String t1() {
		log.info("GET /rest/getText...");
		return "HELLOWORLD";
	}
	@GetMapping(value="/getJson",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public MemoDto t2() {
		log.info("GET /rest/getJson...");
		return new MemoDto(20,"MEMO","ex@ex.com");
	}
	@GetMapping(value="/getXml",produces = MediaType.APPLICATION_XML_VALUE)
	public MemoDto t3() {
		log.info("GET /rest/getXml...");
		return new MemoDto(20,"MEMO","ex@ex.com");
	}
	
	@GetMapping(value="/getXmlList",produces = MediaType.APPLICATION_XML_VALUE)
	public List<MemoDto> t4() {
		log.info("GET /rest/getXmlList...");
		
		List<MemoDto> list = new ArrayList();
		for(int i=1;i<=40;i++) {
			list.add(new MemoDto(i,"memo"+i,"exam"+i));
		}
		
		return list;
	}
	
	@GetMapping(value="/getJsonList",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> t5() {
		log.info("GET /rest/getJsonList...");
		
		List<MemoDto> list = new ArrayList();
		for(int i=1;i<=40;i++) {
			list.add(new MemoDto(i,"memo"+i,"exam"+i));
		}
		
		return list;
	}
	
	@GetMapping(value="/getJsonList/{start}/{end}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<MemoDto> t6(
				@PathVariable int start,
				@PathVariable int end
			) {
		log.info("GET /rest/getJsonList...");
		
		List<MemoDto> list = new ArrayList();
		for(int i=start;i<=end;i++) {
			list.add(new MemoDto(i,"memo"+i,"exam"+i));
		}
		
		return list;
	}
	
	@GetMapping(value="/getJsonEntity/{show}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<MemoDto> t7( @PathVariable boolean show) {
		log.info("GET /rest/getJsonEntity...");
		if(!show)
			return  new ResponseEntity(null,HttpStatus.NOT_FOUND);
		
		return new ResponseEntity(new MemoDto(20,"MEMO","ex@ex.com"),HttpStatus.OK);
	}
	
	
}



