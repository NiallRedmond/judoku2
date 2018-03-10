package com.example.judoku.model;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="Judo_Match") 
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String victor;
    private String loser;
    private String date;
    private String code;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "matches_events",
            joinColumns = @JoinColumn(
                    name = "match_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "event_id", referencedColumnName = "id"))
    private Collection<Event> events;
    
    
    
	public Match(String victor, String loser, String date) {
		super();
		this.victor = victor;
		this.loser = loser;
		this.date = date;
	}

public Match() {
	super();
}



public Match(String victor, String loser, String date, Collection<Event> events) {
	super();
	this.victor = victor;
	this.loser = loser;
	this.date = date;
	this.events = events;
}




public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public Collection<Event> getEvents() {
	return events;
}

public void setEvents(Collection<Event> events) {
	this.events = events;
}

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getVictor() {
	return victor;
}

public void setVictor(String victor) {
	this.victor = victor;
}

public String getLoser() {
	return loser;
}

public void setLoser(String loser) {
	this.loser = loser;
}

public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}
    
    
	
	
    
    
    


}

 