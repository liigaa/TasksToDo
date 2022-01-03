package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String connectionUrl = "jdbc:mysql://127.0.0.1:3306/list";
    String user = "root";
    String pass = "Arinas!23";

    private static Connection connection;

    public DBConnection() {
        try {
            connection = DriverManager.getConnection(connectionUrl, user, pass);

            System.out.println("Connection successful");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
    public static Connection getConnection(){
        return connection;
    }

}
