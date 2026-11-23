package com.ece354.projectmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;

public class Project {
    private int projectId;
    private String name;
    private String description;
    private String startDate;
    private String dueDate;
    private int overallProgress;
    private List<Task> tasks;
    private Manager manager;
    private final Map<String, Integer> contributionPercentages = new HashMap<>();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    public Project(int projectId, String name, String description, String startDate, String dueDate, Manager manager) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.overallProgress = 0;
        this.tasks = new ArrayList<>();
        this.manager = manager;
    }

    public boolean validateDetails() {
        return name != null && !name.isEmpty() && description != null && !description.isEmpty() && startDate != null && dueDate != null;
    }

    public Task createTask(String description, String dueDateStr, List<TeamMember> assignees){
        Task newTask = new Task(tasks.size() + 1, description, dueDateStr);
        if(assignees != null) {
            for(TeamMember member : assignees){
                newTask.addAssignee(member);
            }
        }
        tasks.add(newTask);
        refreshProgress();
        System.out.println("Task#" + newTask.getTaskId() + " ('" + description + "') added to project '" + name + "'.");
        return newTask;
    }

    public String getDueDate() {
        return dueDate != null ? dueDate : "N/A";
    }

    public String getStartDate() {
        return startDate != null ? startDate : "N/A";
    }

    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public int getProgress() {
        refreshProgress();
        return overallProgress;
    }

    public void refreshProgress() {     //Used AI, Prompt: can I get help making my refreshProgress class for my project manager code work correctly
        if (tasks.isEmpty()) {
            overallProgress = 0;
            return;
        }
        int completedCount = 0;
        for (Task t : tasks) {
            if (t.getStatus().equals(TaskStatus.COMPLETED)) {
                completedCount++;
            }
        }
        overallProgress = (int) ((double) completedCount / tasks.size() * 100);
    }

    public Map<String, Integer> getMemberContributions() {
        Map<String, Integer> completedByMember = new HashMap<>();
        contributionPercentages.clear();
        int totalCompleted = 0;

        for (Task t : tasks) {
            if (t.getStatus().equals(TaskStatus.COMPLETED)) {
                totalCompleted++;
                if (t.getCompletedBy() != null) {
                    String user = t.getCompletedBy().getUsername();
                    completedByMember.put(user, completedByMember.getOrDefault(user, 0) + 1);
                }
                else if(!t.getAssignees().isEmpty()){
                    String user = t.getAssignees().get(0).getUsername();
                    completedByMember.put(user, completedByMember.getOrDefault(user, 0) + 1);
                }
            }
        }
        if(totalCompleted == 0) {
            return contributionPercentages;
        }
        for (Map.Entry<String, Integer> entry : completedByMember.entrySet()){
            int pct = (int) Math.round((double) entry.getValue() / totalCompleted * 100);
            contributionPercentages.put(entry.getKey(), pct);
        }
        return contributionPercentages;
    }

    public void displayMemberContributions() {
        System.out.println("Contribution Breakdown for '" + name + "'");
        Map<String, Integer> contributions = getMemberContributions();
        if (contributions.isEmpty()) {
            System.out.println("No completed tasks yet (0%).");
            return;
        }
        for (Map.Entry<String, Integer> entry : contributions.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "% of finished work");
        }
    }

    public int checkAndCountLateTasks(){
        int lateCount = 0;
        for (Task t : tasks) {
            if (t.isLate()) {
                lateCount++;
                if (!t.isLateAlertSent()){
                    String msg = "Task #" + t.getTaskId() + " ('" + t.getDescription() + "') in project '" + name + "'is LATE (Due: " + t.getDueDate() + ").";
                    if (manager != null) {
                        manager.addAlert(msg);
                    }
                    for (TeamMember member : t.getAssignees()) {
                        member.addAlert(msg);
                    }
                    t.setLateAlertSent(true);
                }
            }
        }
        return lateCount;
    }

    public String getNextDueDate() {
        LocalDate earliest = null;
        for (Task t : tasks) {
            if (!t.getStatus().equals(TaskStatus.COMPLETED)) {
                LocalDate taskDue = t.getDueDateObject();
                if (taskDue != null && (earliest == null || taskDue.isBefore(earliest))) {
                    earliest = taskDue;
                }
            }
        }
        if (earliest != null) {
            return earliest.format(FORMATTER);
        }
        return getDueDate();
    }

    public int getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
