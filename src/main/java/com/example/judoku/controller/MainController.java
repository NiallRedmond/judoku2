package com.example.judoku.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import com.example.judoku.model.Competition;
import com.example.judoku.model.Role;
import com.example.judoku.model.Tournament;
import com.example.judoku.model.User;
import com.example.judoku.repository.CompetitionRepository;
import com.example.judoku.repository.TournamentRepository;
import com.example.judoku.repository.UserRepository;

import groovy.lang.Grab;

@Grab("thymeleaf-spring4")

@Controller
//@SessionAttributes("name")
public class MainController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TournamentRepository tournamentRepository;
	@Autowired
	CompetitionRepository competitionRepository;

	
    @GetMapping("/")
    public String root(ModelMap map, Principal principal) {
    	User user = userRepository.findByEmail(principal.getName());
    	ArrayList<String> links = new ArrayList<String>();
    	

		ArrayList<Competition> comps = (ArrayList<Competition>) competitionRepository.findAll();
		
		for(Competition comp : comps)
		{
		       try {


			if(Long.parseLong(comp.getGold()) == user.getId()    )
			{
				String link = "";

				link = "<a href=\"http://localhost:8080/competitionstart/" + comp.getId() + "\"> 	<img src=\"/img/gold.png\" alt=\"gold\" title=\""+ comp.getName() +"\"> </a>";
						 
				links.add(link);

			}
		        } catch (NumberFormatException e) {
	    		     
 		           System.out.println(e.getMessage());
 		        }
			
		}
		
	
       
    	String role = "Role_User";
		User admin = userRepository.findByEmail(principal.getName());
		for (Role r : admin.getRoles()) {
			if (r.getName().equalsIgnoreCase("Role_Admin")) {
				role = "Role_Admin";
			}
		}
		map.addAttribute("links", links);
		map.addAttribute("User", user);
		map.addAttribute("role", role);
    	return "index";
    }
    /*
    @GetMapping("/edit")
    public String editProfile(ModelMap map, Principal principal) {
    	User user = userRepository.findByEmail(principal.getName());
       
 
		map.addAttribute("User", user);
	
    	return "editProfile";
    }*/
    
	@GetMapping("/profile/{id}")
	public String edit(Principal principal,  @PathVariable(value = "id") Long id, ModelMap map) {
	  
  
    	User user = userRepository.findOne(id);
    	ArrayList<String> links = new ArrayList<String>();
    	

    		ArrayList<Competition> comps = (ArrayList<Competition>) competitionRepository.findAll();
    		
    		for(Competition comp : comps)
    		{
    		       try {

   
    			if(Long.parseLong(comp.getGold()) == user.getId()    )
    			{
    				String link = "";

    				link = "<a href=\"http://localhost:8080/competitionstart/" + comp.getId() + "\"> 	<img src=\"/img/gold.png\" alt=\"gold\" title=\""+ comp.getName() +"\"> </a>";
    						 
    				links.add(link);

    			}
    		        } catch (NumberFormatException e) {
    	    		     
     		           System.out.println(e.getMessage());
     		        }
    			
    		}
    		
    	
    	
    	
    	
    	
		
    	
    	String role = "Role_User";
		User admin = userRepository.findByEmail(principal.getName());
		for (Role r : admin.getRoles()) {
			if (r.getName().equalsIgnoreCase("Role_Admin")) {
				role = "Role_Admin";
			}
		}
		map.addAttribute("links", links);
		map.addAttribute("User", user);
		map.addAttribute("role", role);
		
		return "profile";
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