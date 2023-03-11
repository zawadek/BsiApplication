package pl.bsiwallet.bsitest.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.bsiwallet.bsitest.entities.Password;
import java.util.List;
import java.util.Optional;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
    Optional<List<Password>> findByUserId(Integer userId);
    Optional<Password> findById(Integer id);
}
