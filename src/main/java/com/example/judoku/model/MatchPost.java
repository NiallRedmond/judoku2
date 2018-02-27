package com.example.judoku.model;



public class MatchPost{


    private String eventType;
    private String eventTimestamp;
    private String eventDescription;
    private String eventPlayer;
    
    private String victor;
    private String loser;
    private String date;
    
    private String competition;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(String eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getEventPlayer() {
		return eventPlayer;
	}

	public void setEventPlayer(String eventPlayer) {
		this.eventPlayer = eventPlayer;
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

	public String getCompetition() {
		return competition;
	}

	public void setCompetition(String competition) {
		this.competition = competition;
	}

	@Override
	public String toString() {
		return "MatchPost [eventType=" + eventType + ", eventTimestamp=" + eventTimestamp + ", eventDescription="
				+ eventDescription + ", eventPlayer=" + eventPlayer + ", victor=" + victor + ", loser=" + loser
				+ ", date=" + date + ", competition=" + competition + "]";
	}


    
    
}