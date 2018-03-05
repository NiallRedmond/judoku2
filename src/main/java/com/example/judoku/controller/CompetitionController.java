package com.example.judoku.controller;

import java.security.Principal;
import java.time.LocalDateTime;
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
import com.example.judoku.dto.Competitor;
import com.example.judoku.dto.Email;
import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.Competition;
import com.example.judoku.model.Event;
import com.example.judoku.model.Match;
import com.example.judoku.model.MatchPost;
import com.example.judoku.model.User;
import com.example.judoku.repository.CompetitionRepository;
import com.example.judoku.repository.EventRepository;
import com.example.judoku.repository.UserRepository;

import groovy.lang.Grab;

@Grab("thymeleaf-spring4")

@Controller
//@SessionAttributes("name")
public class CompetitionController {
	
	@Autowired
	CompetitionRepository competitionRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	EventRepository eventRepository;



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
    
    

    
    @GetMapping("/competitionstart/{id}")
    public String competition(@PathVariable(value = "id") Long competitionId, ModelMap map) {
    	
    	List<Integer> matchNum = new ArrayList<Integer>();
    	List<Competitor> names = new ArrayList<Competitor>();
    	List<String> buttons = new ArrayList<String>();
    	
	    Competition comp = competitionRepository.findOne(competitionId);
	    
	    for (User user : comp.getCompetitors())
	    {
//	    	names.add(user.getFirstName());
	    	Competitor competitor = new Competitor();
	    	competitor.setId(user.getId());
	    	competitor.setName(user.getFirstName() + " " + user.getLastName());
	    	
	    	names.add(competitor);
	    }
	    
	    
//    	names.add("Alex");
//    	names.add("Bill");
//  
//    	names.add("Conor");
//    	names.add("Daniel");
// 
//    	names.add("Evan");
//    	names.add("Fionn");
//    
//    	names.add("Graham");
//    	names.add("Harry");
	    
	    
    	int counter3 = 0;
    	int counter = 1;
    	for(int i=0; i<names.size(); i++)
    	{
    		if((counter%2==0))
    		{
    			String name1 = names.get(i-1).getName();
    			String name2 = names.get(i).getName();
    			String id1 = names.get(i-1).getId().toString();
    			String id2 = names.get(i).getId().toString();
    			
    			buttons.add("<a href=\"http://localhost:8080/competition/" + competitionId + "/" + name1 + "_" + id1 + "-" + name2 + "_" + id2 + "\" >Match Start!</a>");
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
        return "competition";
    }
    
	@GetMapping("/competition/{id}/{competitors}")
	public String match(ModelMap map, @PathVariable(value = "id") Long competitionId, @PathVariable(value = "competitors") String competitors, Principal principal) {
	
		String[] parts = competitors.split("-");
		String part1 = parts[0]; 
		String part2 = parts[1];
		
		System.out.println(part1);
		System.out.println(part2);
		String[] competitor1Parts = part1.split("_");
		String competitor1Name = competitor1Parts[0];
		String competitor1Id = competitor1Parts[1];		
		Competitor competitor1 = new Competitor();
		competitor1.setId(Long.parseLong(competitor1Id));
		competitor1.setName(competitor1Name);
		
		String[] competitor2Parts = part2.split("_");
		String competitor2Name = competitor2Parts[0];
		String competitor2Id = competitor2Parts[1];		
		Competitor competitor2 = new Competitor();
		competitor2.setId(Long.parseLong(competitor2Id));
		competitor2.setName(competitor2Name);
				
				
		System.out.println("===========================================================");
		System.out.println(principal.getName());
		System.out.println("===========================================================");
		map.addAttribute("competitor1", competitor1);
		map.addAttribute("competitor2", competitor2);
		map.addAttribute("competitionId", competitionId);
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
  
    	String[] parts = matchPost.getLoser().split("_");
    	if(parts[0].equals(matchPost.getVictor()))
    	{
    		matchPost.setLoser(parts[1]);
    	}
    	if(parts[1].equals(matchPost.getVictor()))
    	{
    		matchPost.setLoser(parts[0]);
    	}
   
   
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
    	
    	User user1 = userRepository.findOne(Long.parseLong(matchPost.getVictor()));
    
    	User user2 = userRepository.findOne(Long.parseLong(matchPost.getLoser()));
    	
    	
    	Competition comp = competitionRepository.findOne(Long.parseLong(matchPost.getCompetition()));
    	Match match = new Match();
    	match.setDate(LocalDateTime.now().toString());
    	match.setVictor(matchPost.getVictor());
    	match.setLoser(matchPost.getLoser());
    	match.setEvents(new ArrayList<Event>());
    	for(int i = 0; i<types.size(); i++)
    	{
    		Event event = new Event();
    		event.setType(types.get(i));
    		event.setTimetsamp(timestamps.get(i));
    		event.setDescription(descriptions.get(i));
    	

    		if(eventPlayers.get(i).equals(user1.getId().toString()))
    		{
    			event.setUser(user1.getId().toString());
    			user1.getEvents().add(event);
    		}
    		if(eventPlayers.get(i).equals(user2.getId().toString()))
    		{
    			event.setUser(user2.getId().toString());
    			user2.getEvents().add(event);
    		}
    		
    		match.getEvents().add(event);
    	}
    	
    	user1.getMatches().add(match);
    	user2.getMatches().add(match);
    	
    	comp.getMatches().add(match);
    	
    	competitionRepository.save(comp);
    	userRepository.save(user1);
    	userRepository.save(user2);
    	
    	
    	
    	for(int i = 0; i<types.size(); i++)
    	{ 		
    		System.out.println(types.get(i) + timestamps.get(i) + descriptions.get(i) + eventPlayers.get(i));
    	}

    	
    	//System.out.println(model.get("event").toString());
    	//System.out.println(model.get("type"));
     //   model.addAttribute("event", event2);
       
        return "index";
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
    public String viewCompetitionAddCompetitor(@PathVariable(value = "id") Long competitionId, ModelMap map, @ModelAttribute Email email) {
        
	    Competition comp = competitionRepository.findOne(competitionId);

	    map.addAttribute("competition", comp);	
    	
    	System.out.println(email.getText());
    	
    	User user = userRepository.findByEmail(email.getText());
    	
    	
    	
    	comp.getCompetitors().add(user);
    	
    	competitionRepository.save(comp);
    	
    	return "viewCompetition";
    }
    
}