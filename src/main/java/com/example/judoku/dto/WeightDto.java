package com.example.judoku.dto;

public class WeightDto {
	
	private long userId;
	private long tourId;
	private double userWeight;
	private String weight;
	private String kyu;
	
	public WeightDto() {
		super();
	}

	
	public long getTourId() {
		return tourId;
	}


	public void setTourId(long tourId) {
		this.tourId = tourId;
	}


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public double getUserWeight() {
		return userWeight;
	}

	public void setUserWeight(double userWeight) {
		this.userWeight = userWeight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getKyu() {
		return kyu;
	}

	public void setKyu(String kyu) {
		this.kyu = kyu;
	}
	
	
	

}
