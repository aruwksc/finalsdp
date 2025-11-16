package org.example.finalsdp.abstractfactory;

public class HighSchoolFactory implements InstitutionFactory {
    @Override
    public InstStudent makeStudent(String name, String grade, String param2) {
        int gradeNum = Integer.parseInt(grade);
        return new HSStudent(name, gradeNum);
    }

    @Override
    public InstStaff makeStaff(String name, String subject, String param2) {
        return new HSStaff(name, subject);
    }

    @Override
    public String getInstitutionType() {
        return "High School";
    }
}