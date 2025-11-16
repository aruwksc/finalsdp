package org.example.finalsdp.Decorator;
import java.util.Arrays;
import java.util.List;

public class BasicUser extends User {
    public BasicUser(String name, String userId) {
        super(name, userId);
    }

    @Override
    public List<String> getPermissions() {
        return Arrays.asList("view_profile", "edit_own_profile");
    }

    @Override
    public String getDescription() {
        return "User: " + name;
    }
}
