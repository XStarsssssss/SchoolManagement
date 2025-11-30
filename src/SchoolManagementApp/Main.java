package SchoolManagementApp;

import database.FakeDatabase;
import models.Teacher;
import models.Student;
import services.AdminService;
import services.StudentService;
import services.SyllabusService;
import services.TeacherService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        boolean adminLogged = false;
        Teacher loggedTeacher = null;

        while (running) {
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("           School Management Menu ");
            System.out.println();
            System.out.println("--------------------------------------------");
            System.out.println();
            System.out.println("1. Admin Login");
            System.out.println();
            System.out.println("2. Student Login");
            System.out.println();
            System.out.println("3. Teacher Login (Tr)");
            System.out.println();
            System.out.println("4. Admin Menu (register students/teachers) [admin only]");
            System.out.println();
            System.out.println("5. Teacher: Add Syllabus (must be logged as Tr)");
            System.out.println();
            System.out.println("6. Show Syllables by Class");
            System.out.println();
            System.out.println("7. Show All Students");
            System.out.println();
            System.out.println("8. Show All Teachers");
            System.out.println();
            System.out.println("9. Logout Admin");
            System.out.println();
            System.out.println("0. Exit");
            System.out.println("==========================================");
            System.out.print("Choose: ");


            String line = sc.nextLine();
            int choice;
            try { choice = Integer.parseInt(line); } catch (Exception e) { System.out.println("Invalid input\n"); continue; }

            switch (choice) {
                case 1: // Admin Login
                    if (adminLogged) {
                        System.out.println("==========================================");
                        System.out.println(" Already admin logged in.\n");
                        System.out.println("==========================================");
                        break;
                    }
                    System.out.print("Admin Email: ");
                    String aEmail = sc.nextLine();
                    System.out.print("Admin Password: ");
                    String aPass = sc.nextLine();
                    adminLogged = AdminService.loginAsAdmin(aEmail, aPass);
                    break;

                case 2: // Student Login
                    System.out.print("Student Email: ");
                    String sEmail = sc.nextLine();
                    System.out.print("Student Password: ");
                    String sPass = sc.nextLine();
                    Student s = StudentService.loginStudent(sEmail, sPass);
                    if (s == null) {
                        System.out.println("==========================================");
                        System.out.println("Login failed! Invalid email or password.");
                        System.out.println("==========================================");
                    } else {
                        StudentService.showStudentLoginResponse(s);
                    }
                    break;

                case 3: // Teacher Login
                    System.out.print("Teacher Email: ");
                    String tEmail = sc.nextLine();
                    System.out.print("Teacher Password: ");
                    String tPass = sc.nextLine();
                    Teacher t = TeacherService.loginTeacher(tEmail, tPass);
                    if (t == null) {
                        System.out.println("==========================================");
                        System.out.println("Login failed! Invalid email or password.");
                        System.out.println("==========================================");
                    } else {
                        loggedTeacher = t;
                        System.out.println("==========================================");
                        System.out.println("✔ Teacher login successful.");
                        System.out.println("==========================================");
                        System.out.println();
                        TeacherService.showTeacherAccountInfo(t);
                    }
                    break;

                case 4: // Admin Menu: register students & teachers
                    if (!adminLogged) {
                        System.out.println();
                        System.out.println("=======================================================");
                        System.out.println("Only admin can access this. Please login as admin.");
                        System.out.println("=======================================================");
                        System.out.println();
                        break;
                    }
                    adminSubmenu(sc);
                    break;

                case 5: // Teacher add syllabus (must be logged in as teacher)
                    if (loggedTeacher == null) {
                        System.out.println("=======================================================================");
                        System.out.println("Only logged-in teacher can add syllabus. Please login as Tr first.");
                        System.out.println("========================================================================");
                        System.out.println();
                        break;
                    }
                    System.out.println("Logged as: " + loggedTeacher.getName());
                    System.out.print("Enter Class (e.g., Grade-10): ");
                    String syClass = sc.nextLine();
                    if (!FakeDatabase.classes.contains(syClass)) {
                        System.out.println("Class does not exist.");
                        break;
                    }

                    System.out.println("Your Subjects: " + String.join(" , ", loggedTeacher.getSubjects()));
                    System.out.print("Enter Subject (choose one of your subjects): ");
                    String sySub = sc.nextLine();
                    if (loggedTeacher.getSubjects().stream().noneMatch(ss -> ss.equalsIgnoreCase(sySub))) {
                        System.out.println("==========================================");
                        System.out.println("You do not teach this subject.\n");
                        System.out.println("==========================================");
                        break;
                    }

                    System.out.print("Enter Title/Chapter: ");
                    String syTitle = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String syDesc = sc.nextLine();
                    SyllabusService.addSyllabus(loggedTeacher, syClass, sySub, syTitle, syDesc);
                    break;

                case 6: // Show syllables by class
                    System.out.print("Enter Class (e.g., Grade-10): ");
                    String cl = sc.nextLine();
                    if (!FakeDatabase.classes.contains(cl)) {
                        System.out.println("==========================================");
                        System.out.println("Class does not exist.");
                        System.out.println("==========================================");
                        break;
                    }
                    SyllabusService.showSyllablesByClass(cl);
                    break;

                case 7:
                    StudentService.listAllStudents();
                    break;

                case 8:
                    TeacherService.listAllTeachers();
                    break;

                case 9:
                    if (!adminLogged){
                        System.out.println("=================================");
                        System.out.println("No admin logged in.");
                        System.out.println("=================================");
                    }
                    else {
                        adminLogged = false;
                        System.out.println("=================================");
                        System.out.println(" Admin logged out.");
                        System.out.println("=================================");
                    }
                    break;

                case 0:
                    running = false;
                    System.out.println("=================================");
                    System.out.println("Exiting program...");
                    System.out.println("=================================");
                    break;

                default:
                    System.out.println("=================================");
                    System.out.println("Invalid choice.");
                    System.out.println("=================================");
            }
        }

        sc.close();
    }

    // Admin submenu
    private static void adminSubmenu(Scanner sc) {
        boolean back = false;
        while (!back) {
            System.out.println("------------------------------");
            System.out.println();
            System.out.println("        Admin Menu  ");
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println("1. Register Student");
            System.out.println("2. Register Teacher (Tr)");
            System.out.println("3. Back");
            System.out.print("Choose: ");
            String line = sc.nextLine();


            int pick;
            try { pick = Integer.parseInt(line); } catch (Exception e) { System.out.println("Invalid input");
                continue;
            }

            switch (pick) {
                case 1: // Register Student
                    System.out.print("Enter Student Name: ");
                    String sName = sc.nextLine();
                    System.out.print("Enter Age: ");

                    int age;
                    try {
                        age = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("=================================");
                        System.out.println("Invalid age");
                        System.out.println("=================================");
                        break;
                    }

                    System.out.print("Enter Grade (1-12): ");
                    int grade;

                    try {
                        grade = Integer.parseInt(sc.nextLine());
                    } catch (Exception e) {
                        System.out.println("=================================");
                        System.out.println("Invalid grade");
                        System.out.println("=================================");
                        break;
                    }

                    System.out.print("Enter Email: ");
                    String sEmail = sc.nextLine();
                    System.out.println("Password rules: at least 5 letters, 3 digits and 1 symbol.");
                    System.out.print("Enter Password: ");
                    String sPass = sc.nextLine();

                    List<String> electiveList = new ArrayList<>();
                    if (grade >= 10) {
                        System.out.print("Choose elective (Biology or Economy): ");
                        String elective = sc.nextLine().trim();
                        electiveList.add(elective);
                    }

                    StudentService.registerStudentByAdmin(sName, age, grade, sEmail, sPass, electiveList);
                    break;

                case 2: // Register Teacher
                    System.out.print("Enter Teacher Name: ");
                    String tName = sc.nextLine();
                    System.out.print("Enter Email: ");
                    String tEmail = sc.nextLine();
                    System.out.println("Password rules: at least 5 letters, 3 digits and 1 symbol.");
                    System.out.print("Enter Password: ");
                    String tPass = sc.nextLine();
                    System.out.print("Enter Class to assign (e.g., Grade-10): ");
                    String tClass = sc.nextLine();
                    System.out.print("Enter Phone: ");
                    String tPhone = sc.nextLine();
                    System.out.println("Enter subjects (comma separated), e.g., Mathematics, Chemistry");
                    System.out.print("Subjects: ");
                    String subLine = sc.nextLine();
                    List<String> tSubs = new ArrayList<>();
                    for (String sub : subLine.split(",")) {
                        String tr = sub.trim();
                        if (!tr.isEmpty()) tSubs.add(tr);
                    }
                    TeacherService.registerTeacherByAdmin(tName, tEmail, tPass, tSubs, tClass, tPhone);
                    break;

                case 3:
                    back = true;
                    break;

                default:
                    System.out.println("=================================");
                    System.out.println("Invalid option");
                    System.out.println("=================================");
            }
        }
    }
}
