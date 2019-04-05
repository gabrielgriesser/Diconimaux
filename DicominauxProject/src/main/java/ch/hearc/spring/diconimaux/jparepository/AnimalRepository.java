package ch.hearc.spring.diconimaux.jparepository;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.hearc.spring.diconimaux.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long>
{
}
