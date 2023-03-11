package pl.bsiwallet.bsitest.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bsiwallet.bsitest.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
