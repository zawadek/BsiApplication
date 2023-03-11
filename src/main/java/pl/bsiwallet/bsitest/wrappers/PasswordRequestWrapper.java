package pl.bsiwallet.bsitest.wrappers;

public class PasswordRequestWrapper {
    private String password;
    private String webAddress;
    private String description;
    private String login;
    private Integer id;

    public PasswordRequestWrapper() {}

    public PasswordRequestWrapper(String login, String webAddress, String description) {
        this.webAddress = webAddress;
        this.description = description;
        this.login = login;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
