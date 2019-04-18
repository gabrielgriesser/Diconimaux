package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.spring.diconimaux.model.Alimentation;

public interface AlimentationRepository extends JpaRepository<Alimentation, Long> 
{

}
