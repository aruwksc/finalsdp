package org.example.finalsdp.abstractfactory;

public interface InstitutionFactory {
    InstStudent makeStudent(String name, String param1, String param2);
    InstStaff makeStaff(String name, String param1, String param2);
    String getInstitutionType();
}
