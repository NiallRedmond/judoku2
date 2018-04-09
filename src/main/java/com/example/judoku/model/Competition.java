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

	private Boolean completed;
	private String gold;
	private String silver;
	private String bronze;

	private String type;
	private String belt;
	private double weight;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "competitions_users", joinColumns = @JoinColumn(name = "competiton_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Collection<User> competitors;

	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "competitions_matches", joinColumns = @JoinColumn(name = "competition_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "match_id", referencedColumnName = "id"))
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

	public void setCompletedTrue() {
		this.completed = true;
	}

	public void setCompletedFalse() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBelt() {
		return belt;
	}

	public void setBelt(String belt) {
		this.belt = belt;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Collection<User> getCompetitors() {
		return competitors;
	}

	public void setCompetitors(Collection<User> competitors) {
		this.competitors = competitors;
	}

	@Override
	public String toString() {
		return "Competition [id=" + id + ", name=" + name + ", date=" + date + ", , password=" + ", competitors="
				+ competitors + "]";
	}

}