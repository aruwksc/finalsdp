package org.example.finalsdp.abstractfactory;

public class VocStudent implements InstStudent {
    private String name;
    private String tradeSkill;

    public VocStudent(String studentName, String trade) {
        this.name = studentName;
        this.tradeSkill = trade;
    }

    @Override
    public void studyProcess() {
        System.out.println(name + " learning practical " + tradeSkill + " skills");
    }

    @Override
    public void goToClass() {
        System.out.println(name + " attending workshop training sessions");
    }

    @Override
    public String getInstType() {
        return "Vocational School";
    }

    @Override
    public String getStudentDetails() {
        return "Vocational Student: " + name + " - " + tradeSkill;
    }
}