package pl.bsiwallet.bsitest.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "data_change")
public class DataChange {

    public enum ActionType {
        Create,
        Update,
        Delete
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "previous_value_of_record")
    private String previousValueOfRecord;

    @Column(name = "present_value_of_record")
    private String presentValueOfRecord;

    @Column(name = "time")
    private Date time;

    @Column(name = "action_type")
    @Enumerated(EnumType.STRING)
    private ActionType actionType;

    @ManyToOne
    @JoinColumn(name = "id_user ")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_message_record")
    private Password password;

    public DataChange() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPreviousValueOfRecord() {
        return previousValueOfRecord;
    }

    public void setPreviousValueOfRecord(String previousValueOfRecord) {
        this.previousValueOfRecord = previousValueOfRecord;
    }

    public String getPresentValueOfRecord() {
        return presentValueOfRecord;
    }

    public void setPresentValueOfRecord(String presentValueOfRecord) {
        this.presentValueOfRecord = presentValueOfRecord;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Password getPasswordRecord() {
        return password;
    }

    public void setPasswordRecord(Password passwordRecord) {
        this.password = passwordRecord;
    }
}
