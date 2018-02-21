package com.example.judoku.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String type;
    private String timetsamp;
    private String description;
    
    
	public Event(String type, String timetsamp, String description) {
		super();
		this.type = type;
		this.timetsamp = timetsamp;
		this.description = description;
	}


	public Event() {
		super();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTimetsamp() {
		return timetsamp;
	}


	public void setTimetsamp(String timetsamp) {
		this.timetsamp = timetsamp;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", timetsamp=" + timetsamp + ", description=" + description + "]";
	}
    
    
    
    
}
