package com.example.judoku.dto;




import org.hibernate.validator.constraints.NotEmpty;



public class CompetitionCreationDTO {


    @NotEmpty
    private String name;

    @NotEmpty
    private String date;
    
    @NotEmpty
    private String venue;

    @NotEmpty
    private String password;








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





    @Override
    public String toString() {
        return "User{" +
                "name=" + name +
                ", date='" + date + '\'' +
                ", venue='" + venue + '\'' +

                '}';
    }


}