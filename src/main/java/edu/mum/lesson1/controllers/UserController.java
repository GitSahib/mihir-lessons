package edu.mum.lesson1.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.mum.lesson1.models.User;
import edu.mum.lesson1.service.SecurityService;
import edu.mum.lesson1.service.UserService;
import edu.mum.lesson1.service.UserValidator;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	@Autowired SecurityService securityService;
	@Autowired 
	UserValidator userValidator;
	Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value={"login"},method=RequestMethod.GET)
	public String login(@ModelAttribute("userForm") User userForm, BindingResult errors,String error, String logout) {
		
		
		if (error != null)
			errors.rejectValue("password", "errors.username.or.password.is.invalid");			
		if (logout != null)
			errors.rejectValue("password", "message.you.are.loggedout.successfully");
		else 
		{
			if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails)
			{
				return "redirect:/profile";
			}
		}
		return "login";
	}
	@RequestMapping(value={"register"},method=RequestMethod.GET)
	public String registerForm(@ModelAttribute("user") User user)
	{		
		return "register";
	}
	@RequestMapping(value={"register"},method=RequestMethod.POST)
	public String registerFormPost(User user, BindingResult errors,RedirectAttributes rda)
	{		
		userValidator.validate(user, errors);
		if(errors.hasErrors())
		{
			return "register";
		}
		User userSaved = userService.save(user);
		rda.addFlashAttribute("message","Saved");
		rda.addFlashAttribute("user",userSaved);
		log.info("User is registered");
		securityService.autologin(user.getUsername(), user.getPasswordConfirm());
		return "redirect:userDetails";
	}
	@RequestMapping(value={"userDetails"},method=RequestMethod.GET)
	public String userDetails()
	{		
		return "userDetails";
	}
}
