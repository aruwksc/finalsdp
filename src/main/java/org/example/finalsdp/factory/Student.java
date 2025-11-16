package org.example.finalsdp.factory;

public class Student implements User {
    private String studentName;
    private String studentID;
    private String studentMajor;
    private int currentGrade;

    public Student(String name, String id, String major, int grade) {
        this.studentName = name;
        this.studentID = id;
        this.studentMajor = major;
        this.currentGrade = grade;
    }

    @Override
    public String getUserName() {
        return studentName;
    }

    @Override
    public String getUserId() {
        return studentID;
    }

    @Override
    public String getUserRole() {
        return "Student";
    }

    @Override
    public void showUserDetails() {
        System.out.println("--- Student Information ---");
        System.out.println("Student Name: " + studentName);
        System.out.println("Student ID: " + studentID);
        System.out.println("Major: " + studentMajor);
        System.out.println("Current Grade: " + currentGrade);
        System.out.println("---------------------------");
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public int getCurrentGrade() {
        return currentGrade;
    }
}
