package com.ece354.projectmanager.controller;

import com.ece354.projectmanager.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Handles http://localhost:8080/ and shows the list of projects.
@Controller
public class HomeController {

    private final ProjectService projectService;

    // Spring passes in the ProjectService automatically.
    public HomeController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("projects", projectService.getProjects());
        return "index"; // shows src/main/resources/templates/index.html
    }
}
