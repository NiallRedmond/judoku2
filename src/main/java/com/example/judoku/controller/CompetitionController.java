package com.example.judoku.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import com.example.judoku.model.Competition;
import com.example.judoku.model.User;
import com.example.judoku.repository.CompetitionRepository;
import com.example.judoku.repository.UserRepository;

import groovy.lang.Grab;

@Grab("thymeleaf-spring4")

@Controller
//@SessionAttributes("name")
public class CompetitionController {
	
	@Autowired
	CompetitionRepository competitionRepository;
/**
    @GetMapping("/competition")
    public String competition(Model model) {
    	String name = "test";
    	model.addAttribute(name);
        return "competition";

    }**/
    /**
    @RequestMapping("/competition")
    public String competition(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
    	 name = "test";
    	 String name2 = "test";
    	model.addAttribute(name);
    	model.addAttribute(name2, "TEST");
        return "competition";
        
        
    }**/

    
    

	@GetMapping("/competitions")
	public List<Competition> getAllCompetitions() {
		return competitionRepository.findAll();
	}
	

    @GetMapping("/match")
    public String match(Model model) {
    	String name = "test";
    	model.addAttribute(name);
        return "login";
  
    }
    
    
    @GetMapping("/printViewPage")
    public String passParametersWithModelMap(ModelMap map) {
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("message", "Baeldung");
        return "greeting";
    }
    
    @GetMapping("/competition")
    public String competition(ModelMap map) {
    	List<Integer> matchNum = new ArrayList<Integer>();
    	List<String> names = new ArrayList<String>();
    	List<String> buttons = new ArrayList<String>();
    	names.add("Alex");
    	names.add("Bill");
  
    	names.add("Conor");
    	names.add("Daniel");
 
    	names.add("Evan");
    	names.add("Fionn");
    
    	names.add("Graham");
    	names.add("Harry");
    	int counter3 = 0;
    	int counter = 1;
    	for(int i=0; i<names.size(); i++)
    	{
    		
    	

    		if((counter%2==0))
    		{
    			String name1 = names.get(i-1);
    			String name2 = names.get(i);	
    			
    			buttons.add("<a href=\"http://localhost:8080/competition/" + name1 + "-" + name2 + "\" class=\"btn btn-default\">Match Start!</a>");
        		matchNum.add(counter3);
        		counter3++;
    		}
    		else
    		{
    			buttons.add(" ");
    		}
    		

    		counter++;
    	}
    	
    	
    	
   
    	
    	
    	
    	buttons.add("TEEEEEEEST");
        map.addAttribute("welcomeMessage", "welcome");
        map.addAttribute("names", names);
        
        map.addAttribute("buttons",buttons);
       
        map.addAttribute("matchNum", matchNum);
        return "greeting";
    }
    
	@GetMapping("/competition/{competitors}")
	public String match(ModelMap map, @PathVariable(value = "competitors") String competitors, Principal principal) {
	
		String[] parts = competitors.split("-");
		String part1 = parts[0]; 
		String part2 = parts[1];
		System.out.println("===========================================================");
		System.out.println(principal.getName());
		System.out.println("===========================================================");
		map.addAttribute("part1", part1);
		map.addAttribute("part2", part2);
	    return "match";
	}
    
}