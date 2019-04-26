package ch.hearc.spring.diconimaux.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>
{

	
	@Query(value="SELECT * FROM animal ani, location loc, classification cla, alimentation ali"
			+ " WHERE ani.location_id = loc.id AND ani.classification_id = cla.id AND ani.alimentation_id = ali.id"
			+ " AND ani.name LIKE CONCAT('%',:animalName,'%')"
			+ " AND loc.name LIKE CONCAT('%',:locationName,'%')"
			+ " AND cla.name LIKE CONCAT('%',:classificationName,'%')"
			+ " AND ali.name LIKE CONCAT('%',:alimentationName,'%')", nativeQuery=true)
	List<Animal> findByQuery
			(
			@Param("animalName") String animalName, 
			@Param("locationName") String locationName, 
			@Param("classificationName") String classificationName, 
			@Param("alimentationName") String alimentationName
			);
}
