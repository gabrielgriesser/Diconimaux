package ch.hearc.spring.thymeleaf.data;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ch.hearc.spring.thymeleaf.model.Classification;

// DAO = Data Acces Object
// Pour accéder aux entités du model
public class ClassificationsDAO 
{
	
	private static Map<Integer, Classification> classifications = new HashMap<>();

	static 
	{				
		classifications.put(1, new Classification("Mamifère"));
		classifications.put(2, new Classification("Reptile"));	
	}

	public List<Classification> getAllClassifications() {
		return classifications.values().stream().collect(Collectors.toList());
	}
	
	public void save(Classification classification) {
		classifications.put(nextKey(), classification);
	}
	
	private static Integer nextKey() 
	{
		final Comparator<Integer> comp = (k1, k2) -> Integer.compare( k1, k2);
	    
		return classifications.keySet().stream().max(comp).get() + 1;
	}
}
