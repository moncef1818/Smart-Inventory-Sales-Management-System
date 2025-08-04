package utils;
import model.User;
public class SessionManager {
    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static void clearSession() {
        currentUser = null;
    }
}

