package ch.hearc.spring.diconimaux.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> 
{
	@Query("SELECT loc FROM location loc WHERE loc.id = :location_id")
	List<Location> findByAnimalID(@Param("location_id") Long location_id);
	
}