package com.example.demo.controller;

import com.example.demo.domain.dto.PersonDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/param")
public class ParameterTestController {



	@RequestMapping(value="/p01",method=RequestMethod.GET)
	public void p01(@RequestParam(value="name", required=false) String name) {
		log.info("GET /param/p01..." + name);
	}
	
	@GetMapping("/p02")
	public void p02(@RequestParam(value="name", required=true) String name) {
		log.info("GET /param/p02..." + name);
	}
	
	@PostMapping(value="/p03")
	public void p03(@RequestParam(value="name", required=true) String name) {
		log.info("POST /param/p03..." + name);
	}
	
	// @RequestParam	: 동기요청 파라미터 처리 / form 기반 전달되는 파라미터 받기 
	// @RequestBody		: 비동기요청 파라미터 처리 / form / json 등 파라미터 받기 
	@PostMapping(value="/p04")
	public void p04(@RequestBody String name) {
		log.info("POST /param/p04..." + name);
	}
	
	@RequestMapping(value="/p05",method=RequestMethod.GET)
	public void p05(@RequestParam(value="name",defaultValue="홍길동") String name) {
		log.info("GET /param/p05..." + name);	
	}
	
	@RequestMapping(value="/p06",method=RequestMethod.GET)
	public void p06( 
					@RequestParam(value="name") 	String name,
					@RequestParam(value="age") 		int age , 
					@RequestParam(value="addr")  	String addr
					) 
	{
		log.info("GET /param/p06..." + name + " " + age + " " + addr);	
	}
	
	
	@RequestMapping(value="/p07",method=RequestMethod.GET)
	public void p07( @ModelAttribute PersonDto dto)
	{
		log.info("GET /param/p07..." + dto);	
	}
	
	@RequestMapping(value="/p08/{name}/{age}/{addr}",method=RequestMethod.GET)
	public void p08(
				@PathVariable("name") String name,
				@PathVariable int age,
				@PathVariable String addr
			) 
	{
		log.info("GET /param/p08..." + name+" " + age + " " + addr);	
	}
	
	@RequestMapping(value="/p09/{name}/{age}/{addr}",method=RequestMethod.GET)
	public void p09(PersonDto dto) 
	{
		log.info("GET /param/p09..." + dto);	
	}
	
	@GetMapping("/page1")
	public void page1(PersonDto dto, Model model) {
		log.info("GET /param/page1..." + dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value");
		// Forwarding 위치 : /WEB-INF/views/param/page1.jsp 
		
	}
	@GetMapping("/page2")
	public String page2(PersonDto dto,Model model) {
		log.info("GET /param/page2..." + dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value_2");
		// Forwarding 위치 : /WEB-INF/views/param/page1.jsp 
		return "param/page1";
	}
	@GetMapping("/page3/{name}/{age}/{addr}")
	public String page3(PersonDto dto,Model model) {
		log.info("GET /param/page3..." + dto);
		model.addAttribute("dto",dto);
		model.addAttribute("test","test_value_3");
		// Forwarding 위치 : /WEB-INF/views/param/page1.jsp 
		return "param/page1";
	}
	
	@GetMapping("/page4/{name}/{age}/{addr}")
	public ModelAndView page4(PersonDto dto) {
		log.info("GET /param/page4..." + dto);
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("dto",dto);
		modelAndView.addObject("test","test_value_4");
		modelAndView.setViewName("param/page1");
		// Forwarding 위치 : /WEB-INF/views/param/page1.jsp 
		return modelAndView;
	}
	
	
	//Setvlet
	@GetMapping("/page5")
	public String page5(HttpServletRequest req, HttpServletResponse resp) {
		log.info("GET /param/page5...");
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		log.info(name+" "  + age);
		PersonDto dto = new PersonDto(name,0,null);
		req.setAttribute("dto", dto);
		
		HttpSession session = req.getSession();
		log.info("session : "  + session);
		return "param/page1";
	}
	
	//Forward
	@GetMapping("/forward1")
	public String f1(Model model) {
		log.info("param/forward1..");
		model.addAttribute("f1","f1Value");
		return "forward:/param/forward2";
	}
	//Forward
	@GetMapping("/forward2")
	public String f2(Model model) {
		log.info("/param/forward2..");
		model.addAttribute("f2","f2Value");
		return "forward:/param/forward3";
	}	
	//Forward
	@GetMapping("/forward3")
	public String f3(Model model) {
		log.info("/param/forward3..");
		model.addAttribute("f3","f3Value");
		return "param/forward_result";
	}	
		
	
	//Redirect
	@GetMapping("/redirect1")
	public String r1(Model model, RedirectAttributes redirectAttributes) {
		log.info("/param/redirect1..");
		//model.addAttribute("r1","r1Value");			//쿼리스트링으로 속성전달(파라미터)
		redirectAttributes.addAttribute("r1","r1Value");//쿼리스트링으로 속성전달(파라미터)
		redirectAttributes.addFlashAttribute("r1_flush","r1_flush_value");//세션에 저장(1회)
		return "redirect:/param/redirect2";
	}	
	@GetMapping("/redirect2")
	public String r2(String r1, @ModelAttribute("r1_flush") String r1_flush, RedirectAttributes redirectAttributes) {
		log.info("/param/redirect2..r1_flush : " + r1_flush);
		//model.addAttribute("r1","r1Value");			//쿼리스트링으로 속성전달(파라미터)
		redirectAttributes.addAttribute("r1",r1);//쿼리스트링으로 속성전달(파라미터)
		redirectAttributes.addAttribute("r2","r2Value");//쿼리스트링으로 속성전달(파라미터)
		
		redirectAttributes.addFlashAttribute("r1_flush",r1_flush);//세션에 저장
		redirectAttributes.addFlashAttribute("r2_flush","r2_flush_value");//세션에 저장
		return "redirect:/param/redirect_result";
	}	
	
	@GetMapping("/redirect_result")
	public void r_result(String r1, Model model) {
		log.info("/param/redirect_result..r1 :" + r1);
		
	}	
	
}












