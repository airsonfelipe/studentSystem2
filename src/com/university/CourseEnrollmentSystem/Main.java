package com.university.CourseEnrollmentSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Course Enrollment and Grade Management System");
            System.out.println("1. Add a new course");
            System.out.println("2. Enroll a student in a course");
            System.out.println("3. Assign grade to a student");
            System.out.println("4. Calculate overall course grade for a student");
            System.out.println("5. View courses list");
            System.out.println("6. View students list");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter course code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter course name: ");
                    String courseName = scanner.nextLine();
                    System.out.print("Enter course capacity: ");
                    int capacity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    CourseManagement.addCourse(courseCode, courseName, capacity);
                    System.out.println("Course added successfully.");
                    break;
                case 2:
                    System.out.print("Enter student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    Student student = new Student(studentName, studentId);
                    System.out.print("Enter course code to enroll in: ");
                    String courseCodeToEnroll = scanner.nextLine();
                    Course courseToEnroll = CourseManagement.getCourseByCode(courseCodeToEnroll);
                    if (courseToEnroll != null) {
                        CourseManagement.enrollStudent(student, courseToEnroll);
                        System.out.println("Student enrolled successfully.");
                    } else {
                        System.out.println("Course not found.");
                    }
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    String studentIdForGrade = scanner.nextLine();
                    Student studentForGrade = CourseManagement.getStudentById(studentIdForGrade);
                    if (studentForGrade != null) {
                        System.out.print("Enter course code: ");
                        String courseCodeForGrade = scanner.nextLine();
                        Course courseForGrade = CourseManagement.getCourseByCode(courseCodeForGrade);
                        if (courseForGrade != null) {
                            System.out.print("Enter grade: ");
                            double grade = scanner.nextDouble();
                            scanner.nextLine();  // Consume newline
                            CourseManagement.assignGrade(studentForGrade, courseForGrade, grade);
                            System.out.println("Grade assigned successfully.");
                        } else {
                            System.out.println("Course not found.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    System.out.print("Enter student ID: ");
                    String studentIdForOverallGrade = scanner.nextLine();
                    Student studentForOverallGrade = CourseManagement.getStudentById(studentIdForOverallGrade);
                    if (studentForOverallGrade != null) {
                        double overallGrade = CourseManagement.calculateOverallGrade(studentForOverallGrade);
                        System.out.println("Overall Grade for student " + studentForOverallGrade.getName() + ": " + overallGrade);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 5:
                    CourseManagement.viewCourses();
                    break;
                case 6:
                    CourseManagement.viewStudents();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
