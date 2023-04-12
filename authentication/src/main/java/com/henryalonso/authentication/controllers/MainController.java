package com.henryalonso.authentication.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.henryalonso.authentication.models.LoginUser;
import com.henryalonso.authentication.models.User;
import com.henryalonso.authentication.services.UserService;


@Controller
public class MainController {
 
 @Autowired
 private UserService userService;
 
 @GetMapping("/")
 public String index(Model model) {
 
     model.addAttribute("newUser", new User());
     model.addAttribute("newLogin", new LoginUser());
     return "index.jsp";
 }
 
 @GetMapping("/home")
 public String home(Model model, HttpSession session) {
	 Long loggedIn = (Long) session.getAttribute("loggedIn");
	 if(loggedIn == null) {
		 return "redirect: /";
	 }
	 User user = userService.findById(loggedIn);
	 model.addAttribute("user", user);
	 return "profile.jsp";
 }
 
 @PostMapping("/register")
 public String register(@Valid @ModelAttribute("newUser") User newUser, 
         BindingResult result, Model model, HttpSession session) {
	 
     userService.register(newUser, result);
	 
     if(result.hasErrors()) {
         model.addAttribute("newLogin", new LoginUser());
         return "index.jsp";
     }
     
     // Store their ID from the DB in session
     session.setAttribute("loggedIn", newUser.getId());
 
     return "redirect:/home";
 }
 
 @PostMapping("/login")
 public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
         BindingResult result, Model model, HttpSession session) {
     

     User userInDb = userService.login(newLogin, result);
 
     if(result.hasErrors()) {
         model.addAttribute("newUser", new User());
         return "index.jsp";
     }
 
     // Store their ID from the DB in session
     session.setAttribute("loggedIn", userInDb.getId());
 
     return "redirect:/home";
 }
 
 //Clear the Id in session
 @GetMapping("/logout")
 public String logout(HttpSession session) {
	 session.setAttribute("loggedIn", null);
	 return "redirect:/";
	 }
 
}
