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
	private String name;
	
	
	public Location()
	{
		
	}
	
	public Location(String name)
	{
		super();
		this.name = name;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
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
