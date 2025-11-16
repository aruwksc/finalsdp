package org.example.finalsdp.observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Student implements Observer {
    private String name;
    private String studentId;
    private List<String> notifications;

    public Student(String name, String studentId) {
        this.name = name;
        this.studentId = studentId;
        this.notifications = new ArrayList<>();
    }

    @Override
    public void update(String message, Map<String, Object> data) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String notification = "[" + timestamp + "] " + message;
        notifications.add(notification);
        System.out.println("📬 Student " + name + " get notification: " + message);
        if (data != null && !data.isEmpty()) {
            System.out.println("   Data: " + data);
        }
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public List<String> getNotifications() {
        return notifications;
    }
}