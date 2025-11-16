package org.example.finalsdp.abstractfactory;

public class VocStaff implements InstStaff {
    private String name;
    private String skillArea;

    public VocStaff(String staffName, String skill) {
        this.name = staffName;
        this.skillArea = skill;
    }

    @Override
    public void conductTeaching() {
        System.out.println(name + " training students in " + skillArea);
    }

    @Override
    public void handleCurriculum() {
        System.out.println(name + " creating industry-based " + skillArea + " curriculum");
    }

    @Override
    public String getInstType() {
        return "Vocational School";
    }

    @Override
    public String getStaffDetails() {
        return "Vocational Instructor: " + name + " - " + skillArea;
    }
}