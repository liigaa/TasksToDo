package task;

import database.DBTaskRepository;

import javax.swing.*;
import java.util.ArrayList;

public class Manager {
    DBTaskRepository taskRepository = new DBTaskRepository();


    public ArrayList<Task> getTasks() {

        return taskRepository.getAll();
    }

    public ArrayList<Task> getActive() {
        return taskRepository.getActive();
    }

    public String addTask() {
        JTextField title = new JTextField(15);
        JTextField description = new JTextField(20);
        String[] availableStatus = {"Active", "Done"};
        JComboBox<String> statusField = new JComboBox<>(availableStatus);
        statusField.setVisible(true);
        String status = (String) statusField.getSelectedItem();
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Title"));
        myPanel.add(title);
        myPanel.add(new JLabel("Description:"));
        myPanel.add(description);
        myPanel.add(Box.createVerticalBox());
        myPanel.add(new JLabel("Status:"));
        myPanel.add(statusField);

        int result = JOptionPane.showConfirmDialog(null, myPanel,
                "Create Task", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.CANCEL_OPTION) {
            return "No new Task!";
        }
        if (result == JOptionPane.OK_OPTION) {
            Task task = new Task(title.getText(), description.getText(), status);
            String newStatus = (String) statusField.getSelectedItem();
            task.setStatus(newStatus);
            if (task.getTitle() == null || task.getTitle().isEmpty()) {
                return "Please add Title";
            }
            if (task.getDescription() == null || task.getDescription().isEmpty()) {
                return "Please add description";
            }
            taskRepository.create(task);
        }
        return "Task: " + title.getText() + " added successfully";
    }


    public String updateTask() {
        try {
            ArrayList<Task> tasks = this.getTasks();
            Task taskToUpdate = (Task) JOptionPane.showInputDialog(null,
                    "Choose Task to update",
                    "Update Task",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    tasks.toArray(new Task[0]),
                    tasks);
            JTextField descriptionField = new JTextField(20);
            String[] availableStatus = {"Active", "Done"};
            JComboBox<String> statusField = new JComboBox<>(availableStatus);
            statusField.setVisible(true);
            descriptionField.setText(taskToUpdate.getDescription());
            statusField.setSelectedItem(taskToUpdate.getStatus());
            JPanel myPanel = new JPanel();
            myPanel.add(new JLabel("Description:"));
            myPanel.add(descriptionField);
            myPanel.add(Box.createHorizontalStrut(15));
            myPanel.add(new JLabel("Status:"));
            myPanel.add(statusField);

            String title = taskToUpdate.getTitle();
            int result = JOptionPane.showConfirmDialog(null, myPanel,
                    "Update Task: " + title, JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.CANCEL_OPTION) {
                return "Update canceled!";
            }
            if (result == JOptionPane.OK_OPTION) {
                String newDescription = descriptionField.getText();
                String newStatus = (String) statusField.getSelectedItem();
                taskToUpdate.setDescription(newDescription);
                taskToUpdate.setStatus(newStatus);
                taskRepository.update(taskToUpdate);

            }
            return "Task: " + title + " updated successfully!";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No task to update",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        return "Choose Task to update";
    }

    public String removeTask() {
        try {
            ArrayList<Task> tasks = this.getTasks();
            Task taskToRemove = (Task) JOptionPane.showInputDialog(null,
                    "Choose Task to delete",
                    "Delete Task",
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    tasks.toArray(new Task[0]),
                    tasks);
            String title = taskToRemove.getTitle();
            taskRepository.delete(title);

            return "Task: " + title + " deleted successfully";
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No task delete",
                    "Warning!", JOptionPane.WARNING_MESSAGE);
        }
        return "Choose Task to delete";
    }
}

