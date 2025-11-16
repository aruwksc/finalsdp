package org.example.finalsdp.Decorator;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    private final Map<String, User> users = new HashMap<>();

    public User createUser(String name, String userId) {
        User user = new BasicUser(name, userId);
        users.put(userId, user);
        log.info("Created basic user: {} ({})", name, userId);
        return user;
    }

    public User addTeacherRole(User user) {
        User decoratedUser = new TeacherRole(user);
        users.put(user.getUserId(), decoratedUser);
        log.info("Added teacher role to user: {}", user.getName());
        return decoratedUser;
    }

    public User addAdminRole(User user) {
        User decoratedUser = new AdminRole(user);
        users.put(user.getUserId(), decoratedUser);
        log.info("Added admin role to user: {}", user.getName());
        return decoratedUser;
    }

    public User addGroupCuratorRole(User user, String groupName) {
        User decoratedUser = new GroupCuratorRole(user, groupName);
        users.put(user.getUserId(), decoratedUser);
        log.info("Added curator role to user: {} for group: {}", user.getName(), groupName);
        return decoratedUser;
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
}
