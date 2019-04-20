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

	//private String nameLocation;
	//private String nameClassification;
	//private String nameAlimentation;

	public Animal() {
		// TODO Auto-generated constructor stub
	}
	
	public Animal(String name, String description, Integer height, Integer weight, Location location, Alimentation alimentation, Classification classification) {
		super();
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.alimentation = alimentation;
		this.location = location;
		this.classification = classification;
		
		/*
			if(location != null || alimentation != null || classification != null)
			
			{
				setNameAlimentation(alimentation.getName());
				setNameLocation(location.getName());
				setNameClassification(classification.getName());
				
				System.out.println("l : " + nameLocation + " a : " + nameAlimentation + " C : " + nameClassification);
			}
			else
			{
				System.out.println("NULL");
			}
		*/

	}
	

	
	/*
	 * ICI on a le champs NOM LOCATION/CLASS/ALIM
	 * CE QUI EST ETRANGE, C'EST QUE ICI LE location.getName EST NULL !!!
	 * Alors que si dans la méthode getLocation() je teste location.getName(), c'est PAS NULL !!!
	 * POURQUOI ?
	 * LE MIEUX EST D'EVITER D'UTILISER UN CHAMP NOMLOCATION/NOMXXX !!!
	 * Donc pas obligatoire, pour l'instant ça marche sans ces champs
	 */
	/*
		public String getNameLocation()
		{
			
			//System.out.println("NOM : " + location.getName());
			return this.nameLocation;
		}
		
		public String getNameClassification()
		{
			return this.classification.getName();
		}
		public String getNameAlimentation()
		{
			return this.nameAlimentation;
		}
		
		
		
		public void setNameLocation(String name)
		{
			this.nameLocation = name;
		}
		public void setNameClassification(String name)
		{
			this.nameClassification = name;
		}
		public void setNameAlimentation(String name)
		{
			this.nameAlimentation = name;
		}
		
	*/
	
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

	/*
	 * ICI, la méthode de retour est STRING ! Ca marche donc pour l'affichage (qui affiche le NOM)
	 * MAIS CA MARCHE PAS POUR LE FORMULAIRE QUI A BESOIN D'UN ACCES A LA CLASSE et pas au NOM ! 
	 * Si on le retour par Alimentation/Location/Classification, le formulaire MARCHE ! Mais regarde l'affichage...
	 */
	public String getAlimentation() 
	{
		if(alimentation != null)
			return alimentation.getName();
		else
			return null;
	}

	
	public String getLocation() 
	{
		//return this.location;
		if(location != null)
			return location.getName();
		else
			return null;
			
	}
	public String getClassification() 
	{
		//return this.classification;
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
	
	public String getName () 
	{
		return this.name;
	}	
	
}
