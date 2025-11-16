package org.example.finalsdp.abstractfactory;

public class UniStudent implements InstStudent {
    private String name;
    private String majorField;
    private int studyYear;

    public UniStudent(String studentName, String major, int year) {
        this.name = studentName;
        this.majorField = major;
        this.studyYear = year;
    }

    @Override
    public void studyProcess() {
        System.out.println(name + " studying " + majorField + " in year " + studyYear);
    }

    @Override
    public void goToClass() {
        System.out.println(name + " attending lectures with flexible schedule");
    }

    @Override
    public String getInstType() {
        return "University";
    }

    @Override
    public String getStudentDetails() {
        return "University Student: " + name + " - " + majorField + " (Year " + studyYear + ")";
    }
}
