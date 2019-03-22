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
	private String classification;
	
	public Classification(String classification) 
	{
		super();
		this.classification = classification;
	}

	public String getClassification() 
	{
		return this.classification;
	}
	
	public void setClassification(String classification)
	{
		this.classification = classification;
	}
	
	
}
