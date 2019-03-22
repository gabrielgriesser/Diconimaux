package ch.hearc.spring.diconimaux.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="classification")
public class Classification 
{
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    private Integer id;
	
	@Column
	private String name;
	
	public Classification()
	{
		
	}
	
	public Classification(String nameClassification) 
	{
		super();
		this.name = nameClassification;
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
