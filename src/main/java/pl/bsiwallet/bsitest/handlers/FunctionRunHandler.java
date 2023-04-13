package pl.bsiwallet.bsitest.handlers;

import pl.bsiwallet.bsitest.builders.FunctionRunBuilder;
import pl.bsiwallet.bsitest.entities.FunctionRun;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.service.FunctionRunService;

import java.util.Date;

public class FunctionRunHandler {

    private final FunctionRunService functionRunService;

    public FunctionRunHandler(FunctionRunService functionRunService) {
        this.functionRunService = functionRunService;
    }

    public void createFunctionRunLog(User user, FunctionRun.FunctionName functionName) {
        functionRunService.createFunctionRunLog(
                new FunctionRunBuilder()
                        .withTime(new Date())
                        .withUser(user)
                        .withFunction(functionName)
                        .get()
        );
    }
}
