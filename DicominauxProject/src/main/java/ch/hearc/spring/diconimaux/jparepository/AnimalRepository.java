package ch.hearc.spring.diconimaux.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>
{
	/*
	@Query(value="SELECT animal.id, animal.name, animal.description,  alimentation.name, location.name, classification.name, animal.weight, animal.height"
			+ " animal.alimentation_id, animal.location_id, animal.classification_id,"
			+ " animal.alimentation_name, animal.location_name, animal.classification_name, "
			+ " FROM animal"
			+ " INNER JOIN alimentation ON animal.alimentation_name=alimentation.name"
			+ " INNER JOIN location ON animal.location_name=location.name"
			+ " INNER JOIN classification ON animal.classification_name=classification.name", nativeQuery=true)
	List<Animal> getAllAnimalsWithoutID(Pageable pageable);
	*/
	
	@Query(value="SELECT * FROM animal a, location l WHERE a.location_id = l.id AND l.name= :name", nativeQuery=true)
	List<Animal> findByLocation(@Param("name") String name);
	
	@Query(value="SELECT * FROM animal WHERE animal.name = :name", nativeQuery=true)
	List<Animal> findByName(@Param("name") String name);
	
	@Query(value="SELECT * FROM animal ani, location loc, classification cla, alimentation ali"
			+ " WHERE ani.location_id = loc.id AND ani.classification_id = cla.id AND ani.alimentation_id = ali.id"
			+ " AND ani.name = :animalName"
			+ " AND loc.name = :locationName"
			+ " AND cla.name = :classificationName"
			+ " AND ali.name = :alimentationName", nativeQuery=true)
	List<Animal> findByQuery(@Param("animalName") String animalName, @Param("locationName") String locationName, @Param("classificationName") String classificationName, @Param("alimentationName") String alimentationName);
}
