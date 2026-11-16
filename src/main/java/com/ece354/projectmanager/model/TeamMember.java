package com.ece354.projectmanager.model;

public class TeamMember extends User {
    public TeamMember(String username, String password) {
        super(username, password);
    }

    public void updateTask(Task task, String newStatus) {
        if (task.isAssignedTo(this)) {
            task.updateStatus(newStatus);
        }
        else {
            System.out.println("Access Denied: Not your task.");
        }
    }
}
