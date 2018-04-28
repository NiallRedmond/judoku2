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

import com.example.judoku.dto.CategoryDTO;
import com.example.judoku.dto.Email;
import com.example.judoku.dto.UserDto;
import com.example.judoku.dto.WeightDto;
import com.example.judoku.model.Competition;
import com.example.judoku.model.Tournament;
import com.example.judoku.model.User;
import com.example.judoku.repository.TournamentRepository;
import com.example.judoku.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	TournamentRepository tournamentRepository;
	

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
	
	
	
	// Get All Users
	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	// Create a new user
	@PostMapping("/users")
	public User createUsers(@Valid @RequestBody User user) {
	    return userRepository.save(user);
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

