package com.example.judoku.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import com.example.judoku.model.User;
import com.example.judoku.repository.UserRepository;

import groovy.lang.Grab;

@Grab("thymeleaf-spring4")

@Controller
//@SessionAttributes("name")
public class MainController {

	@Autowired
	UserRepository userRepository;
	
	
	
    @GetMapping("/")
    public String root() {
    	String date = LocalDateTime.now().toString();
    	System.out.println(date);
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
    	String name = "test";
    	model.addAttribute(name);
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    /**
    @GetMapping("/competition")
    public String competition() {
        return "competition2";
    }**/
    
    @GetMapping("/test")
    public String test() {
        return "test";
    }

    
	// Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		
		return userRepository.findAll();
	}
}