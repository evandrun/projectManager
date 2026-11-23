package com.ece354.projectmanager.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private int taskId;
    private String description;
    private String status;
    private LocalDate dueDate;
    private List<TeamMember> assignees;
    private TeamMember completedBy;
    private boolean lateAlertSent;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public Task(int taskId, String description) {
        this(taskId, description, null);
    }

    public Task(int taskId, String description, String dueDateStr) {
        this.taskId = taskId;
        this.description = description;
        this.status = TaskStatus.NOT_STARTED;
        this.dueDate = (dueDateStr != null && !dueDateStr.isEmpty()) ? LocalDate.parse(dueDateStr, FORMATTER) : null;
        this.assignees = new ArrayList<>();
        this.completedBy = null;
        this.lateAlertSent = false;
    }
    public void addAssignee(TeamMember member) {
        if(member != null && !assignees.contains(member)){
            assignees.add(member);
            member.assignTask(this);
        }
    }
    public List<TeamMember> getAssignees() {
        return assignees;
    }

    public boolean isAssignedTo(TeamMember teamMember) {
        return assignees.contains(teamMember);
    }
    public void setDueDate(String newDueDateStr) {
        this.dueDate = LocalDate.parse(newDueDateStr, FORMATTER);
        if (!LocalDate.now().isAfter(this.dueDate)){
            this.lateAlertSent = false;
        }
        System.out.println("Task #" + taskId + " due date updated to: " + getDueDate());
    }
    public String getDueDate() {
        return dueDate != null ? dueDate.format(FORMATTER) : "N/A";
    }
    public LocalDate getDueDateObject() {
        return dueDate;
    }
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        if (!newStatus.equals(TaskStatus.COMPLETED)) {
            this.completedBy = null;
        }
        System.out.println("Task " + taskId + " Status updated to: " + status);
    }
    public void updateStatus(String newStatus, TeamMember updater) {
        this.status = newStatus;
        if (TaskStatus.COMPLETED.equals(newStatus)) {
            this.completedBy = updater;
        }
        else {
            this.completedBy = null;
        }
        System.out.println("Task " + taskId + " status updated to: " + status + " by " + updater.getUsername());
    }

    public TeamMember getCompletedBy() {
        return completedBy;
    }

    public String getStatus() {
        return status;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLate() {
        return !status.equals(TaskStatus.COMPLETED) && dueDate != null && LocalDate.now().isAfter(dueDate);
    }

    public boolean isLateAlertSent() {
        return lateAlertSent;
    }

    public void setLateAlertSent(boolean lateAlertSent){
        this.lateAlertSent = lateAlertSent;
    }
}
