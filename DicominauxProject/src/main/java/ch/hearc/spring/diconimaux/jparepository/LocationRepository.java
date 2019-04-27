package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> 
{
	@Query(value="SELECT * FROM location loc WHERE loc.name = :locationName", nativeQuery=true)
	Location findByName(@Param("locationName") String locationName);
}