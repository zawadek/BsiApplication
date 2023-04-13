package pl.bsiwallet.bsitest.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bsiwallet.bsitest.entities.DataChange;

@Repository
public interface DataChangeRepository extends JpaRepository<DataChange,Integer> {
}
