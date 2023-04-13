package pl.bsiwallet.bsitest.builders;

import pl.bsiwallet.bsitest.entities.FunctionRun;
import pl.bsiwallet.bsitest.entities.User;

import java.util.Date;

public class FunctionRunBuilder {

    private FunctionRun functionRun;

    public FunctionRunBuilder() {
        functionRun = new FunctionRun();
    }

    public FunctionRunBuilder withTime(Date time) {
        functionRun.setTime(time);
        return this;
    }

    public FunctionRunBuilder withUser(User user) {
        functionRun.setUser(user);
        return this;
    }

    public FunctionRunBuilder withFunction(FunctionRun.FunctionName functionName) {
        functionRun.setFunctionName(functionName);
        return this;
    }

    public FunctionRun get() {
        return functionRun;
    }

    public void reset() {
        functionRun = new FunctionRun();
    }
}
