package org.example.finalsdp.Decorator;
import java.util.List;

public abstract class User {
    protected String name;
    protected String userId;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public abstract List<String> getPermissions();
    public abstract String getDescription();

    public String getName() { return name; }
    public String getUserId() { return userId; }
}
