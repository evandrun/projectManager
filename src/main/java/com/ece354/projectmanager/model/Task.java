public class Task {
    private int taskId;
    private String description;
    private String status;

    public Task(int taskId, String description) {
        this.taskId = taskId;
        this.description = description;
        this.status = TaskStatus.NOT_STARTED;
    }
    public boolean isAssignedTo(TeamMember teamMember) {
        return true;
    }
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Task " + taskId + " Status updated to: " + status);
    }
    public String getStatus() {
        return status;
    }
}
