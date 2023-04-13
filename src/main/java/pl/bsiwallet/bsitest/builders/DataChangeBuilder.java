package pl.bsiwallet.bsitest.builders;

import pl.bsiwallet.bsitest.entities.DataChange;
import pl.bsiwallet.bsitest.entities.Password;
import pl.bsiwallet.bsitest.entities.User;

import java.util.Date;

public class DataChangeBuilder {

    private DataChange dataChange;

    public DataChangeBuilder() {
        dataChange = new DataChange();
    }

    public DataChangeBuilder withPreviousValueOfRecord(String previousValueOfRecord) {
        dataChange.setPreviousValueOfRecord(previousValueOfRecord);
        return this;
    }

    public DataChangeBuilder withPresentValueOfRecord(String presentValueOfRecord) {
        dataChange.setPresentValueOfRecord(presentValueOfRecord);
        return this;
    }

    public DataChangeBuilder withTme(Date time) {
        dataChange.setTime(time);
        return this;
    }

    public DataChangeBuilder withActionType(DataChange.ActionType actionType) {
        dataChange.setActionType(actionType);
        return this;
    }

    public DataChangeBuilder withUser(User user) {
        dataChange.setUser(user);
        return this;
    }

    public DataChangeBuilder withPassword(Password password) {
        dataChange.setPasswordRecord(password);
        return this;
    }

    public DataChange get() {
        return dataChange;
    }

    public void reset() {
        dataChange = new DataChange();
    }
}
