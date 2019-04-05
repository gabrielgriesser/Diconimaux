package ch.hearc.spring.diconimaux.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="alimentation")
public class Alimentation {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

    private Integer id;
    
	private String name;
	
    public Alimentation()
    {
    	
    }
    
	public Alimentation(String nameAlimentation) 
	{
		super();
		this.name = nameAlimentation;
	}
    
    public void setId(Integer id)
	{
		this.id = id;
	}

    public Integer getId()
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
