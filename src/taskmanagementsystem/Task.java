package taskmanagementsystem;

/**
 * Represents a single task in the Task Management System.
 * <p>
 * Every task has a title, a description and a status. The status can be
 * "Pending", "In Progress" or "Completed". A newly created task always starts
 * with the status "Pending". This class only stores data; all the logic for
 * adding, updating and deleting tasks is handled by {@link TaskManager}.
 *
 * @author Sahab Saeed
 * @author Shanza Khalid Khan
 */
public class Task {
    /** The short title of the task. */
    private String title;
    /** The detailed description of the task. */
    private String description;
    /** The current status of the task (Pending, In Progress or Completed). */
    private String status;

    /**
     * Constructs a new Task with the given title and description.
     * The status of the new task is set to "Pending" by default.
     * 
     * @param title The title of the task.
     * @param description The description of the task.
     */
    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = "Pending"; // Default status
    }

    /**
     * Returns the title of this task.
     * 
     * @return the title of the task
     */
    public String getTitle() { return title; }

    /**
     * Sets a new title for this task.
     * 
     * @param title the new title of the task
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Returns the description of this task.
     * 
     * @return the description of the task
     */
    public String getDescription() { return description; }

    /**
     * Sets a new description for this task.
     * 
     * @param description the new description of the task
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Returns the current status of this task.
     * 
     * @return the status of the task: "Pending", "In Progress" or "Completed"
     */
    public String getStatus() { return status; }

    /**
     * Sets a new status for this task.
     * 
     * @param status the new status: "Pending", "In Progress" or "Completed"
     */
    public void setStatus(String status) { this.status = status; }
}