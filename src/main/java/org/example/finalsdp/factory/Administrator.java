package org.example.finalsdp.factory;

public class Administrator implements User {
    private String adminName;
    private String adminID;
    private String adminDepartment;

    public Administrator(String name, String id, String dept) {
        this.adminName = name;
        this.adminID = id;
        this.adminDepartment = dept;
    }

    @Override
    public String getUserName() {
        return adminName;
    }

    @Override
    public String getUserId() {
        return adminID;
    }

    @Override
    public String getUserRole() {
        return "Administrator";
    }

    @Override
    public void showUserDetails() {
        System.out.println("--- Administrator Information ---");
        System.out.println("Admin Name: " + adminName);
        System.out.println("Admin ID: " + adminID);
        System.out.println("Department: " + adminDepartment);
        System.out.println("--------------------------------");
    }

    public String getAdminDepartment() {
        return adminDepartment;
    }
}