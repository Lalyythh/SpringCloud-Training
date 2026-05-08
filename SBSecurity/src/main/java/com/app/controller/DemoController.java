package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {
	
	//http://localhost:8080
	@GetMapping("/")
	public String showHome()
	{
		return "home";
	}
	
	@GetMapping("/info")
	public String showPage1()
	{
		return "page1";
	}
	
	@GetMapping("/sys")
	public String showPage2()
	{
		return "page2";
	}

}
