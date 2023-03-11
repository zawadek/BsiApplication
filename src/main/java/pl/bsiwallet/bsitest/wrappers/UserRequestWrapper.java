package pl.bsiwallet.bsitest.wrappers;

public class UserRequestWrapper {
    private String login;
    private String password;
    private Boolean isHmac;

    public UserRequestWrapper() {}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsHmac() {
        return isHmac;
    }

    public void setIsHmac(Boolean hmac) {
        isHmac = hmac;
    }
}
