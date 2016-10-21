package edu.mum.lesson1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.lesson1.models.User;
import edu.mum.lesson1.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@RequestMapping(value={"login"},method=RequestMethod.GET)
	public String loginForm(@ModelAttribute("user") User user)
	{		
		return "login";
	}
	@RequestMapping(value={"login"},method=RequestMethod.POST)
	public String loginFormPost(User user, Model model)
	{		
		if( userService.authenticat(user))
		{
			return "redirect:profile";
		}
		else
		{
			model.addAttribute("message","UserName or Password is not correct");
			return "login";
		}
		
	}
	@RequestMapping(value={"register"},method=RequestMethod.GET)
	public String registerForm(@ModelAttribute("user") User user)
	{		
		return "register";
	}
	@RequestMapping(value={"register"},method=RequestMethod.POST)
	public String registerFormPost(User user,RedirectAttributes rda)
	{		
		User userSaved = userService.save(user);
		rda.addFlashAttribute("message","Saved");
		rda.addFlashAttribute("user",userSaved);
		return "redirect:userDetails";
	}
	@RequestMapping(value={"userDetails"},method=RequestMethod.GET)
	public String userDetails()
	{		
		return "userDetails";
	}
}
