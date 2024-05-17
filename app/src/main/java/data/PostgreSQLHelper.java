package data;

import android.os.StrictMode;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLHelper {

    private static final String URL = "jdbc:postgresql://trabajosnicaragua.c7osqgq681bb.us-east-2.rds.amazonaws.com:6666/trabajosnicaragua_db";
    private static final String USER = "root";
    private static final String PASSWORD = "CJOC260802";

    private Connection connection;

    public PostgreSQLHelper() {
        connect();
    }

    private void connect() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connection Successful!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("PostgreSQL JDBC Driver not found.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed. Check output console.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection Closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}