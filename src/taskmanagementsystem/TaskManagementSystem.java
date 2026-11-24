package taskmanagementsystem;
// Entry point of the Task Management System application
/**
 * Main class to run the Task Management System.
 * <p>
 * It contains the main method, which starts the Swing GUI
 * ({@link TaskManagerGUI}) on the Event Dispatch Thread.
 *
 * @author Sahab Saeed
 * @author Shanza Khalid Khan
 */
public class TaskManagementSystem {
    /**
     * Entry point of the application. It creates and shows the main window
     * using {@code SwingUtilities.invokeLater}, because Swing components must
     * be created on the Event Dispatch Thread.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // GUI ko start karne ke liye (Thread safe way)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            /**
             * Creates the main window and makes it visible.
             */
            public void run() {
                TaskManagerGUI gui = new TaskManagerGUI();
                gui.setVisible(true);
            }
        });
    }
}