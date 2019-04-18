package ch.hearc.spring.diconimaux.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Alimentation;
import ch.hearc.spring.diconimaux.model.Animal;
import ch.hearc.spring.diconimaux.model.Classification;
import ch.hearc.spring.diconimaux.model.Location;

public interface AnimalRepository extends JpaRepository<Animal, Long>
{
	@Query(value="SELECT animal.id, animal.name, animal.description,  alimentation.name, location.name, classification.name, animal.weight, animal.height"
			+ " animal.alimentation_id, animal.location_id, animal.classification_id,"
			+ " animal.alimentation_name, animal.location_name, animal.classification_name, "
			+ " FROM animal"
			+ " INNER JOIN alimentation ON animal.alimentation_name=alimentation.name"
			+ " INNER JOIN location ON animal.location_name=location.name"
			+ " INNER JOIN classification ON animal.classification_name=classification.name", nativeQuery=true)
	List<Animal> getAllAnimalsWithoutID();
	
	
	List<Animal> findByLocation(Location loc);
	List<Animal> findByClassification(Classification cla);
	List<Animal> findByAlimentation(Alimentation ali);
	
	@Query(value="SELECT * FROM animal WHERE animal.name = :name", nativeQuery=true)
	List<Animal> findByName(@Param("name") String name);
}
