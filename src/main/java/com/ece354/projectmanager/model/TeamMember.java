package com.ece354.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

public class TeamMember extends User {
    private List<Task> assignedTasks;

    public TeamMember(String username, String password) {
        super(username, password);
        this.assignedTasks = new ArrayList<>();
    }

    public void assignTask(Task task) {
        if (!assignedTasks.contains(task)){
            assignedTasks.add(task);
        }
    }

    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void updateTask(Task task, String newStatus) {
        if (task.isAssignedTo(this)) {
            task.updateStatus(newStatus, this);
        }
        else {
            System.out.println("Access Denied: Not your task.");
        }
    }

    public int getUnfinishedTaskCount() {
        int count = 0;
        for (Task t : assignedTasks) {
            if (!t.getStatus().equals(TaskStatus.COMPLETED)){
                count++;
            }
        }
        return count;
    }

    public void viewMyTasks() {
        System.out.println("Tasks for " + username + "(Unfinished: " + getUnfinishedTaskCount() + ")");
        if (assignedTasks.isEmpty()) {
            System.out.println("No tasks assigned.");
            return;
        }
        for (Task t : assignedTasks) {
            String lateTag = t.isLate() ? " [LATE]" : "";
            System.out.println("Task #" + t.getTaskId() + ": " + t.getDescription() + " | Due: " + t.getDueDate() + " | Status: " + t.getStatus() + lateTag);
        }
    }
}
