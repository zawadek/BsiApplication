package pl.bsiwallet.bsitest.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.bsiwallet.bsitest.entities.FunctionRun;
import pl.bsiwallet.bsitest.interfaces.FunctionRunRepository;

import java.util.List;

@Service
public class FunctionRunService {

    private final FunctionRunRepository repository;

    public FunctionRunService(FunctionRunRepository repository) {
        this.repository = repository;
    }

    public void createFunctionRunLog(FunctionRun dataChange) {
        repository.save(dataChange);
    }

    public ResponseEntity<?> getAllFunctionRunLogs() {
        try {
            List<FunctionRun> logs = repository.findAll();
            if (!logs.isEmpty()) {
                return new ResponseEntity<>(logs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("We couldn't find any function run logs.", HttpStatus.NOT_FOUND);
            }
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
