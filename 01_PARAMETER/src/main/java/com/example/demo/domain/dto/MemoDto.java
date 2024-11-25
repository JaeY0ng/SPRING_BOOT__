package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemoDto {
	private int id;			//메모ID
	private String text;	//메모내용
	private String writer;	//작성자
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
	private LocalDateTime createAt; 
}
