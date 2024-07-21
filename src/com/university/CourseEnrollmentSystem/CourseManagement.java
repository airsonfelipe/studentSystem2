package com.university.CourseEnrollmentSystem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseManagement {
    private static List<Course> courses = new ArrayList<>();
    private static Map<String, Student> students = new HashMap<>();

    public static void addCourse(String code, String name, int capacity) {
        courses.add(new Course(code, name, capacity));
    }

    public static Course getCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }

    public static void enrollStudent(Student student, Course course) {
        if (course.getCurrentEnrollment() < course.getMaxCapacity()) {
            student.enrollCourse(course);
            course.incrementEnrollment(); // Atualiza o número de inscrições
            students.put(student.getId(), student);
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Course is full. Cannot enroll student.");
        }
    }

    public static void assignGrade(Student student, Course course, double grade) {
        student.assignGrade(course, grade);
    }

    public static Student getStudentById(String id) {
        return students.get(id);
    }

    public static double calculateOverallGrade(Student student) {
        double totalGrades = 0;
        int numberOfCourses = 0;
        for (Double grade : student.getCoursesAndGrades().values()) {
            if (grade != null) {
                totalGrades += grade;
                numberOfCourses++;
            }
        }
        return numberOfCourses == 0 ? 0 : totalGrades / numberOfCourses;
    }

    public static void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No Course found.");
        } else {
            System.out.println("Courses List:");
            for (Course course : courses) {
                System.out.println("Course Code: " + course.getCode() + ", Course Name: " + course.getName() + ", Capacity: " + course.getMaxCapacity() + ", Current Enrollment: " + course.getCurrentEnrollment());
            }
        }
    }

    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No Student found.");
        } else {
            System.out.println("Students List:");
            for (Student student : students.values()) {
                System.out.println("Student Name: " + student.getName() + ", Student ID: " + student.getId());
            }
        }
    }
}
