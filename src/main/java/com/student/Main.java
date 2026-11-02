
package com.student;

import com.student.model.Student;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();

        students.add(new Student(1, "Ahmed", 20));
        students.add(new Student(2, "Sara", 21));
        students.add(new Student(3, "Omar", 22));

        int choice = 0;

        while (choice != 5) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();

                scanner.nextLine();

                System.out.print("Enter student name: ");
                String name = scanner.nextLine();

                System.out.print("Enter student age: ");
                int age = scanner.nextInt();

                students.add(new Student(id, name, age));
                System.out.println("Student added successfully!");

            } else if (choice == 2) {
                for (Student student : students) {
                    student.displayInfo();
                    System.out.println("--------------------");
                }

            } else if (choice == 3) {
                System.out.print("Enter student ID to search: ");
                int id = scanner.nextInt();
                boolean found = false;

                for (Student student : students) {
                    if (student.getId() == id) {
                        student.displayInfo();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Student not found!");
                }

            } else if (choice == 4) {
                System.out.print("Enter student ID to delete: ");
                int id = scanner.nextInt();
                boolean removed = false;

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        students.remove(i);
                        removed = true;
                        break;
                    }
                }

                if (removed) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Student not found!");
                }

            } else if (choice == 5) {
                System.out.println("Goodbye!");

            } else {
                System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}
