package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.spring.diconimaux.model.Classification;

public interface ClassificationRepository extends JpaRepository<Classification, Long> {
	
}
