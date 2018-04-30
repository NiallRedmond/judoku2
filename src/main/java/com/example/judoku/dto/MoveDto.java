package com.example.judoku.dto;

public class MoveDto {

	private long userId;
	private long moveFromId;
	private long moveToId;
	private long tournamentId;
	
	
	
	
	public MoveDto() {
		super();
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getMoveFromId() {
		return moveFromId;
	}
	public void setMoveFromId(long moveFromId) {
		this.moveFromId = moveFromId;
	}
	public long getMoveToId() {
		return moveToId;
	}
	public void setMoveToId(long moveToId) {
		this.moveToId = moveToId;
	}
	public long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(long tournamentId) {
		this.tournamentId = tournamentId;
	}
	
	
	
	
}
