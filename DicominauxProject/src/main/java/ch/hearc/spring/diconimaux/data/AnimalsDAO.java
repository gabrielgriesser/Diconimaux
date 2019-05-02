package ch.hearc.spring.diconimaux.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.diconimaux.model.Alimentation;
import ch.hearc.spring.diconimaux.model.Animal;
import ch.hearc.spring.diconimaux.model.Classification;
import ch.hearc.spring.diconimaux.model.Location;

// DAO = Data Acces Object
// Pour accéder aux entités du model
@SuppressWarnings("null")
public class AnimalsDAO {
	
	private static Map<Integer, Location> locations = new HashMap<>();
	private static Map<Integer, Classification> classifications = new HashMap<>();
	private static Map<Integer, Alimentation> alimentations = new HashMap<>();
	private static Map<Integer, Animal> animals = new HashMap<>();

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
