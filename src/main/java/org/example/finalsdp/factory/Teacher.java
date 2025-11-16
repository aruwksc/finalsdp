package org.example.finalsdp.factory;

public class Teacher implements User {
    private String teacherName;
    private String teacherID;
    private String subjectTaught;
    private int experienceYears;

    public Teacher(String name, String id, String subject, int years) {
        this.teacherName = name;
        this.teacherID = id;
        this.subjectTaught = subject;
        this.experienceYears = years;
    }

    @Override
    public String getUserName() {
        return teacherName;
    }

    @Override
    public String getUserId() {
        return teacherID;
    }

    @Override
    public String getUserRole() {
        return "Teacher";
    }

    @Override
    public void showUserDetails() {
        System.out.println("--- Teacher Information ---");
        System.out.println("Teacher Name: " + teacherName);
        System.out.println("Teacher ID: " + teacherID);
        System.out.println("Subject: " + subjectTaught);
        System.out.println("Years of Experience: " + experienceYears);
        System.out.println("---------------------------");
    }

    public String getSubjectTaught() {
        return subjectTaught;
    }

    public int getExperienceYears() {
        return experienceYears;
    }
}