package taskmanagementsystem;

import java.util.ArrayList;

/**
 * Manages the list of tasks. Contains logic for Add, Update, Delete, and View.
 * <p>
 * Tasks are stored in an {@link ArrayList}, so the position (index) of a task
 * in the list is the same as its row number in the GUI table. This class also
 * validates input: a task title must never be empty.
 *
 * @author Sahab Saeed
 * @author Shanza Khalid Khan
 */
public class TaskManager {
    /** The list that stores all tasks in the order they were added. */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskManager with an empty task list.
     */
    public TaskManager() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a new task.
     * 
     * @param title The title of the task.
     * @param description The description of the task.
     * @throws IllegalArgumentException if title is empty.
     */
    public void addTask(String title, String description) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty!");
        }
        tasks.add(new Task(title, description));
    }

    /**
     * Returns all tasks currently stored in the manager (used to view tasks).
     * 
     * @return the list of all tasks; the list is empty if no task has been added
     */
    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Updates a task at a specific index.
     * If the index does not exist in the list, nothing is changed.
     * 
     * @param index The position of the task in the list.
     * @param newTitle The new title.
     * @param newDesc The new description.
     * @param newStatus The new status.
     * @throws IllegalArgumentException if the new title is null or empty.
     */
    public void updateTask(int index, String newTitle, String newDesc, String newStatus) {
        if (index >= 0 && index < tasks.size()) {
            if (newTitle == null || newTitle.trim().isEmpty()) {
                throw new IllegalArgumentException("Task title cannot be empty!");
            }
            Task t = tasks.get(index);
            t.setTitle(newTitle);
            t.setDescription(newDesc);
            t.setStatus(newStatus);
        }
    }

    /**
     * Deletes a task at a specific index.
     * If the index does not exist in the list, nothing is deleted.
     * 
     * @param index The position of the task.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
}