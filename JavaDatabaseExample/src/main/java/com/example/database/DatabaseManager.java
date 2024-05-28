package main.java.com.example.database;

import com.example.database.models.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/mydatabase";
    private static final String USER = "myuser";
    private static final String PASSWORD = "mypassword";
    private Connection connection;

    public DatabaseManager() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // In case we need to disconnect
    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // We gotta start the CRUD with the Create ofc
    public void addStudent(Student student) {
        // Store the query on a String
        String query = "INSERT INTO students (id, name, lastName) VALUES (?, ?, ?)";

        // Use a Try-Catch to handle errors in case something goes wrong with the query
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // We add the values using the prepared statement
            stmt.setInt(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getLastName());

            // Then execute the query
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Catch the excepcion if needed
            e.printStackTrace();
        }
    }

    // Then we do the R, R stands for Read
    public Student getStudent(int id) {
        // Same as before we create the query
        String query = "SELECT * FROM students WHERE id = ?";

        // Try-Catch as always
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Bind params
            stmt.setInt(1, id);

            // Execute the query and store the result on a var
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Return the query result
                return new Student(rs.getInt("id"), rs.getString("name"), rs.getString("lastName"));
            }
        } catch (SQLException e) {
            // Catch if needed
            e.printStackTrace();
        }

        // Return null if somethings wrong
        return null;
    }

    // U Stand for Update so, here's it
    public void updateStudent(Student student) {
        // Query
        String query = "UPDATE students SET name = ?, lastName = ? WHERE id = ?";

        // Try-Catch ofc
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Bind params
            stmt.setString(1, student.getName());
            stmt.setString(2, student.getLastName());
            stmt.setInt(3, student.getId());

            // Execute the query rq
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Catch if needed
            e.printStackTrace();
        }
    }

    // Last one, D stands for Delete
    public void deleteStudent(int id) {
        // Query string
        String query = "DELETE FROM students WHERE id = ?";

        // Pim, pam, Try-Catch
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            // Bind params
            stmt.setInt(1, id);

            // Execute the query
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Catch if needed again...
            e.printStackTrace();
        }
    }
}