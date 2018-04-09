package com.example.judoku.model;



import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String date;
    private String venue;

    private Boolean signUpOpen;
    private Boolean completed;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "competitions_sign_ups",
            joinColumns = @JoinColumn(
                    name = "competiton_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private Collection<User> competitors;
    
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "all_competition_matches",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "match_id", referencedColumnName = "id"))
    private Collection<Match> matches;
    
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "competition_categories",
            joinColumns = @JoinColumn(
                    name = "tournament_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"))
    private Collection<Competition> categories;
    
	public void setCompletedTrue() {
		this.completed = true;
	}

	public void setCompletedFalse() {
		this.completed = false;
	}

	public Tournament() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public Boolean getSignUpOpen() {
		return signUpOpen;
	}

	public void setSignUpOpen(Boolean signUpOpen) {
		this.signUpOpen = signUpOpen;
	}

	public Boolean isCompleted() {
		return completed;
	}

	public Boolean getCompleted() {
		return completed;
	}
	
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Collection<User> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Collection<User> competitors) {
		this.competitors = competitors;
	}

	public Collection<Match> getMatches() {
		return matches;
	}

	public void setMatches(Collection<Match> matches) {
		this.matches = matches;
	}
    
	
	
	public Collection<Competition> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Competition> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		return id + "\n"+ name + "\n"+date + "\n"+ venue;
	}

    
   
   
}