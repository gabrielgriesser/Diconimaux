package ch.hearc.spring.thymeleaf.model;

public class Animal {
	public String name, description, feed, location, classification;
	public Integer height, weight;
	//private Feed feed;
	//private Location location;
	//private Classification classification;
	
	
	public Animal(String name, String description, Integer height, Integer weight, Location location, Feed feed, Classification classification) {
		super();
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.feed = feed.getFeed();
		this.location = location.getLocation();
		this.classification = classification.getClassification();

		//this.setLocation(location);
		//this.setFeed(feed);
		//this.setClassification(classification);
	}
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}

	public String getName () 
	{
		return this.name;
	}	
	
}
