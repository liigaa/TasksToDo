package database;

import task.Task;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBTaskRepository {
    DBConnection dbConnection = new DBConnection();

    public void create(Task task) {
        String query = "INSERT INTO task(title, description, status) VALUES(?,?,?)";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(query);

            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getDescription());
            preparedStatement.setString(3, task.getStatus());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Task> getAll() {
        String query = "SELECT * FROM task";
        Statement statement;
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            statement =dbConnection.getConnection().createStatement();

            ResultSet results = statement.executeQuery(query);


            while (results.next()) {
                String title = results.getString("title");
                String description = results.getString("description");
                String status = results.getString("status");

                Task task = new Task(title, description, status);
                tasks.add(task);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks;
    }


    public ArrayList<Task> getActive() {
        String query = "SELECT * FROM task WHERE status = 'Active'";
        PreparedStatement preparedStatement;
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(query);

            preparedStatement.execute();
            ResultSet results = preparedStatement.getResultSet();

            while (results.next()){
                String title= results.getString("title");
                String description = results.getString("description");
                String status = results.getString("status");

                Task task = new Task(title, description, status);
                tasks.add(task);
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }return tasks;
    }

    public void update(Task task) {
        String query = "UPDATE task SET description=?, status=? WHERE title =?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(query);


            preparedStatement.setString(1, task.getDescription());
            preparedStatement.setString(2, task.getStatus());
            preparedStatement.setString(3, task.getTitle());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String title){
        String query = "DELETE FROM task WHERE title=?";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = dbConnection.getConnection().prepareStatement(query);


            preparedStatement.setString(1,title);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

