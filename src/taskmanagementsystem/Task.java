package taskmanagementsystem;

/**
 * Represents a single task in the Task Management System.
 * 
 * @author AR Tech Laptops
 */
public class Task {
    private String title;
    private String description;
    private String status;

    /**
     * Constructs a new Task.
     * 
     * @param title The title of the task.
     * @param description The description of the task.
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "Pending"; // Default status
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}