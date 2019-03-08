package ch.hearc.spring.thymeleaf.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.thymeleaf.model.Feed;
import ch.hearc.spring.thymeleaf.model.Animal;
import ch.hearc.spring.thymeleaf.model.Classification;
import ch.hearc.spring.thymeleaf.model.Location;

// DAO = Data Acces Object
// Pour accéder aux entités du model
public class AnimalsDAO {
	
	private static Map<Integer, Location> locations = new HashMap<>();
	private static Map<Integer, Classification> classifications = new HashMap<>();
	private static Map<Integer, Feed> alimentations = new HashMap<>();
	private static Map<Integer, Animal> animals = new HashMap<>();


	static 
	{
		LocationsDAO dao = null;
		locations = dao.getLocations();
		//???
		
		//locations.put(1, new Location("Inde"));
		//locations.put(2, new Location("Australie"));
		//locations.put(3, new Location("Afrique"));
		
		
		alimentations.put(1, new Feed("Carnivore"));
		alimentations.put(2, new Feed("Herbivore"));
		
		classifications.put(1, new Classification("Mamifère"));
		classifications.put(2, new Classification("Reptile"));

		
		
		animals.put(1, new Animal("Tigre", "Un tigre c'est cool", 230, 140, locations.get(1), alimentations.get(1), classifications.get(1)));
		animals.put(2, new Animal("Crocodile", "Un crocodile c'est dangereux", 500, 400, locations.get(2), alimentations.get(1), classifications.get(2)));
		animals.put(3, new Animal("Antilope", "Une antilope c'est rapide", 150, 5, locations.get(3), alimentations.get(2), classifications.get(1)));
		
		//animals.put(2, new Animal("Veronique D.",19,'F'));
		
	}

	public List<Animal> getAllAnimals() {
		return animals.values().stream().collect(Collectors.toList());
	}
	
	public void save(Animal animal) {
		animals.put(nextKey(), animal);
	}
	
	private static Integer nextKey() 
	{
		final Comparator<Integer> comp = (k1, k2) -> Integer.compare( k1, k2);
	    
		return animals.keySet().stream().max(comp).get() + 1;
	}
}
