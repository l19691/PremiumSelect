package domain;

import cart.GroupCart;
import data.UserPantry;
import engine.DealEngine;

public class User {
    private final String username;
    private final String role; // "admin" or "customer"
    private double wallet;
    private final UserPantry pantry;
    private final GroupCart cart;

    private UserObserver observer; // For Observer pattern

    public User(String username, String role, double startingWallet, DealEngine dealEngine) {
        this.username = username;
        this.role = role.toLowerCase();
        this.wallet = startingWallet;
        this.pantry = new UserPantry();
        this.cart = new GroupCart(dealEngine, 1); // default group size = 1
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public double getWallet() {
        return wallet;
    }

    public void addFunds(double amount) {
        if (amount > 0) {
            wallet += amount;
            if (observer != null) {
                observer.notify("💳 Funds added: €" + amount + " | New balance: €" + wallet);
            }
        }
    }

    public boolean deduct(double amount) {
        if (amount <= wallet) {
            wallet -= amount;
            if (observer != null && wallet < 10) {
                observer.notify("⚠️ Wallet low: €" + wallet);
            }
            return true;
        }
        return false;
    }

    public UserPantry getPantry() {
        return pantry;
    }

    public GroupCart getCart() {
        return cart;
    }

    public void setObserver(UserObserver observer) {
        this.observer = observer;
    }

    @Override
    public String toString() {
        return "User: " + username + " | Role: " + role + " | Wallet: €" + String.format("%.2f", wallet);
    }
}
