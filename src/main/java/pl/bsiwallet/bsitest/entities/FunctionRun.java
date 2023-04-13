package pl.bsiwallet.bsitest.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "function_run")
public class FunctionRun {

    public enum FunctionName {
        create_password,
        get_session_user_passwords,
        get_decrypted_password,
        edit_password,
        create_user,
        get_all_users,
        get_user_by_login,
        login,
        change_password,
        change_access,
        current_access
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "id_user ")
    private User user;

    @Column(name = "function")
    @Enumerated(EnumType.STRING)
    private FunctionName functionName;

    public FunctionRun() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public FunctionName getFunctionName() {
        return functionName;
    }

    public void setFunctionName(FunctionName functionName) {
        this.functionName = functionName;
    }
}
