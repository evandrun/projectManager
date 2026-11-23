package com.ece354.projectmanager.model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends User {
    private List<Project> projectList;

    public Manager(String username, String password){
        super(username, password);
        this.projectList = new ArrayList<>();
    }

    @Override
    public boolean isManager() {
        return true;
    }

    public Project createProject(String name, String description, String startDate, String dueDate) {
        Project newProject = new Project(projectList.size() +1, name, description, startDate, dueDate, this);
        if (!newProject.validateDetails()) {
            System.out.println("Error: All 4 project details need to be filled.");
            return null;
        }

        projectList.add(newProject);
        System.out.println("Success: Project '" + newProject.getName() + "' created and added to manager's project list.");
        return newProject;
    }

    public Task assignTaskToProject(Project project, String description, String dueDate, List<TeamMember> assignees){
        if (!projectList.contains(project)) {
            System.out.println("Error: Project not managed by " + username);
            return null;
        }
        return project.createTask(description, dueDate, assignees);
    }

    public void updateProjectDueDate(Project project, String newDueDate) {
        project.setDueDate(newDueDate);
    }

    public void updateTaskDueDate(Task task, String newDueDate){
        task.setDueDate(newDueDate);
    }

    public void editProject(Project project) {
        System.out.println("Editing project: " + project.getName());
    }

    public List<Project> getProjects() {
        return projectList;
    }

    public void displayDashboard() {
        System.out.println("~~~~~~~~~~");
        System.out.println("MANAGER MAIN SCREEN - Projects for " + username);
        System.out.println("~~~~~~~~~~");
        if (projectList.isEmpty()) {
            System.out.println("No active projects.");
            return;
        }
        System.out.printf("%-22s | %-10s | %-11s | %-14s%n", "Project Name", "% Done", "Late Tasks", "Next Due Date");
        for (Project p : projectList) {
            int lateTasks = p.checkAndCountLateTasks();
            int progress = p.getProgress();
            String nextDue = p.getNextDueDate();
            System.out.printf("%-22s | %-10s | %-11s | %-14s%n", p.getName(), progress, lateTasks, nextDue);
        }
    }

    public void displayTeamworkload(List<TeamMember> teamMembers){
        System.out.println("Team Workload (Unfinished Tasks)");
        for (TeamMember member : teamMembers) {
            System.out.println(member.getUsername() + ": " + member.getUnfinishedTaskCount() + " unfinished task(s)");
        }
    }
}
