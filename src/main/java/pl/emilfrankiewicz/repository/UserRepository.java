package pl.emilfrankiewicz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.emilfrankiewicz.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsUserByUsername(String username);
	boolean existsUserByEmail(String email);
	User findByUsername(String username);
}
