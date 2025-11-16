package org.example.finalsdp.Decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeacherRole extends UserDecorator {
    public TeacherRole(User user) {
        super(user);
    }

    @Override
    public List<String> getPermissions() {
        List<String> permissions = new ArrayList<>(super.getPermissions());
        permissions.addAll(Arrays.asList(
                "add_grades",
                "view_all_students",
                "create_assignments"
        ));
        return permissions;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Role: Teacher";
    }
}
