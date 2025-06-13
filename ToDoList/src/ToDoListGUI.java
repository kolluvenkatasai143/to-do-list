import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Only one Task class definition
class List {
    String description;
    boolean isCompleted;

    List(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public String toString() {
        return (isCompleted ? "[✓] " : "[ ] ") + description;
    }
}

public class ToDoListGUI extends JFrame implements ActionListener {
    private DefaultListModel<Task> taskListModel;
    private JList<Task> taskList;
    private JTextField taskInput;
    private JButton addButton, deleteButton, completeButton;

    ArrayList<Task> tasks = new ArrayList<>();

    public ToDoListGUI() {
        setTitle("To-Do List");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input panel
        JPanel topPanel = new JPanel(new BorderLayout());
        taskInput = new JTextField();
        addButton = new JButton("Add Task");
        addButton.addActionListener(this);
        topPanel.add(taskInput, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);

        // Task list
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Action buttons
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("Delete Task");
        completeButton = new JButton("Mark as Complete");
        deleteButton.addActionListener(this);
        completeButton.addActionListener(this);
        bottomPanel.add(deleteButton);
        bottomPanel.add(completeButton);

        // Frame layout
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            String desc = taskInput.getText().trim();
            if (!desc.isEmpty()) {
                Task newTask = new Task(desc);
                tasks.add(newTask);
                taskListModel.addElement(newTask);
                taskInput.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Task description cannot be empty.");
            }
        } else if (e.getSource() == deleteButton) {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                tasks.remove(index);
                taskListModel.remove(index);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.");
            }
        } else if (e.getSource() == completeButton) {
            int index = taskList.getSelectedIndex();
            if (index != -1) {
                Task task = taskListModel.get(index);
                task.isCompleted = true;
                taskList.repaint();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to mark as complete.");
            }
        }
    }

    public static void main(String[] args) {
        new ToDoListGUI();
    }
}
