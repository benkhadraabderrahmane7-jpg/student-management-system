package com.student;

import com.student.exception.DuplicateStudentException;
import com.student.model.*;
import com.student.persistence.TextStudentRepository;
import com.student.service.StudentManager;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    private static final Scanner INPUT = new Scanner(System.in);
    public static void main(String[] args) {
        try {
            StudentManager manager = new StudentManager(new TextStudentRepository(Paths.get("data", "students.txt")));
            while (true) {
                System.out.println("\n=== STUDENT MANAGEMENT SYSTEM ===");
                System.out.println("1. Add Student\n2. View All Students\n3. Search Student\n4. Delete Student\n5. Exit");
                int choice = readInt("Choose 1-5: ");
                try {
                    switch (choice) {
                        case 1:
                            int id = readInt("ID: ");
                            System.out.print("Name: ");
                            String name = INPUT.nextLine();
                            int age = readInt("Age: ");
                            System.out.print("Type (U=Undergraduate, G=Graduate): ");
                            String type = INPUT.nextLine().trim();
                            manager.add(StudentFactory.create(type, id, name, age));
                            System.out.println("Student added and saved.");
                            break;
                        case 2:
                            if (manager.listSortedById().isEmpty()) System.out.println("No students found.");
                            for (Student student : manager.listSortedById()) System.out.println(student.displayInfo());
                            break;
                        case 3:
                            Student found = manager.find(readInt("Search ID: "));
                            System.out.println(found == null ? "Student not found." : found.displayInfo());
                            break;
                        case 4:
                            System.out.println(manager.delete(readInt("Delete ID: ")) ? "Student deleted and saved." : "Student not found.");
                            break;
                        case 5:
                            System.out.println("Goodbye!");
                            return;
                        default: System.out.println("Please choose a number from 1 to 5.");
                    }
                } catch (IllegalArgumentException | DuplicateStudentException | IOException ex) {
                    System.out.println("Error: " + ex.getMessage());
                }
            }
        } catch (IOException ex) {
            System.err.println("Cannot load student data: " + ex.getMessage());
        }
    }
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String text = INPUT.nextLine().trim();
            try { return Integer.parseInt(text); }
            catch (NumberFormatException ex) { System.out.println("Please enter a whole number."); }
        }
    }
}
