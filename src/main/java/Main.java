import task.Manager;
import task.Task;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class Main {
    Manager myTask = new Manager();
   ImageIcon icon = new ImageIcon("src/pages.png");

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();
    }

    void menu() {
        String taskOption;

        do {
            String[] options = {
                    "Select...", "Create task", "View all tasks","View Active tasks", "Update task",
                    "Delete task", "Exit"};
            taskOption = (String) JOptionPane.showInputDialog(null, """
                            Welcome to TODO List!
                            Please select choice
                            Choose EXIT to quit""",
                    "TODO list menu",
                    JOptionPane.PLAIN_MESSAGE,
                    icon,
                    options,
                    options[0]);
            switch (taskOption) {
                case "Create task":
                    JOptionPane.showMessageDialog(null, myTask.addTask());
                    break;
                case "View all tasks":
                    List<String> taskStrings = myTask.getTasks().stream().map(Task::toString).toList();
                    JOptionPane.showMessageDialog(null, String.join(",\n", taskStrings), "All Tasks", JOptionPane.PLAIN_MESSAGE);
                    break;
                case "Update task":
                    JOptionPane.showMessageDialog(null, myTask.updateTask(), "Task update", JOptionPane.PLAIN_MESSAGE);
                    break;
                case "Delete task":
                    JOptionPane.showMessageDialog(null, myTask.removeTask());
                    break;
                case "View Active tasks":
                    List<String> taskActiveStrings = myTask.getActive().stream().map(Task::toString).toList();
                    JOptionPane.showMessageDialog(null, String.join(",\n", taskActiveStrings), "Active Tasks", JOptionPane.PLAIN_MESSAGE);
                    break;
                default:
                    break;
            }
        } while (!taskOption.equals("Exit"));
    }
}
