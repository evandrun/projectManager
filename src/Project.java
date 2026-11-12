import java.util.ArrayList;
import java.util.List;

public class Project {
    private int projectId;
    private String name;
    private String description;
    private String startDate;
    private String dueDate;
    private int overallProgress;
    private List<Task> tasks;

    public Project(int projectID, String name, String description, String startDate, String dueDate) {
        this.projectId = projectId;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.overallProgress = 0;
        this.tasks = new ArrayList<>();
    }

    public boolean validateDetails() {
        if (name == null || name.isEmpty() || description == null || description.isEmpty() || startDate == null || startDate.isEmpty() || dueDate == null || dueDate.isEmpty()) {
            return false;
        }
        return true;
    }

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

    public String getName() {
        return name;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
