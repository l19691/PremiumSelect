package util;

import domain.User;
import engine.DealEngine;
import engine.FlatDiscountDeal;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users = new HashMap<>();
    private User currentUser;

    public boolean login(String username, String role) {
        username = username.toLowerCase();
        if (users.containsKey(username)) {
            currentUser = users.get(username);
            return true;
        }

        // New user registration
        DealEngine defaultDeal = new FlatDiscountDeal(10.0); // default 10% off
        User newUser = new User(username, role, 100.0, defaultDeal); // default €100 wallet
        users.put(username, newUser);
        currentUser = newUser;
        LoggerUtility.log("Created new user: " + username + " as " + role);
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void switchUser(String username) {
        if (users.containsKey(username.toLowerCase())) {
            currentUser = users.get(username.toLowerCase());
        }
    }

    public Map<String, User> getAllUsers() {
        return users;
    }

    public void logout() {
        currentUser = null;
    }
}
