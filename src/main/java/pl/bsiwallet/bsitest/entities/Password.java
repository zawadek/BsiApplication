package pl.bsiwallet.bsitest.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "password")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "password", length = 512)
    private String password;

    @Column(name = "web_address", length = 512)
    private String webAddress;

    @Column(name = "description", length = 512)
    private String description;

    @Column(name = "login", length = 40)
    private String login;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Password() {}

    public Password(Password password) {
        this.id = password.id;
        this.webAddress = password.webAddress;
        this.description = password.description;
        this.login = password.login;
        this.user = password.user;
        this.password = password.password;
    }

    public Password(String webAddress, String description, String login, User user) {
        this.webAddress = webAddress;
        this.description = description;
        this.login = login;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWebAddress() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress = webAddress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
