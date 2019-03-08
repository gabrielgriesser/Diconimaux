package ch.hearc.spring.thymeleaf.model;

public class Location {
	
	private String location;
	
	public Location(String location)
	{
		super();
		this.location = location;
	}

	public String getLocation () 
	{
		return this.location;
	}
	
	
}
