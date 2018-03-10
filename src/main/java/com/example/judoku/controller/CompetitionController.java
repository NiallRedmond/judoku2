package com.example.judoku.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
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
import com.example.judoku.dto.MatchGen;
import com.example.judoku.dto.UserRegistrationDto;
import com.example.judoku.model.Competition;
import com.example.judoku.model.Event;
import com.example.judoku.model.Match;
import com.example.judoku.model.MatchPost;
import com.example.judoku.model.Role;
import com.example.judoku.model.User;
import com.example.judoku.repository.CompetitionRepository;
import com.example.judoku.repository.EventRepository;
import com.example.judoku.repository.UserRepository;

import groovy.lang.Grab;

@Grab("thymeleaf-spring4")

@Controller
// @SessionAttributes("name")
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

		// List<Integer> matchNum = new ArrayList<Integer>();
		List<Competitor> competitors = new ArrayList<Competitor>();
		List<String> buttons = new ArrayList<String>();
		List<MatchGen> matches = new ArrayList<MatchGen>();
		boolean completed;
		boolean newRound = true;
		int code = 1;
		int code2 = 1;
		String victor = "";
		Competition comp = competitionRepository.findOne(competitionId);

		for (User user : comp.getCompetitors()) {

			Competitor competitor = new Competitor();
			competitor.setId(user.getId());
			competitor.setName(user.getFirstName() + " " + user.getLastName());

			competitors.add(competitor);
		}

		// for(int i = 0; i < competitors.size();i++)
		// {
		// MatchGen match = new MatchGen();
		// ArrayList<Competitor> c = new ArrayList<Competitor>();
		// c.add(competitors.get(i));
		// c.add(competitors.get(i+1));
		// match.setCompetitors(c);
		// matches.add(match);
		// i = i + 2;
		// }
		
		
		Collection<Match> completedMatches = comp.getMatches();
		System.out.println("AAAAAAAAAAAAAAA");
		while (competitors.size() > 1 && newRound == true) {
			int counter = 1;
			code2 =1;
			for (Competitor c : competitors) {
				System.out.println(c.getName());
			}

			System.out.println("TESTESTESTEST");
			
			buttons.clear();
			
			for (int i = 0; i < competitors.size(); i++) {
				if ((counter % 2 == 0)) {
					completed = false;
					System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
					String name1 = competitors.get(i - 1).getName();
					String name2 = competitors.get(i).getName();
					String id1 = competitors.get(i - 1).getId().toString();
					String id2 = competitors.get(i).getId().toString();

					for (Match m : completedMatches) {
						if (m.getVictor().equals(id1) && m.getLoser().equals(id2)
								|| m.getVictor().equals(id2) && m.getLoser().equals(id1)) {
							User user = userRepository.findOne(Long.parseLong(m.getVictor()));
							completed = true;
							victor = user.getFirstName() + " " + user.getLastName();

						}
					}
					String codeSend = Integer.toString(code) + Integer.toString(code2);
					if (completed == false) {
						buttons.add("<a href=\"http://localhost:8080/competition/" + competitionId + "/" + codeSend + "/" + name1 + "_"
								+ id1 + "-" + name2 + "_" + id2 + "\" >Match Start!</a>");
						newRound = false;
					} else if(name1.equals("BYE")|| name2.equals("BYE")) {
						buttons.add("<div id = \"completed\"></div>");
					}
					else
					{
						buttons.add("<div id = \"completed\">Match completed. Victor: " + victor + "  </div>");
					}

				} else {
					buttons.add(" ");
				}
				code2++;
				counter++;
			}
		
			// System.out.println("BBBBBBBBBBBBBBBBBBBBBBBBBB");
			if (newRound == true && competitors.size() > 1) {

				for (Match m : completedMatches) {

					for (int i = 0; i < competitors.size(); i++) {
						if (m.getLoser().equals(competitors.get(i).getId().toString()) && String.valueOf(m.getCode().charAt(0)).equals(Integer.toString(code) )) {
							competitors.remove(i);
						}
					}

				}

			}
			code++;
	}

		if(competitors.size()==1)
		{
			buttons.add("testestest");
			Match match = completedMatches.iterator().next();
			for (Match m : completedMatches) {
				if(Integer.parseInt(m.getCode()) > Integer.parseInt(match.getCode()) )
				{
					match = m;
				}
				
			}
			comp.setGold(match.getVictor());
			comp.setSilver(match.getLoser());
			
			comp.setCompletedTrue();
			competitionRepository.save(comp);
			
			
			
		}
		else
		{
		if ((competitors.size() & 1) == 0) {
			// even...
		} else {
			// odd...
			Competitor competitor = new Competitor();
			competitor.setName("BYE");
			competitors.add(competitor);
		}

		}
		for (Competitor c : competitors) {
			System.out.println(c.getName());
		}

		buttons.add(" ");
		
		map.addAttribute("welcomeMessage", "welcome");
		map.addAttribute("names", competitors);

		map.addAttribute("buttons", buttons);

		// map.addAttribute("matchNum", matchNum);
		return "competition";
	}

	@GetMapping("/competition/{id}/{code}/{competitors}")
	public String match(ModelMap map, @PathVariable(value = "id") Long competitionId, @PathVariable(value = "code") String code,
			@PathVariable(value = "competitors") String competitors, Principal principal) {

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
		map.addAttribute("code", code);
		return "match";
	}


	@PostMapping("/competition/save")
	public String formPost(MatchPost matchPost, ModelMap model) {
		String button;
		String[] parts = matchPost.getLoser().split("_");
		if (parts[0].equals(matchPost.getVictor())) {
			matchPost.setLoser(parts[1]);
		}
		if (parts[1].equals(matchPost.getVictor())) {
			matchPost.setLoser(parts[0]);
		}


		System.out.println(model.toString());
		System.out.println("=====================");
		System.out.println(matchPost.toString());
		System.out.println("=====================");

		ArrayList<String> types = new ArrayList<String>();
		for (String word : matchPost.getEventType().split(",")) {
			types.add(word);
		}
		ArrayList<String> timestamps = new ArrayList<String>();
		for (String word : matchPost.getEventTimestamp().split(",")) {
			timestamps.add(word);
		}
		ArrayList<String> descriptions = new ArrayList<String>();
		for (String word : matchPost.getEventDescription().split(",")) {
			descriptions.add(word);
		}
		ArrayList<String> eventPlayers = new ArrayList<String>();
		for (String word : matchPost.getEventPlayer().split(",")) {
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
		match.setCode(matchPost.getCode());
		for (int i = 0; i < types.size(); i++) {
			Event event = new Event();
			event.setType(types.get(i));
			event.setTimetsamp(timestamps.get(i));
			event.setDescription(descriptions.get(i));

			if (eventPlayers.get(i).equals(user1.getId().toString())) {
				event.setUser(user1.getId().toString());
				user1.getEvents().add(event);
			}
			if (eventPlayers.get(i).equals(user2.getId().toString())) {
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

		for (int i = 0; i < types.size(); i++) {
			System.out.println(types.get(i) + timestamps.get(i) + descriptions.get(i) + eventPlayers.get(i));
		}

		// System.out.println(model.get("event").toString());
		// System.out.println(model.get("type"));
		// model.addAttribute("event", event2);

//		buttons.add("<a href=\"http://localhost:8080/competition/" + competitionId + "/" + codeSend + "/" + name1 + "_"
//				+ id1 + "-" + name2 + "_" + id2 + "\" >Match Start!</a>");
		button = "<a href =\"http://localhost:8080/competitionstart/" + matchPost.getCompetition() + "\" >Return to competition</a>  ";
		model.addAttribute("button", button);
		
		return "matchsaved";
	}

	@GetMapping("/createCompetition")
	public String competitonForm(Model model) {
		model.addAttribute("competition", new Competition());
		return "createCompetition";
	}

	@PostMapping("/createCompetition")
	public String createCompetition(@ModelAttribute("competition") @Valid CompetitionCreationDTO competitionDto,
			BindingResult result) {
		competitionDto.setDate(LocalDateTime.now().toString());
		if (result.hasErrors()) {
			return "createCompetition";
		}

		// userService.save(competitionDto);
		System.out.println(competitionDto.toString());

		Competition comp = new Competition();
		comp.setName(competitionDto.getName());
		comp.setDate(competitionDto.getDate());
		comp.setVenue(competitionDto.getVenue());
		comp.setPassword(passwordEncoder.encode(competitionDto.getPassword()));
		comp.setCompletedFalse();

		competitionRepository.save(comp);

		return "redirect:/createCompetition?success";

	}

	@GetMapping("/competition/{id}")
	public String viewCompetition(@PathVariable(value = "id") Long competitionId, ModelMap map, Principal principal) {
		Competition comp = competitionRepository.findOne(competitionId);
		Email email = new Email();
		String role = "Role_User";
		User user = userRepository.findByEmail(principal.getName());
		for(Role r : user.getRoles())
		{
			if(r.getName().equalsIgnoreCase("Role_Admin"))
			{
				role = "Role_Admin";
			}
		}
		
		
		map.addAttribute("competition", comp);
		map.addAttribute("email", email);
		map.addAttribute("user", user);
		map.addAttribute("role", role);
		return "viewCompetition";
	}
	
	@GetMapping("/competitionresult/{id}")
	public String viewResults(@PathVariable(value = "id") Long competitionId, ModelMap map) {
		Competition comp = competitionRepository.findOne(competitionId);
		Email email = new Email();
		map.addAttribute("competition", comp);
		map.addAttribute("email", email);
		return "competition2";
	}
	
	@GetMapping("/competition/search")
	public String compSearch(ModelMap map) {
		List<Competition> competitions = competitionRepository.findAll();
		int year = LocalDateTime.now().getYear();
		int yearCounter = year;
		ArrayList<Integer> years = new ArrayList<Integer>();

		
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		
		years.add(year);
		while(yearCounter > 1960)
		{
			yearCounter--;
			years.add(yearCounter);
		}
		
		
		for(int i = 0; i < competitions.size(); i++)
		{
			if(LocalDateTime.parse(competitions.get(i).getDate(), formatter).getYear() != year)
			{
				competitions.remove(i);
			}
		}
		
		map.addAttribute("years", years);
		map.addAttribute("competitions", competitions);
		return "CompetitionSearch";
				
	}



	@PostMapping("/competitions/{id}")
	public String viewCompetitionAddCompetitor(@PathVariable(value = "id") Long competitionId, ModelMap map,
			@ModelAttribute Email email) {

		Competition comp = competitionRepository.findOne(competitionId);

		map.addAttribute("competition", comp);

		System.out.println(email.getText());

		User user = userRepository.findByEmail(email.getText());

		comp.getCompetitors().add(user);

		competitionRepository.save(comp);

		return "viewCompetition";
	}

}