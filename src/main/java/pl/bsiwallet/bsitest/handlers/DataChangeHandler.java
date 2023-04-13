package pl.bsiwallet.bsitest.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import pl.bsiwallet.bsitest.builders.DataChangeBuilder;
import pl.bsiwallet.bsitest.entities.DataChange;
import pl.bsiwallet.bsitest.entities.Password;
import pl.bsiwallet.bsitest.entities.User;
import pl.bsiwallet.bsitest.service.DataChangeService;
import pl.bsiwallet.bsitest.utils.Utils;

import java.util.Date;

public class DataChangeHandler {

    private final DataChangeService dataChangeService;

    public DataChangeHandler(DataChangeService dataChangeService) {
        this.dataChangeService = dataChangeService;
    }

    public void createDataChangeLog(Object presentValue, DataChange.ActionType actionType, User user, Password password) throws JsonProcessingException {
        dataChangeService.createDataChangeLog(
                new DataChangeBuilder()
                        .withTme(new Date())
                        .withPresentValueOfRecord(Utils.changeObjectToJSON(presentValue))
                        .withActionType(actionType)
                        .withUser(user)
                        .withPassword(password)
                        .get()
        );
    }

    public void createDataChangeLog(Object previousValue, Object presentValue, DataChange.ActionType actionType,
                                    User user, Password password) throws JsonProcessingException {
        dataChangeService.createDataChangeLog(
                new DataChangeBuilder()
                        .withTme(new Date())
                        .withPreviousValueOfRecord(Utils.changeObjectToJSON(previousValue))
                        .withPresentValueOfRecord(Utils.changeObjectToJSON(presentValue))
                        .withActionType(actionType)
                        .withUser(user)
                        .withPassword(password)
                        .get()
        );
    }

    public void createDataChangeLog(Object presentValue, DataChange.ActionType actionType, User user) throws JsonProcessingException {
        dataChangeService.createDataChangeLog(
                new DataChangeBuilder()
                        .withTme(new Date())
                        .withPresentValueOfRecord(Utils.changeObjectToJSON(presentValue))
                        .withActionType(actionType)
                        .withUser(user)
                        .get()
        );
    }

    public void createDataChangeLog(Object previousValue, Object presentValue, DataChange.ActionType actionType, User user) throws JsonProcessingException {
        dataChangeService.createDataChangeLog(
                new DataChangeBuilder()
                        .withTme(new Date())
                        .withPreviousValueOfRecord(Utils.changeObjectToJSON(previousValue))
                        .withPresentValueOfRecord(Utils.changeObjectToJSON(presentValue))
                        .withActionType(actionType)
                        .withUser(user)
                        .get()
        );
    }
}
