package ch.hearc.spring.diconimaux.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="animal")
public class Animal 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column
	private Integer id;
	
	@Column
	private String name;
	
	@Column
	private String description;
	
	@Column
	private String alimentation;
	
	//TODO
	//CHANGER LOCATION ET CLASSIFICATION --> ID (?)
	@Column
	private String location;
	
	@Column
	private String classification;
	
	@Column
	private Integer height;
	
	@Column 
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
		this.alimentation = alimentation.getAlimentation();
		this.location = location.getLocation();
		this.classification = classification.getClassification();

		//this.setLocation(location);
		//this.setFeed(feed);
		//this.setClassification(classification);
	}


	public Integer getId() {
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

	public void setAlimentation(String alimentation) {
		this.alimentation = alimentation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location.getLocation();
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification.getClassification();
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
