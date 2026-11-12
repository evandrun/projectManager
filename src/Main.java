public class Main {
    public static void main(String[] args) {
        System.out.println("Project Manager v0.1");

        Manager manager = new Manager("Ethan_mgr", "Password06");

        if (manager.isManager()) {
            Project project = manager.createProject("Spring Fair", "Plan the booths, voulenteers, and food for the spring campus fair.", "04-01-2027", "04-17-2027");
            if (project != null) {
                Task task1 = new Task(1, "Book the venue");
                project.getTasks().add(task1);
                task1.updateStatus(TaskStatus.COMPLETED);
                System.out.println("Current Project Progress: " + project.getProgress() + "%");
            }
        }
        else {
            System.out.println("Only managers may access this.");
        }
    }
}
