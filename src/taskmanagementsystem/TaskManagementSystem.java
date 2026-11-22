package taskmanagementsystem;

/**
 * Main class to run the Task Management System.
 * 
 * @author AR Tech Laptops
 */
public class TaskManagementSystem {
    public static void main(String[] args) {
        // GUI ko start karne ke liye (Thread safe way)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TaskManagerGUI gui = new TaskManagerGUI();
                gui.setVisible(true);
            }
        });
    }
}