package ch.hearc.spring.diconimaux.model;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name="alimentation")
public class Alimentation {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
    private Long id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "alimentation")
	private List<Animal> animals;
	
    public Alimentation()
    {
    	
    }
    
	public Alimentation(String nameAlimentation) 
	{
		super();
		this.name = nameAlimentation;
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
