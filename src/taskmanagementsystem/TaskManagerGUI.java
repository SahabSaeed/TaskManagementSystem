package taskmanagementsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The GUI class for the Task Management System using Java Swing.
 * <p>
 * It shows input fields (title, description, status), a table that lists all
 * tasks, and buttons to add, update and delete a task or clear the fields.
 * All task logic is delegated to {@link TaskManager}; this class only handles
 * what the user sees and clicks. Invalid input is reported to the user with a
 * dialog box and a red border around the title field.
 *
 * @author Sahab Saeed
 * @author Shanza Khalid Khan
 */
public class TaskManagerGUI extends JFrame {
    /** The object that stores the tasks and performs add, update and delete. */
    private TaskManager manager;
    
    /** Text field where the user types the task title. */
    private JTextField titleField;
    /** Text field where the user types the task description. */
    private JTextField descField;
    /** Drop-down list to choose the task status. */
    private JComboBox<String> statusCombo;
    /** Table that displays all tasks. */
    private JTable taskTable;
    /** Data model of the table; holds the rows shown in {@link #taskTable}. */
    private DefaultTableModel tableModel;

    // Color Palette (Premium Black & Yellow)
    private final Color BG_COLOR = new Color(30, 30, 30); 
    private final Color PANEL_COLOR = new Color(45, 45, 45); 
    private final Color YELLOW_COLOR = new Color(255, 193, 7); 
    private final Color TEXT_COLOR = Color.WHITE;
    private final Color BUTTON_TEXT_COLOR = new Color(20, 20, 20); 

    /**
     * Constructs the main window. It creates the TaskManager, builds the input
     * panel, the task table and the buttons, and attaches the event listeners
     * for Add, Update, Delete, Clear and table row selection.
     */
    public TaskManagerGUI() {
        manager = new TaskManager();
        
        setTitle("Task Management System - OEL Project");
        setSize(850, 650); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout(10, 10));

        getContentPane().setBackground(BG_COLOR);

        // --- TOP PANEL (Input Fields) ---
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBackground(PANEL_COLOR);
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(YELLOW_COLOR, 2), 
                        "Task Details", 
                        0, 0, 
                        new Font("Segoe UI", Font.BOLD, 14), 
                        YELLOW_COLOR)
        ));

        JLabel titleLabel = createStyledLabel("  Title:");
        inputPanel.add(titleLabel);
        titleField = createStyledTextField();
        inputPanel.add(titleField);

        JLabel descLabel = createStyledLabel("  Description:");
        inputPanel.add(descLabel);
        descField = createStyledTextField();
        inputPanel.add(descField);

        JLabel statusLabel = createStyledLabel("  Status:");
        inputPanel.add(statusLabel);
        statusCombo = new JComboBox<>(new String[]{"Pending", "In Progress", "Completed"});
        statusCombo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        statusCombo.setBackground(PANEL_COLOR);
        statusCombo.setForeground(TEXT_COLOR);
        inputPanel.add(statusCombo);

        add(inputPanel, BorderLayout.NORTH);

        // --- CENTER PANEL (Table) ---
        String[] columns = {"Title", "Description", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        taskTable = new JTable(tableModel);
        
        taskTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        taskTable.setRowHeight(30);
        taskTable.setBackground(PANEL_COLOR);
        taskTable.setForeground(TEXT_COLOR);
        taskTable.setGridColor(Color.GRAY);
        taskTable.setSelectionBackground(YELLOW_COLOR);
        taskTable.setSelectionForeground(BUTTON_TEXT_COLOR);
        
        JTableHeader header = taskTable.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(YELLOW_COLOR);
        header.setForeground(BUTTON_TEXT_COLOR);
        
        JScrollPane scrollPane = new JScrollPane(taskTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        scrollPane.getViewport().setBackground(BG_COLOR);
        add(scrollPane, BorderLayout.CENTER);

        // --- BOTTOM PANEL (Buttons) ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttonPanel.setBackground(BG_COLOR);
        
        JButton addButton = createStyledButton("Add Task");
        JButton updateButton = createStyledButton("Update Task");
        JButton deleteButton = createStyledButton("Delete Task");
        JButton clearButton = createStyledButton("Clear Fields");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // --- BUTTON ACTIONS ---
        addButton.addActionListener(new ActionListener() {
            /**
             * Adds a new task using the text typed in the fields. If the title
             * is empty, a validation error is shown and the title field gets a
             * red border.
             * 
             * @param e the click event of the Add Task button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    manager.addTask(titleField.getText(), descField.getText());
                    refreshTable();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Task Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    // RED BORDER VISUAL ALERT
                    titleField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            /**
             * Updates the task selected in the table with the values in the
             * fields. If no row is selected, a warning is shown instead. If
             * the title is empty, a validation error is shown.
             * 
             * @param e the click event of the Update Task button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                // NON-EXISTING HANDLING
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task from the table to update.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                try {
                    String newStatus = statusCombo.getSelectedItem().toString();
                    manager.updateTask(selectedRow, titleField.getText(), descField.getText(), newStatus);
                    refreshTable();
                    clearFields();
                    JOptionPane.showMessageDialog(null, "Task Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    titleField.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            /**
             * Deletes the task selected in the table. If no row is selected,
             * a warning is shown instead.
             * 
             * @param e the click event of the Delete Task button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = taskTable.getSelectedRow();
                // NON-EXISTING HANDLING
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(null, "Please select a task from the table to delete.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                manager.deleteTask(selectedRow);
                refreshTable();
                clearFields();
                JOptionPane.showMessageDialog(null, "Task Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        clearButton.addActionListener(new ActionListener() {
            /**
             * Clears all input fields and removes the table selection.
             * 
             * @param e the click event of the Clear Fields button
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        taskTable.addMouseListener(new java.awt.event.MouseAdapter() {
            /**
             * Copies the data of the clicked table row into the input fields
             * so that the user can edit it, and resets the red border.
             * 
             * @param evt the mouse click event on the table
             */
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = taskTable.getSelectedRow();
                if (selectedRow != -1) {
                    titleField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                    descField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                    statusCombo.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
                    
                    // Reset Red Border
                    titleField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(Color.GRAY, 1),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)
                    ));
                }
            }
        });
    }

    /**
     * Creates a label with the application's white bold font.
     * 
     * @param text the text shown on the label
     * @return the styled label
     */
    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 15));
        label.setForeground(TEXT_COLOR);
        return label;
    }

    /**
     * Creates an empty text field with the application's dark theme and
     * a gray border.
     * 
     * @return the styled text field
     */
    private JTextField createStyledTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBackground(PANEL_COLOR);
        field.setForeground(TEXT_COLOR);
        field.setCaretColor(YELLOW_COLOR);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        return field;
    }

    /**
     * Creates a yellow button with the application's theme.
     * 
     * @param text the text shown on the button
     * @return the styled button
     */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(YELLOW_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(130, 35)); 
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setOpaque(true);
        button.setBorderPainted(false); 
        return button;
    }

    /**
     * Reloads the table: removes all rows and adds one row for every task
     * currently stored in the TaskManager.
     */
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Task t : manager.getAllTasks()) {
            Object[] row = {t.getTitle(), t.getDescription(), t.getStatus()};
            tableModel.addRow(row);
        }
    }

    /**
     * Empties the title and description fields, sets the status back to
     * "Pending", clears the table selection and resets the title border.
     */
    private void clearFields() {
        titleField.setText("");
        descField.setText("");
        statusCombo.setSelectedIndex(0);
        taskTable.clearSelection();
        // Reset Border
        titleField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
    }
}