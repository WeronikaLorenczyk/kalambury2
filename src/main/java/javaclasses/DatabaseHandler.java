package javaclasses;


import java.util.HashMap;

public class DatabaseHandler {

    static HashMap<String, UserInfo> users = new HashMap<>();


    static {
        users.put("a", new UserInfo("a", "a"));
        users.put("b", new UserInfo("b", "b"));
        users.put("c", new UserInfo("c", "c"));
    }

    public static boolean addUser(UserInfo ui) {
        if (ui.getAge() < 0)
            return false;
        users.put(ui.getLogin(), ui);
        return true;
    }

    public static boolean userExist(String login, String password) {
        UserInfo u = users.get(login);
        if (u != null && u.getPassword().equals(password))
            return true;
        return false;
    }

    public static UserInfo getUser(String login) {
        return users.get(login);
    }


}
