package edu.mum.lesson1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value={"/","/home"},method=RequestMethod.GET)
	public String home(Model model)
	{
		model.addAttribute("message","Welcome to Stpring boot Lesson1");
		return "home";
	}
	@RequestMapping(value="/profile",method=RequestMethod.GET)
	public String profile(Model model)
	{
		return "profile";
	}
	@RequestMapping(value="/*",method=RequestMethod.GET)
	public String handl_404(Model model)
	{
		return "404";
	}
	
}
