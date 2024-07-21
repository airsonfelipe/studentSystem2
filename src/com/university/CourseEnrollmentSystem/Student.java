package com.university.CourseEnrollmentSystem;
import java.util.HashMap;
import java.util.Map;

public class Student {
    private String name;
    private String id;
    private Map<Course, Double> coursesAndGrades;

    public Student(String name, String id) {
        this.name = name;
        this.id = id;
        this.coursesAndGrades = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void enrollCourse(Course course) {
        coursesAndGrades.put(course, null);
    }

    public void assignGrade(Course course, double grade) {
        if (coursesAndGrades.containsKey(course)) {
            coursesAndGrades.put(course, grade);
        }
    }

    public Map<Course, Double> getCoursesAndGrades() {
        return coursesAndGrades;
    }
}
