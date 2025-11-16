package org.example.finalsdp.bridge;

import org.example.finalsdp.factory.Administrator;
import org.example.finalsdp.factory.Student;
import org.example.finalsdp.factory.Teacher;
import org.example.finalsdp.factory.User;

public class UserProfileViewer extends ProfileViewer {
    private User userObj;

    public UserProfileViewer(DisplayFormat displayFormat, User user) {
        super(displayFormat);
        this.userObj = user;
    }

    @Override
    public void renderProfile() {
        format.showHeader(userObj.getUserRole() + " Profile");

        StringBuilder content = new StringBuilder();
        content.append("Name: ").append(userObj.getUserName()).append("\n");
        content.append("ID: ").append(userObj.getUserId()).append("\n");
        content.append("Role: ").append(userObj.getUserRole()).append("\n");

        if (userObj instanceof Student) {
            Student s = (Student) userObj;
            content.append("Major: ").append(s.getStudentMajor()).append("\n");
            content.append("Grade: ").append(s.getCurrentGrade());
        } else if (userObj instanceof Teacher) {
            Teacher t = (Teacher) userObj;
            content.append("Subject: ").append(t.getSubjectTaught()).append("\n");
            content.append("Experience: ").append(t.getExperienceYears()).append(" years");
        } else if (userObj instanceof Administrator) {
            Administrator a = (Administrator) userObj;
            content.append("Department: ").append(a.getAdminDepartment());
        }

        format.showContent(content.toString());
        format.showFooter();
    }
}
