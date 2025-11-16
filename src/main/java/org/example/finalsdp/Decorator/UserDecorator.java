package org.example.finalsdp.Decorator;

import java.util.List;

public abstract class UserDecorator extends User {
    protected User user;

    public UserDecorator(User user) {
        super(user.getName(), user.getUserId());
        this.user = user;
    }

    @Override
    public List<String> getPermissions() {
        return user.getPermissions();
    }

    @Override
    public String getDescription() {
        return user.getDescription();
    }
}
