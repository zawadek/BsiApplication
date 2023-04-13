package pl.bsiwallet.bsitest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bsiwallet.bsitest.service.DataChangeService;

@RestController
@RequestMapping(path = "logs")
@AllArgsConstructor
public class DataChangeController {

    private final DataChangeService dataChangeService;

    @GetMapping("/dataChange")
    public ResponseEntity<?> getAllDataChangeLogs() {
        return dataChangeService.getAllDataChangeLogs();
    }
}
