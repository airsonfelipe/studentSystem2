Overview of the System

The Course Enrollment and Grade Management System in Java is designed to manage students and courses, allow student enrollments, assign grades, and calculate overall grades. This system also includes an administrator interface to facilitate interaction with the system.

Code Structure
1. Course Class
This class represents a course offered by the university.
Code
package com.university.CourseEnrollmentSystem;

public class Course {
    private String code;
    private String name;
    private int maxCapacity;
    private int currentEnrollment;

    // Construtor
    public Course(String code, String name, int maxCapacity) {
        this.code = code;
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentEnrollment = 0; // Inicialmente, não há inscrições
    }

    // Getters e Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }

    // Método para incrementar o número de inscrições
    public void incrementEnrollment() {
        if (currentEnrollment < maxCapacity) {
            currentEnrollment++;
        } else {
            System.out.println("Course is full. Cannot enroll more students.");
        }
    }
}

Private Attributes: The Course class has private attributes to store the course code, name, maximum capacity, and current enrollment. Encapsulation is used to protect these data.
Constructor: Initializes the course's code, name, and maximum capacity. The currentEnrollment is initialized to 0.
Getters and Setters: Public methods to access and modify the private attributes.
incrementEnrollment Method: Increases the number of enrollments if there is space available in the course. Otherwise, it prints an error message.

2. Student Class
This class represents a student who can enroll in courses and receive grades.
Code
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

Private Attributes: The Student class has private attributes for the student ID, name, and a map of courses and grades.
Constructor: Initializes the student ID, name, and the map of courses and grades.
Getters and Setters: Public methods to access and modify the private attributes.
enrollCourse Method: Adds a course to the map of courses and grades, initializing the grade as null.
assignGrade Method: Assigns a grade to a course if the student is enrolled. Otherwise, it prints an error message.
3. CourseManagement Class
This class manages operations related to courses and students, such as adding courses, enrolling students, assigning grades, and calculating overall grades.
Code
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

Static Variables: Stores the list of courses and the map of students. These data are shared across all instances of the CourseManagement class.
Static Methods:
addCourse: Adds a new course to the list of courses.
getCourseByCode: Retrieves a course by its code.
enrollStudent: Enrolls a student in a course if there is space available.
assignGrade: Assigns a grade to a student for a specific course.
getStudentById: Retrieves a student by their ID.
calculateOverallGrade: Computes the average grade of a student across all enrolled courses.
viewCourses: Displays the list of courses or a message if no courses are found.
viewStudents: Displays the list of students or a message if no students are found.



4. Main Class (Administrator Interface)
This class provides a command-line interface for the administrator to interact with the system.
Code
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

Infinite Loop: The while (true) loop keeps the menu visible until the administrator chooses to exit.
Menu: Displays options to add courses, enroll students, assign grades, calculate overall grades, and view lists of courses and students.
Input Reading: Uses Scanner to read user input.
Switch Case: Handles user choices and calls the appropriate methods from the CourseManagement class.
Add Course: Prompts for course details and adds the course.
Enroll Student: Prompts for student and course details and enrolls the student in the course.
Assign Grade: Prompts for student, course, and grade details and assigns the grade to the student.
Calculate Overall Grade: Prompts for the student ID and calculates the average grade.
View Courses and Students: Displays the list of courses or students.
Exit: Closes the program.
Conclusion
The code for the Course Enrollment and Grade Management System is structured to facilitate the management of courses and students with essential functionalities for handling enrollments, grades, and overall grades. The classes are designed to maintain a clear separation of responsibilities, which aids in the maintenance and extensibility of the system. The administrator interface allows for interactive management of courses and students, ensuring ease of use and effective control over the enrollment and grading process.
Output:
 



Add new course:
 






Enroll a student in a course:
 
Assign grade:
 













I added another course, enrolled the student and assigned a grade to the student (to make the overall grade):
 
 

 
Calculate de overall course grade for a student:
 













View courses and student list and exit:
 



References:
Eck, D. J. (2022). Introduction to Programming Using Java: Version 9.0, JavaFX Edition. Retrieved from http://math.hws.edu/javanotes/
Bloch, J. (2018). Effective Java (3rd ed.). Addison-Wesley Professional.
Oracle. (2023). The Java Tutorials. Oracle Corporation.
GeeksforGeeks. (n.d.). Arrays in Java. Retrieved July 15, 2024, from https://www.geeksforgeeks.org/arrays-in-java/
GeeksforGeeks. (n.d.). ArrayList in Java. Retrieved July 15, 2024, from https://www.geeksforgeeks.org/arraylist-in-java/?ref=header_search

