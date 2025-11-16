import org.example.finalsdp.abstractfactory.*;
import org.example.finalsdp.bridge.*;
import org.example.finalsdp.factory.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SchoolManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<User> userList = new ArrayList<>();
    private static List<Object> institutionMembers = new ArrayList<>();
    private static DisplayFormat currentDisplay = new ConsoleDisplayFormat();

    public static void main(String[] args) {
        showWelcomeScreen();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    createUserMenu();
                    break;
                case 2:
                    createInstitutionMemberMenu();
                    break;
                case 3:
                    viewAllUsers();
                    break;
                case 4:
                    viewInstitutionMembers();
                    break;
                case 5:
                    changeDisplayFormatMenu();
                    break;
                case 6:
                    showSystemInformation();
                    break;
                case 0:
                    System.out.println("\nThank you for using School Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }

        scanner.close();
    }

    private static void showWelcomeScreen() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║                                                ║");
        System.out.println("║     SCHOOL MANAGEMENT SYSTEM v1.0              ║");
        System.out.println("║                                                ║");
        System.out.println("║     Welcome to Educational Institution         ║");
        System.out.println("║     Management Platform                        ║");
        System.out.println("║                                                ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }

    private static void showMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("                   MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Register New User");
        System.out.println("2. Register Institution Member");
        System.out.println("3. View All Users");
        System.out.println("4. View Institution Members");
        System.out.println("5. Change Display Format");
        System.out.println("6. System Information");
        System.out.println("0. Exit");
        System.out.println("=".repeat(50));
    }

    private static void createUserMenu() {
        System.out.println("\n--- REGISTER NEW USER ---");
        System.out.println("1. Register Student");
        System.out.println("2. Register Teacher");
        System.out.println("3. Register Administrator");
        System.out.print("Choose user type: ");

        int choice = getIntInput("");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter ID: ");
        String id = scanner.nextLine();

        User newUser = null;

        try {
            if (choice == 1) {
                System.out.print("Enter major: ");
                String major = scanner.nextLine();
                System.out.print("Enter grade (1-12): ");
                String grade = scanner.nextLine();
                newUser = UserFactory.createNewUser("student", name, id, major, grade);
            } else if (choice == 2) {
                System.out.print("Enter subject: ");
                String subject = scanner.nextLine();
                System.out.print("Enter years of experience: ");
                String years = scanner.nextLine();
                newUser = UserFactory.createNewUser("teacher", name, id, subject, years);
            } else if (choice == 3) {
                System.out.print("Enter department: ");
                String dept = scanner.nextLine();
                newUser = UserFactory.createNewUser("admin", name, id, dept, "");
            }

            if (newUser != null) {
                userList.add(newUser);
                System.out.println("\n✓ User created successfully!");

                ProfileViewer viewer = new UserProfileViewer(currentDisplay, newUser);
                viewer.renderProfile();
            }
        } catch (Exception e) {
            System.out.println("\n✗ Error creating user: " + e.getMessage());
        }
    }

    private static void createInstitutionMemberMenu() {
        System.out.println("\n--- REGISTER INSTITUTION MEMBER ---");
        System.out.println("1. High School");
        System.out.println("2. University");
        System.out.println("3. Vocational School");
        System.out.print("Choose institution type: ");

        int instChoice = getIntInput("");

        System.out.println("\n1. Create Student");
        System.out.println("2. Create Staff");
        System.out.print("Choose member type: ");
        int memberChoice = getIntInput("");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        InstitutionFactory factory = null;
        Object member = null;

        try {
            if (instChoice == 1) {
                factory = new HighSchoolFactory();
                if (memberChoice == 1) {
                    System.out.print("Enter grade level (9-12): ");
                    String grade = scanner.nextLine();
                    member = factory.makeStudent(name, grade, "");
                } else {
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();
                    member = factory.makeStaff(name, subject, "");
                }
            } else if (instChoice == 2) {
                factory = new UniversityFactory();
                if (memberChoice == 1) {
                    System.out.print("Enter major: ");
                    String major = scanner.nextLine();
                    System.out.print("Enter year (1-4): ");
                    String year = scanner.nextLine();
                    member = factory.makeStudent(name, major, year);
                } else {
                    System.out.print("Enter department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Is researcher? (true/false): ");
                    String research = scanner.nextLine();
                    member = factory.makeStaff(name, dept, research);
                }
            } else if (instChoice == 3) {
                factory = new VocationalFactory();
                if (memberChoice == 1) {
                    System.out.print("Enter trade skill: ");
                    String trade = scanner.nextLine();
                    member = factory.makeStudent(name, trade, "");
                } else {
                    System.out.print("Enter expertise area: ");
                    String skill = scanner.nextLine();
                    member = factory.makeStaff(name, skill, "");
                }
            }

            if (member != null) {
                institutionMembers.add(member);
                System.out.println("\n✓ Institution member created successfully!");

                // show what they can do
                if (member instanceof InstStudent) {
                    InstStudent s = (InstStudent) member;
                    s.studyProcess();
                    s.goToClass();
                } else if (member instanceof InstStaff) {
                    InstStaff s = (InstStaff) member;
                    s.conductTeaching();
                    s.handleCurriculum();
                }
            }
        } catch (Exception e) {
            System.out.println("\n✗ Error creating institution member: " + e.getMessage());
        }
    }

    private static void viewAllUsers() {
        System.out.println("\n--- ALL USERS IN SYSTEM ---");

        if (userList.isEmpty()) {
            System.out.println("No users in the system yet.");
            return;
        }

        System.out.println("Total users: " + userList.size());
        System.out.println();

        for (int i = 0; i < userList.size(); i++) {
            System.out.println((i + 1) + ". " + userList.get(i).getUserName() +
                    " (" + userList.get(i).getUserRole() + ")");
        }

        System.out.print("\nEnter user number to view details (0 to go back): ");
        int choice = getIntInput("");

        if (choice > 0 && choice <= userList.size()) {
            User selectedUser = userList.get(choice - 1);
            ProfileViewer viewer = new UserProfileViewer(currentDisplay, selectedUser);
            viewer.renderProfile();
        }
    }

    private static void viewInstitutionMembers() {
        System.out.println("\n--- INSTITUTION MEMBERS ---");

        if (institutionMembers.isEmpty()) {
            System.out.println("No institution members created yet.");
            return;
        }

        System.out.println("Total members: " + institutionMembers.size());
        System.out.println();

        for (int i = 0; i < institutionMembers.size(); i++) {
            Object member = institutionMembers.get(i);
            String info = "";
            if (member instanceof InstStudent) {
                info = ((InstStudent) member).getStudentDetails();
            } else if (member instanceof InstStaff) {
                info = ((InstStaff) member).getStaffDetails();
            }
            System.out.println((i + 1) + ". " + info);
        }

        System.out.print("\nEnter member number to view activities (0 to go back): ");
        int choice = getIntInput("");

        if (choice > 0 && choice <= institutionMembers.size()) {
            Object member = institutionMembers.get(choice - 1);
            System.out.println();
            if (member instanceof InstStudent) {
                InstStudent s = (InstStudent) member;
                s.studyProcess();
                s.goToClass();
            } else if (member instanceof InstStaff) {
                InstStaff s = (InstStaff) member;
                s.conductTeaching();
                s.handleCurriculum();
            }
        }
    }

    private static void changeDisplayFormatMenu() {
        System.out.println("\n--- CHANGE DISPLAY FORMAT ---");
        System.out.println("Current format: " + currentDisplay.getClass().getSimpleName());
        System.out.println("\n1. Console Format");
        System.out.println("2. Web Format");
        System.out.println("3. Mobile Format");
        System.out.print("Choose display format: ");

        int choice = getIntInput("");

        if (choice == 1) {
            currentDisplay = new ConsoleDisplayFormat();
            System.out.println("\n✓ Display format changed to Console");
        } else if (choice == 2) {
            currentDisplay = new WebDisplayFormat();
            System.out.println("\n✓ Display format changed to Web");
        } else if (choice == 3) {
            currentDisplay = new MobileDisplayFormat();
            System.out.println("\n✓ Display format changed to Mobile");
        }

        // demonstrate with existing user if available
        if (!userList.isEmpty()) {
            System.out.println("\nDemonstrating new format with first user:");
            ProfileViewer viewer = new UserProfileViewer(currentDisplay, userList.get(0));
            viewer.renderProfile();
        }
    }

    private static void showSystemInformation() {
        System.out.println("\n╔════════════════════════════════════════════════╗");
        System.out.println("║           SYSTEM INFORMATION                   ║");
        System.out.println("╚════════════════════════════════════════════════╝");

        System.out.println("\nSystem Statistics:");
        System.out.println("- Total Registered Users: " + userList.size());
        System.out.println("- Total Institution Members: " + institutionMembers.size());
        System.out.println("- Current Display Format: " + currentDisplay.getClass().getSimpleName());

        System.out.println("\nUser Type Breakdown:");
        int studentCount = 0, teacherCount = 0, adminCount = 0;
        for (User user : userList) {
            if (user instanceof Student) studentCount++;
            else if (user instanceof Teacher) teacherCount++;
            else if (user instanceof Administrator) adminCount++;
        }
        System.out.println("- Students: " + studentCount);
        System.out.println("- Teachers: " + teacherCount);
        System.out.println("- Administrators: " + adminCount);

        System.out.println("\nSupported Institution Types:");
        System.out.println("- High School");
        System.out.println("- University");
        System.out.println("- Vocational School");

        System.out.println("\nAvailable Display Formats:");
        System.out.println("- Console Format");
        System.out.println("- Web Format");
        System.out.println("- Mobile Format");

        System.out.println("\n" + "=".repeat(50));
    }

    // Helper method to get integer input
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid number: ");
        }
        int value = scanner.nextInt();
        scanner.nextLine(); // consume newline
        return value;
    }
}