package ch.hearc.spring.diconimaux.jparepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Alimentation;

public interface AlimentationRepository extends JpaRepository<Alimentation, Long> 
{
	@Query(value="SELECT name FROM alimentation alim WHERE alim.id = :alimentation_id")
	List<Alimentation> findByAnimalID(@Param("alimentation_id") Long alimentation_id);
}
