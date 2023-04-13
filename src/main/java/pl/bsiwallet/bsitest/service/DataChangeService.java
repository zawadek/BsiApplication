package pl.bsiwallet.bsitest.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.bsiwallet.bsitest.entities.DataChange;
import pl.bsiwallet.bsitest.interfaces.DataChangeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataChangeService {

    private final DataChangeRepository repository;

    public DataChangeService(DataChangeRepository repository) {
        this.repository = repository;
    }

    public void createDataChangeLog(DataChange dataChange) {
        repository.save(dataChange);
    }

    public ResponseEntity<?> getAllDataChangeLogs() {
        List<DataChange> logs = repository.findAll();
        if (!logs.isEmpty()) {
            return new ResponseEntity<>(logs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("We couldn't find any data change logs.", HttpStatus.NOT_FOUND);
        }
    }
}
