package ch.hearc.spring.diconimaux.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.diconimaux.model.Alimentation;

// DAO = Data Acces Object
// Pour accéder aux entités du model
public class FeedsDAO {
	
	private static Map<Integer, Alimentation> feeds = new HashMap<>();

	static 
	{
			
		//feeds.put(1, new Alimentation("Carnivore"));
		//feeds.put(2, new Alimentation("Herbivore"));
		
	}

	public List<Alimentation> getAllFeeds() {
		return feeds.values().stream().collect(Collectors.toList());
	}
	
	public void save(Alimentation feed) {
		feeds.put(nextKey(), feed);
	}
	
	private static Integer nextKey() 
	{
		final Comparator<Integer> comp = (k1, k2) -> Integer.compare( k1, k2);
	    
		return feeds.keySet().stream().max(comp).get() + 1;
	}
}
