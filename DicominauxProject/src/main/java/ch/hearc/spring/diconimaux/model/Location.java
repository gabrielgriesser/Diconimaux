package ch.hearc.spring.diconimaux.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="location")
public class Location {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	private String name;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.ALL)
	//private List<Animal> animals;
	
	
	public Location()
	{
		
	}
	
	public Location(String name)
	{
		super();
		this.name = name;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return this.id;
	}

	public String getName() 
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
}
