package ch.hearc.spring.thymeleaf.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.thymeleaf.model.Feed;

// DAO = Data Acces Object
// Pour accéder aux entités du model
public class FeedsDAO {
	
	private static Map<Integer, Feed> feeds = new HashMap<>();

	static 
	{
			
		feeds.put(1, new Feed("Carnivore"));
		feeds.put(2, new Feed("Herbivore"));
		
	}

	public List<Feed> getAllFeeds() {
		return feeds.values().stream().collect(Collectors.toList());
	}
	
	public void save(Feed feed) {
		feeds.put(nextKey(), feed);
	}
	
	private static Integer nextKey() 
	{
		final Comparator<Integer> comp = (k1, k2) -> Integer.compare( k1, k2);
	    
		return feeds.keySet().stream().max(comp).get() + 1;
	}
}
