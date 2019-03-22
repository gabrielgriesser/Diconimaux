package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.spring.diconimaux.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> 
{
	//TODO
	// Requêtes personnalisées (??)
}