package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ch.hearc.spring.diconimaux.model.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> 
{
	@Query(value="SELECT * FROM classification cla WHERE cla.name = :classificationName", nativeQuery=true)
	Classification findByName(@Param("classificationName") String classificationName);
}
