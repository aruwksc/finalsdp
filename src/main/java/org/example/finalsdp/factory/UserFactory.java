package org.example.finalsdp.factory;

public class UserFactory {
    public static User createNewUser(String userType, String name, String id, String detail1, String detail2) {
        if (userType.equalsIgnoreCase("student")) {
            int grade = Integer.parseInt(detail2);
            return new Student(name, id, detail1, grade);
        } else if (userType.equalsIgnoreCase("teacher")) {
            int years = Integer.parseInt(detail2);
            return new Teacher(name, id, detail1, years);
        } else if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("administrator")) {
            return new Administrator(name, id, detail1);
        } else {
            System.out.println("Error: Unknown user type!");
            return null;
        }
    }
}