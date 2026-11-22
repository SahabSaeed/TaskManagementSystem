package taskmanagementsystem;

import java.util.ArrayList;

/**
 * Manages the list of tasks. Contains logic for Add, Update, Delete, and View.
 * 
 * @author AR Tech Laptops
 */
public class TaskManager {
    private ArrayList<Task> tasks;

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

    public ArrayList<Task> getAllTasks() {
        return tasks;
    }

    /**
     * Updates a task at a specific index.
     * 
     * @param index The position of the task in the list.
     * @param newTitle The new title.
     * @param newDesc The new description.
     * @param newStatus The new status.
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
     * 
     * @param index The position of the task.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
    }
}