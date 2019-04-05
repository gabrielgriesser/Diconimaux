package ch.hearc.spring.diconimaux.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="animal")
public class Animal 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private String description;
	
	private String alimentation;
	//private Alimentation alimentation;
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "location_id")
	private String location;
	
	private String classification;
	
	private Integer height;
	
	private Integer weight;
	
	
	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	public Animal(String name, String description, Integer height, Integer weight, Location location, Alimentation alimentation, Classification classification) {
		super();
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.alimentation = alimentation.getName();
		this.location = location.getName();
		this.classification = classification.getName();

	}


	public long getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAlimentation() {
		return alimentation;
	}

	public void setAlimentation(Alimentation alimentation) {
		this.alimentation = alimentation.getName();
	}

	public String getLocation() {
		return location;
	}
	

	public void setLocation(String location) {
		this.location = location;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName () 
	{
		return this.name;
	}	
	
}
