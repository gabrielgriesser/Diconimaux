package ch.hearc.spring.diconimaux.jparepository;

import ch.hearc.spring.diconimaux.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}