package pl.bsiwallet.bsitest.wrappers;

public class PasswordEncryptionUpdateWrapper {

    private String oldPassword;
    private String newPassword;

    public PasswordEncryptionUpdateWrapper() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
