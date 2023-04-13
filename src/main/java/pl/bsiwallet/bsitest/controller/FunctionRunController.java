package pl.bsiwallet.bsitest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bsiwallet.bsitest.service.FunctionRunService;

@RestController
@RequestMapping(path = "logs")
@AllArgsConstructor
public class FunctionRunController {

    private final FunctionRunService functionRunService;

    @GetMapping("/functionRun")
    public ResponseEntity<?> getAllFunctionRunLogs() {
        return functionRunService.getAllFunctionRunLogs();
    }
}
