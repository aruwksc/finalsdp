package org.example.finalsdp.abstractfactory;

public class HSStaff implements InstStaff {
    private String name;
    private String subject;

    public HSStaff(String staffName, String subj) {
        this.name = staffName;
        this.subject = subj;
    }

    @Override
    public void conductTeaching() {
        System.out.println(name + " teaching " + subject + " to high school students");
    }

    @Override
    public void handleCurriculum() {
        System.out.println(name + " following standard curriculum for " + subject);
    }

    @Override
    public String getInstType() {
        return "High School";
    }

    @Override
    public String getStaffDetails() {
        return "HS Teacher: " + name + " (" + subject + ")";
    }
}
