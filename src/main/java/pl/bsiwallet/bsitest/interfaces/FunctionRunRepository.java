package pl.bsiwallet.bsitest.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bsiwallet.bsitest.entities.FunctionRun;

@Repository
public interface FunctionRunRepository extends JpaRepository<FunctionRun, Integer> {
}