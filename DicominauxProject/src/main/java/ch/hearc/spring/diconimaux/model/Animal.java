package ch.hearc.spring.diconimaux.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="animal")
public class Animal 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alimentation_id")
	private Alimentation alimentation;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "classification_id")
	private Classification classification;
	
	@Column(name="height")
	private Integer height;
	
	@Column(name="weight")
	private Integer weight;

	private String location_name;
	private String classification_name;
	private String alimentation_name;

	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	public Animal(String name, String description, Integer height, Integer weight, Location location, Alimentation alimentation, Classification classification) {
		super();
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.alimentation = alimentation;//.getName();
		this.location = location;//.getName();
		this.classification = classification;//.getName();
		
		this.location_name = location.getName();
		this.classification_name = classification.getName();
		this.alimentation_name = alimentation.getName();

	}
	
	public String getLocation_name()
	{
		return this.location_name;
	}
	
	public String getAlimentation_name()
	{
		return this.alimentation_name;
	}
	
	public String getClassification_name()
	{
		return this.classification_name;
	}
	
	public long getLocationID()
	{
		return this.location.getId();
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

	public Alimentation getAlimentation() {
		return this.alimentation;
	}

	public void setAlimentation(Alimentation alimentation) {
		this.alimentation = alimentation;
	}

	public Location getLocation() 
	{
		return this.location;
	}
	

	public void setLocation(Location location) {
		this.location = location;
	}

	public Classification getClassification() {
		return this.classification;
	}

	public void setClassification(Classification classification) {
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
