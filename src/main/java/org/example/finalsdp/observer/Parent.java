package org.example.finalsdp.observer;
import lombok.Data;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.*;

@Data
@AllArgsConstructor
public class Parent implements Observer {
    private String name;
    private String childName;
    private List<String> notifications = new ArrayList<>();

    public Parent(String name, String childName) {
        this.name = name;
        this.childName = childName;
        this.notifications = new ArrayList<>();
    }

    @Override
    public void update(String message, Map<String, Object> data) {
        String notification = String.format("[%s] about student %s: %s",
                LocalDateTime.now(), childName, message);
        notifications.add(notification);
        System.out.println("Parent " + name + " get notification: " + message);
    }
}
