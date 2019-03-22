package ch.hearc.spring.diconimaux.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="location")
public class Location {
    
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    private Integer id;
	
	@Column
	private String location;
	
	public Location(String location)
	{
		super();
		this.location = location;
	}

	public String getLocation() 
	{
		return this.location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
}
