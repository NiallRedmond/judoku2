package com.example.judoku.model;



import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	public Competition(String name, String date, String venue, String password, Collection<User> competitors) {
		super();
		this.name = name;
		this.date = date;
		this.venue = venue;
		this.password = password;
		this.competitors = competitors;
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