package pl.bsiwallet.bsitest.utils;

import pl.bsiwallet.bsitest.entities.User;

public final class UserSession {
    private static UserSession instance;

    private static User user;
    private static Boolean isValid;

    private UserSession() {
        isValid = false;
    }

    public static UserSession getSession() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public static UserSession refreshSession() {
        instance = new UserSession();
        return instance;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        UserSession.user = user;
    }

    public static Boolean getIsValid() {
        return isValid;
    }

    public static void setIsValid(Boolean isValid) {
        UserSession.isValid = isValid;
    }
}