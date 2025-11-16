package org.example.finalsdp.abstractfactory;

public class UniStaff implements InstStaff {
    private String name;
    private String dept;
    private boolean doesResearch;

    public UniStaff(String staffName, String department, boolean research) {
        this.name = staffName;
        this.dept = department;
        this.doesResearch = research;
    }

    @Override
    public void conductTeaching() {
        System.out.println(name + " teaching advanced courses in " + dept);
    }

    @Override
    public void handleCurriculum() {
        System.out.println(name + " managing curriculum for " + dept);
        if (doesResearch) {
            System.out.println(name + " also doing research work");
        }
    }

    @Override
    public String getInstType() {
        return "University";
    }

    @Override
    public String getStaffDetails() {
        String info = "University Prof: " + name + " - " + dept;
        if (doesResearch) {
            info += " (Researcher)";
        }
        return info;
    }
}
