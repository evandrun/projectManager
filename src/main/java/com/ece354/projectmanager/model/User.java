package com.ece354.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    protected String username;
    protected String password;
    protected List<Alert> alerts;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.alerts = new ArrayList<>();
    }
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public String getUsername() { return username;}

    public String getUserDetails() {
        return "User: " + username;
    }

    public boolean isManager() {
        return false;
    }

    public List<Alert> getAlerts() { return alerts; }

    public void addAlert(String message) {
        alerts.add(new Alert(message));
    }

    public void displayAlerts() {
        System.out.println("Alerts for " + username);
        if(alerts.isEmpty()) {
            System.out.println("No alerts.");
            return;
        }
        for (Alert alert : alerts) {
            System.out.println(alert);
        }
    }
}
