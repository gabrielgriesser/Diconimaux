package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Alimentation;

public interface AlimentationRepository extends JpaRepository<Alimentation, Long> 
{
	@Query(value="SELECT * FROM alimentation ali WHERE ali.name = :alimentationName", nativeQuery=true)
	Alimentation findByName(@Param("alimentationName") String alimentationName);
}
