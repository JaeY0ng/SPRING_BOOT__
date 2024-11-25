package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter
//@Setter
//@ToString
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {
	
	private String name;
	private int age;
	private String addr;
	
	//toString
	//getter and setter
	//생성w

}
