package ch.hearc.spring.diconimaux.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.diconimaux.model.Location;

// DAO = Data Acces Object
// Pour accéder aux entités du model
public class LocationsDAO {
	
	private static Map<Integer, Location> locations = new HashMap<>();

	static 
	{
		//locations.put(1, new Location("Inde"));
		//locations.put(2, new Location("Australie"));
		//locations.put(3, new Location("Afrique"));
	}

	public List<Location> getAllLocations() {
		return locations.values().stream().collect(Collectors.toList());
	}
	
	public void save(Location location) {
		locations.put(nextKey(), location);
	}
	
	public Map<Integer, Location> getLocations()
	{
		return locations;
	}
	
	private static Integer nextKey() 
	{
		final Comparator<Integer> comp = (k1, k2) -> Integer.compare( k1, k2);
	    
		return locations.keySet().stream().max(comp).get() + 1;
	}
}
