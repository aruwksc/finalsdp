package org.example.finalsdp.Decorator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminRole extends UserDecorator {
    public AdminRole(User user) {
        super(user);
    }

    @Override
    public List<String> getPermissions() {
        List<String> permissions = new ArrayList<>(super.getPermissions());
        permissions.addAll(Arrays.asList(
                "manage_users",
                "delete_records",
                "view_all_data",
                "system_settings"
        ));
        return permissions;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Role: Admin";
    }
}

