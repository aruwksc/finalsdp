package org.example.finalsdp.abstractfactory;

public class UniversityFactory implements InstitutionFactory {
    @Override
    public InstStudent makeStudent(String name, String major, String year) {
        int yearNum = Integer.parseInt(year);
        return new UniStudent(name, major, yearNum);
    }

    @Override
    public InstStaff makeStaff(String name, String dept, String research) {
        boolean isResearcher = Boolean.parseBoolean(research);
        return new UniStaff(name, dept, isResearcher);
    }

    @Override
    public String getInstitutionType() {
        return "University";
    }
}