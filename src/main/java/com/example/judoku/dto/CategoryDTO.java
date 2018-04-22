package com.example.judoku.dto;

public class CategoryDTO {

	
	private String weight;
	private String name;
	private String kyu;
	
	
	
	public CategoryDTO() {
		super();
	}

	
	public String getKyu() {
		return kyu;
	}


	public void setKyu(String kyu) {
		this.kyu = kyu;
	}


	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
