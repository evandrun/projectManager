package com.ece354.projectmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjectmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectmanagerApplication.class, args);
    }

    // Prints a clickable link once the server is ready
    @EventListener(ApplicationReadyEvent.class)
    public void printLink() {
        System.out.println();
        System.out.println("  Project Manager is running: http://localhost:8080");
        System.out.println();
    }
}