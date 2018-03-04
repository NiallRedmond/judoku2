package com.example.judoku.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

import com.example.judoku.dto.CompetitionCreationDTO;
import com.example.judoku.dto.Email;
import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.Competition;
import com.example.judoku.model.Event;
import com.example.judoku.model.Match;
import com.example.judoku.model.MatchPost;
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


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    

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
    			
    			buttons.add("<a href=\"http://localhost:8080/competition/" + name1 + "-" + name2 + "\" >Match Start!</a>");
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
//	@PostMapping("/competition/save")
//	public String matchPost(@Valid @RequestBody Event match) {
//	
//		
//	
//	    return "match2";
//	}

    @PostMapping("/competition/save")
    public String formPost(MatchPost matchPost, ModelMap model) {
  
    
   
    	Event event2 = new Event();
    	System.out.println(model.toString());
    	System.out.println("=====================");
    	System.out.println(matchPost.toString());
    	System.out.println("=====================");
    	
    	ArrayList<String> types = new ArrayList<String>();
    	for(String word : matchPost.getEventType().split(",")) {
    	    types.add(word);
    	}
    	ArrayList<String> timestamps = new ArrayList<String>();
    	for(String word : matchPost.getEventTimestamp().split(",")) {
    	    timestamps.add(word);
    	}
    	ArrayList<String> descriptions = new ArrayList<String>();
    	for(String word : matchPost.getEventDescription().split(",")) {
    	    descriptions.add(word);
    	}
    	ArrayList<String> eventPlayers = new ArrayList<String>();
    	for(String word : matchPost.getEventPlayer().split(",")) {
    	    eventPlayers.add(word);
    	}
    	
    	
    	    
    	
    	
    	
    	for(int i = 0; i<types.size(); i++)
    	{ 		
    		System.out.println(types.get(i) + timestamps.get(i) + descriptions.get(i) + eventPlayers.get(i));
    	}

    	
    	//System.out.println(model.get("event").toString());
    	//System.out.println(model.get("type"));
     //   model.addAttribute("event", event2);
       
        return "match2";
    }
    
    
    @GetMapping("/createCompetition")
    public String competitonForm(Model model) {
        model.addAttribute("competition", new Competition());
        return "createCompetition";
    }


    
    @PostMapping("/createCompetition")
    public String createCompetition(@ModelAttribute("competition") @Valid CompetitionCreationDTO competitionDto, 
                                      BindingResult result){
    	


        if (result.hasErrors()){
            return "createCompetition";
        }

      //  userService.save(competitionDto);
        System.out.println(competitionDto.toString());
        
        Competition comp = new Competition();
        comp.setName(competitionDto.getName());
        comp.setDate(competitionDto.getDate());
        comp.setVenue(competitionDto.getVenue());
        comp.setPassword(passwordEncoder.encode(competitionDto.getPassword()));

        competitionRepository.save(comp);
        
        return "redirect:/createCompetition?success";
        

    }
    
	@GetMapping("/competitions/{id}")
	public String viewCompetition(@PathVariable(value = "id") Long competitionId, ModelMap map) {
	    Competition comp = competitionRepository.findOne(competitionId);
	  	Email email = new Email();
	    map.addAttribute("competition", comp);	
	    map.addAttribute("email", email);	
	    return "viewCompetition";
	}
    
    @PostMapping("/competitions/{id}")
    public String viewCompetitionAddCompetitor(@ModelAttribute Email email) {
        
    	System.out.println(email.getText());
    	return "viewCompetition";
    }
    
}