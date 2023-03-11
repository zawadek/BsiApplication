package pl.bsiwallet.bsitest.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "login", unique = true, length = 40)
    private String login;

    @Column(name = "password_hash", length = 512)
    private String passwordHash;

    @Column(name = "salt", length = 30)
    private String salt;

    @Column(name = "is_kept_as_hash")
    private Boolean isKeptAsHash;

    public User() {}

    public User(String login, Boolean isKeptAsHash) {
        this.login = login;
        this.isKeptAsHash = isKeptAsHash;
    }

    public User(String login, String passwordHash, String salt, Boolean isKeptAsHash) {
        this.login = login;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.isKeptAsHash = isKeptAsHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getKeptAsHash() {
        return isKeptAsHash;
    }

    public void setKeptAsHash(Boolean keptAsHash) {
        isKeptAsHash = keptAsHash;
    }
}
