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
	
	@Column(name="image_url")
	private String image_url;
	
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
	
	@Column(name="user_id")
	private Integer userId;


	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	public Animal(String name, String description, String image_url, Integer height, Integer weight, Integer userId, Location location, Alimentation alimentation, Classification classification) {
		super();
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.alimentation = alimentation;
		this.location = location;
		this.classification = classification;		
		this.image_url = image_url;
		this.userId = userId;
	}
	
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getAlimentation() 
	{
		if(alimentation != null)
			return alimentation.getName();
		else
			return null;
	}

	
	public String getLocation() 
	{
		if(location != null)
			return location.getName();
		else
			return null;
			
	}
	
	public Location getLocationObject()
	{
		return location;
	}
	
	public Classification getClassificationObject()
	{
		return classification;
	}
	
	public Alimentation getAlimentationObject()
	{
		return alimentation;
	}
	
	public String getClassification() 
	{
		if(classification != null)
			return classification.getName();
		else
			return null;	
	}

	public void setLocation(Location location) 
	{
		this.location = location;
	}
	

	public void setAlimentation(Alimentation alimentation) {
		this.alimentation = alimentation;
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
	
	public String getName() 
	{
		return this.name;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
