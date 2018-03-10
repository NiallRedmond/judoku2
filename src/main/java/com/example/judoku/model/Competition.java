package com.example.judoku.model;



import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String date;
    private String venue;
    private String password;
    private Boolean signUpOpen;
    private Boolean completed;
    private String gold;
    private String silver;
    private String bronze;
    

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "competitions_users",
            joinColumns = @JoinColumn(
                    name = "competiton_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"))
    private Collection<User> competitors;
    
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "competitions_matches",
            joinColumns = @JoinColumn(
                    name = "competition_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "match_id", referencedColumnName = "id"))
    private Collection<Match> matches;
    

	public Competition() {
		super();
	}


	
	
	

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	

	public void setCompletedTrue( ) {
		this.completed = true;
	}
	
	public void setCompletedFalse( ) {
		this.completed = false;
	}

	public String getGold() {
		return gold;
	}

	public void setGold(String gold) {
		this.gold = gold;
	}

	public String getSilver() {
		return silver;
	}

	public void setSilver(String silver) {
		this.silver = silver;
	}

	public String getBronze() {
		return bronze;
	}

	public void setBronze(String bronze) {
		this.bronze = bronze;
	}

	public Boolean getSignUpOpen() {
		return signUpOpen;
	}

	public void setSignUpOpen(Boolean signUpOpen) {
		this.signUpOpen = signUpOpen;
	}

	public Collection<Match> getMatches() {
		return matches;
	}

	public void setMatches(Collection<Match> matches) {
		this.matches = matches;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<User> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Collection<User> competitors) {
		this.competitors = competitors;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", date=" + date + ", venue=" + venue + ", password="
				+ password + ", competitors=" + competitors + "]";
	}

    


    

    
    

   
}