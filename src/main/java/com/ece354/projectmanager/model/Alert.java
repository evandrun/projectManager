package com.ece354.projectmanager.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Alert {
    private static int nextId = 1;

    private final int alertId;
    private final String message;
    private final LocalDateTime createdAt;
    private boolean isRead;

    public Alert(String message) {
        this.alertId = nextId++;
        this.message = message;
        this.createdAt = LocalDateTime.now();
        this.isRead = false;
    }

    public int getAlertId() { return alertId; }
    public String getMessage() { return message; }
    public boolean isRead() { return isRead; }
    public void markAsRead() { this.isRead = true; }

    @Override
    public String toString() {
        String timeStr = createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return "[ALERT #" + alertId + " | " + timeStr + "] " + message;
    }
}

