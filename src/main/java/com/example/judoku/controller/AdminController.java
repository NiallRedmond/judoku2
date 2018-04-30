package com.example.judoku.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.judoku.dto.Belt;
import com.example.judoku.dto.CategoryDTO;
import com.example.judoku.dto.Email;
import com.example.judoku.dto.MoveDto;
import com.example.judoku.dto.Query;
import com.example.judoku.dto.UserDto;
import com.example.judoku.dto.WeightDto;
import com.example.judoku.model.Competition;
import com.example.judoku.model.Tournament;
import com.example.judoku.model.User;
import com.example.judoku.repository.CompetitionRepository;
import com.example.judoku.repository.TournamentRepository;
import com.example.judoku.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TournamentRepository tournamentRepository;
	@Autowired
	CompetitionRepository competitionRepository;
	

	@GetMapping("/opensignup/{id}")
	public String openSignUp(@PathVariable(value = "id") Long Id) {
		
		Tournament comp = tournamentRepository.findOne(Id);
		boolean b = true;
		comp.setSignUpOpen(b);
		tournamentRepository.save(comp);
		
		return "redirect:/comp/" + Id;
	}
	@GetMapping("/closesignup/{id}")
	public String closeSignUp(@PathVariable(value = "id") Long Id) {
		
		Tournament comp = tournamentRepository.findOne(Id);
		boolean b = false;
		comp.setSignUpOpen(b);
		tournamentRepository.save(comp);
		
		return "redirect:/comp/" + Id;
	}
	
	@PostMapping("/addcategory/{id}")
	public String createCategory(@PathVariable(value = "id") Long Id, ModelMap map, @ModelAttribute CategoryDTO dto,
			BindingResult result) {

		

		
		return "redirect:/comp/" + Id;

	}
	
	@GetMapping("/weighin/{id}")
	public String weighIn(@PathVariable(value = "id") Long Id, ModelMap map) {
	
		Tournament comp = tournamentRepository.findOne(Id);
		Collection<Competition> categories = comp.getCategories();
		
		Collection<User> users = comp.getCompetitors();
		
		
		ArrayList<UserDto> competitors = new ArrayList<UserDto>();
		
		for(User user : users)
		{
			boolean b = false;
			if(user.getLastName() == null)
			{
				user.setLastName(" ");
			}
			
			
			
			
			
			UserDto u = new UserDto();
			u.setWeighedIn(b);
			u.setUserId(user.getId());
			u.setUserName(user.getFirstName() + " " + user.getLastName());
			
			for(Competition cat : categories)
			{
				Collection<User> exists = cat.getCompetitors();
				
				for(User aUser : exists)
				{
					if( u.getUserId() == aUser.getId())
					{
					    b = true;
						u.setWeighedIn(b);
						u.setMessage("Weigh in complete - Click to edit");
					}

				}
				
				
				
			}
			if(!u.isWeighedIn())
			{
				u.setMessage(" ");
			}
			competitors.add(u);
					
		}
		

		map.addAttribute("competitors", competitors);
		map.addAttribute("competition", comp);
		
		return "weighIn";
	}
	
	@GetMapping("/weighin/{id}/{userId}")
	public String weighIn(@PathVariable(value = "id") Long Id, @PathVariable(value = "userId") Long userId, ModelMap map) {
	
		Tournament comp = tournamentRepository.findOne(Id);
		User user = userRepository.findOne(userId);
		
		
		
	
		map.addAttribute("WeightDto", new WeightDto());
		map.addAttribute("User", user);
		map.addAttribute("competition", comp);
		
		return "weighInUser";
	} 
	
	@GetMapping("/weighin/edit/{tourId}/{compId}/{userId}")
	public String edit(@PathVariable(value = "tourId") Long tourId, @PathVariable(value = "compId") Long compId, @PathVariable(value = "userId") Long userId, ModelMap map) {
	
    	Tournament comp = tournamentRepository.findOne(tourId);
    	User user = userRepository.findOne(userId);
		
		
		
	
		map.addAttribute("MoveDto", new MoveDto());
		map.addAttribute("userId", userId);
		map.addAttribute("User", user);
		map.addAttribute("compId", compId);
		map.addAttribute("tourId", tourId);
		map.addAttribute("categories", comp.getCategories());
		
		return "moveUser";
	} 

	@PostMapping("/weighin/edit")
	public String createCategory(ModelMap map, @ModelAttribute MoveDto dto,
			BindingResult result) {

		System.out.println(dto.getMoveFromId());
		System.out.println(dto.getMoveToId());
		System.out.println(dto.getUserId());
		System.out.println(dto.getTournamentId());

		User user = userRepository.findOne(dto.getUserId());
		
		Competition categoryMoveFrom = competitionRepository.findOne(dto.getMoveFromId());
		Competition categoryMoveTo = competitionRepository.findOne(dto.getMoveToId());
		
		Collection<User> categoryMoveFromCompetitors = categoryMoveFrom.getCompetitors();
		Collection<User> categoryMoveToCompetitors = categoryMoveTo.getCompetitors();
	
		System.out.println("=============================================");
		System.out.println(user.getFirstName());
		System.out.println("From: " + categoryMoveFrom.getName());
		System.out.println("To: " + categoryMoveTo.getName());
		System.out.println("=============================================");
		
		boolean dup = false;
		for(User u : categoryMoveToCompetitors)
		{
			if(u.getId() == user.getId())
			{
			    dup = true;
			}
		}
		
		if(dup == false)
		{
			categoryMoveToCompetitors.add(user);
		}
		
		categoryMoveFromCompetitors.remove(user);
		
		
		categoryMoveFrom.setCompetitors(categoryMoveFromCompetitors);
		categoryMoveTo.setCompetitors(categoryMoveToCompetitors);
		
		competitionRepository.save(categoryMoveFrom);
		competitionRepository.save(categoryMoveTo);
				
		
		return "redirect:/comp/" + dto.getTournamentId();

	}
	
	@GetMapping("/profile/{id}")
	public String edit( @PathVariable(value = "id") Long id, ModelMap map) {
	
  
    	User user = userRepository.findOne(id);
		
		
		
    	

		map.addAttribute("User", user);

		
		return "profile";
	} 
	

	
	@GetMapping("/users")
	public String userSearch(ModelMap map) {
		// List<Book> books = bookRepository.findAll();
		Query query = new Query();
	
		ArrayList<String> links = new ArrayList<String>();

		map.addAttribute("links", links);
		map.addAttribute("Query", query);
		return "userDatabase";

	}
	
	
	@PostMapping("/users")
	public String bookSearchPost(ModelMap map, @ModelAttribute Query query) {
		List<User> users = userRepository.findAll();
		System.out.println("=================================");
		System.out.println(query.getQuery());
		System.out.println("=================================");

		ArrayList<String> links = new ArrayList<String>();




		if (query.getQuery() != "") {

			for (int i = 0; i < users.size(); i++) {

				if (users.get(i).getFirstName().toLowerCase().contains((query.getQuery().toLowerCase())) || users.get(i).getLastName().toLowerCase().contains((query.getQuery().toLowerCase()))  ) {
					String link = "";


					link = "<a href=\"http://localhost:8080/profile/" + users.get(i).getId() + "\"> "
							+ users.get(i).getFirstName() + " " +  users.get(i).getLastName() +  "</a>" +  " - email: " + users.get(i).getEmail();
					links.add(link);
				} else {

				}

			}

		}



		System.out.println("=================================");
		System.out.println(users.toString());


		map.addAttribute("links", links);
		return "userDatabase";

	}
	
	
	@GetMapping("/edit/{id}")
	public String userEdit( @PathVariable(value = "id") Long id, ModelMap map) {
		
		User user = userRepository.findOne(id);
		Belt belt = new Belt();
		
		
		
		map.addAttribute("User", user);
		map.addAttribute("Belt", belt);
		return "editProfile";

	}
	
	@PostMapping("/edit")
	public String userEditPost( @ModelAttribute Belt belt, ModelMap map) {
		
		User user = userRepository.findOne(belt.getUserId());
		
		System.out.println(belt.getBelt());
		
		user.setBelt(belt.getBelt());
		
		userRepository.save(user);
		
		
		map.addAttribute("User", user);
		map.addAttribute("Belt", belt);
		return "redirect:/profile/" + belt.getUserId();

	}
	



	// Get a Single User
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long userId) {
	    User user = userRepository.findOne(userId);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok().body(user);
	}

	// Update a User
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateNote(@PathVariable(value = "id") Long userId, @Valid @RequestBody User userDetails) {
	    User user = userRepository.findOne(userId);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }

	    user.setEmail(userDetails.getEmail());

	    User updatedUser = userRepository.save(user);
	    return ResponseEntity.ok(updatedUser);
	}

	// Delete a User
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteNote(@PathVariable(value = "id") Long userId) {
	    User user = userRepository.findOne(userId);
	    if(user == null) {
	        return ResponseEntity.notFound().build();
	    }
	    userRepository.delete(user);
	    return ResponseEntity.ok().build();
	}
}

