package org.example.finalsdp.abstractfactory;


public class VocationalFactory implements InstitutionFactory {
    @Override
    public InstStudent makeStudent(String name, String trade, String param2) {
        return new VocStudent(name, trade);
    }

    @Override
    public InstStaff makeStaff(String name, String skill, String param2) {
        return new VocStaff(name, skill);
    }

    @Override
    public String getInstitutionType() {
        return "Vocational School";
    }
}
