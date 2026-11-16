package com.ece354.projectmanager.service;

import com.ece354.projectmanager.model.Manager;
import com.ece354.projectmanager.model.Project;
import com.ece354.projectmanager.model.Task;
import com.ece354.projectmanager.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.List;

// Holds the app's data in memory and loads demo data when the app starts.
// There is no database: restarting the app resets everything to this demo data.
@Service
public class ProjectService {

    private final Manager manager;

    public ProjectService() {
        manager = new Manager("Ethan_mgr", "Password06");
        loadDemoData();
    }

    private void loadDemoData() {
        Project fair = manager.createProject("Spring Fair",
                "Plan the booths, volunteers, and food for the spring campus fair.",
                "04-01-2027", "04-17-2027");
        Task venue = new Task(1, "Book the venue");
        venue.updateStatus(TaskStatus.COMPLETED);
        fair.getTasks().add(venue);
        fair.getTasks().add(new Task(2, "Order food and drinks"));

        Project website = manager.createProject("Website Redesign",
                "Rebuild the company website with the new branding.",
                "11-01-2026", "12-15-2026");
        Task about = new Task(1, "Write content for the About page");
        about.updateStatus(TaskStatus.COMPLETED);
        website.getTasks().add(about);
        Task homepage = new Task(2, "Build the new homepage from the approved mockup");
        homepage.updateStatus(TaskStatus.IN_PROGRESS);
        website.getTasks().add(homepage);
    }

    public Manager getManager() {
        return manager;
    }

    public List<Project> getProjects() {
        return manager.getProjects();
    }
}
