package com.cg.springbootmvcdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	// for root mapping
	@RequestMapping("/")
	public String home()
	{
		return "home";
	}
	@RequestMapping("/greet")
	public String greetUser(@RequestParam("txtuser") String username, Model model) {
		model.addAttribute("username", username);
		return "welcome";	
	}
}
