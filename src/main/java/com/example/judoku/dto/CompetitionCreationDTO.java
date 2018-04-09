package com.example.judoku.dto;




import java.time.LocalDateTime;

import org.hibernate.validator.constraints.NotEmpty;



public class CompetitionCreationDTO {


    @NotEmpty
    private String name;

    @NotEmpty
    private String date;
    
    @NotEmpty
    private String venue;






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






    @Override
    public String toString() {
        return "competition{" +
                "name=" + name +
                ", date='" + date + '\'' +
                ", venue='" + venue + '\'' +

                '}';
    }


}