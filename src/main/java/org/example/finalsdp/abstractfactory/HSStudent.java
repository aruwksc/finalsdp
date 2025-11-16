package org.example.finalsdp.abstractfactory;

public class HSStudent implements InstStudent {
    private String name;
    private int gradeLevel;

    public HSStudent(String studentName, int grade) {
        this.name = studentName;
        this.gradeLevel = grade;
    }

    @Override
    public void studyProcess() {
        System.out.println(name + " is studying basic subjects for grade " + gradeLevel);
    }

    @Override
    public void goToClass() {
        System.out.println(name + " attending classes with strict schedule");
    }

    @Override
    public String getInstType() {
        return "High School";
    }

    @Override
    public String getStudentDetails() {
        return "HS Student: " + name + " (Grade " + gradeLevel + ")";
    }
}