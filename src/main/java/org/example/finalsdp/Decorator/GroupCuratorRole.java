package org.example.finalsdp.Decorator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupCuratorRole extends UserDecorator {
    private String groupName;

    public GroupCuratorRole(User user, String groupName) {
        super(user);
        this.groupName = groupName;
    }

    @Override
    public List<String> getPermissions() {
        List<String> permissions = new ArrayList<>(super.getPermissions());
        permissions.addAll(Arrays.asList(
                "view_group_students",
                "send_group_notifications",
                "manage_attendance"
        ));
        return permissions;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " + Role: Group curator '" + groupName + "'";
    }
}
