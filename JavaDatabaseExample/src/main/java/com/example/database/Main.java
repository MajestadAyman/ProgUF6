package main.java.com.example.database;

import main.java.com.example.database.models.Student;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        // Add a student
        Student student = new Student(1, "John", "Doe");
        dbManager.addStudent(student);

        // Get a student
        Student retrievedStudent = dbManager.getStudent(1);
        System.out.println(retrievedStudent);

        // Update a student
        student.setName("Jane");
        student.setLastName("Doe");
        dbManager.updateStudent(student);

        // Delete a student
        dbManager.deleteStudent(1);

        // Disconnect from the database
        dbManager.disconnect();
    }
}