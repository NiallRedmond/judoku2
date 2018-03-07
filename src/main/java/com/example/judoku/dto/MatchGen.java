package com.example.judoku.dto;

import java.util.ArrayList;

import antlr.collections.List;

public class MatchGen {
	
	private long id;
	private ArrayList<Competitor> competitors;
	private Competitor Victor;
	private Competitor Loser;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public ArrayList<Competitor> getCompetitors() {
		return competitors;
	}
	public void setCompetitors(ArrayList<Competitor> competitors) {
		this.competitors = competitors;
	}
	public Competitor getVictor() {
		return Victor;
	}
	public void setVictor(Competitor victor) {
		Victor = victor;
	}
	public Competitor getLoser() {
		return Loser;
	}
	public void setLoser(Competitor loser) {
		Loser = loser;
	}

	
	
}
