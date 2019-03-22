package ch.hearc.spring.diconimaux.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="alimentation")
public class Alimentation {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column
    private Integer id;
    
    @Column
	private String alimentation;
	
	public Alimentation(String alimentation) 
	{
		super();
		this.alimentation = alimentation;
	}

	public String getAlimentation() 
	{
		return this.alimentation;
	}
	
	public void setAlimentation(String alimentation)
	{
		this.alimentation = alimentation;
	}
	
}
