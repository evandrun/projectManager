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
        Project newProject = new Project(projectList.size() +1, name, description, startDate, dueDate);
        if (!newProject.validateDetails()) {
            System.out.println("Error: All 4 project details need to be filled.");
            return null;
        }

        projectList.add(newProject);
        System.out.println("Success: Project '" + newProject.getName() + "' created and added to manager's project list.");
        return newProject;
    }

    public void editProject(Project project) {
        System.out.println("Editing project: " + project.getName());
    }

    public List<Project> getProjects() {
        return projectList;
    }
}
