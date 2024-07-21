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
