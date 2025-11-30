package services;

import Setting.Setting;
import database.FakeDatabase;
import models.Student;
import models.Teacher;

import java.util.ArrayList;
import java.util.List;


public class StudentService {

    // Admin registers student (with elective chosen when needed)
    public static boolean registerStudentByAdmin(String name, int age, int grade, String email, String password, List<String> electiveChoice) {
        if (!Setting.validName(name)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid name. Use letters A-Z only (spaces allowed).");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (!Setting.validAge(age)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid age. Must be between 6 and 18.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (!Setting.validGrade(grade)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid grade. Must be 1 to 12.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (!Setting.validEmailFormat(email)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid email format.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (emailExists(email)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Email already exists!");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (!Setting.validPassword(password)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid password. Must contain at least 10 letters, 3 numbers and 1 symbol.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }

        List<String> subjects = generateSubjectsForGrade(grade);
        if (grade >= 10) {
            if (electiveChoice == null || electiveChoice.isEmpty()) {
                System.out.println("=======================================================================");
                System.out.println();
                System.out.println(" Elective required for grade >=10.");
                System.out.println();
                System.out.println("=======================================================================");
                return false;
            }
            String elective = electiveChoice.get(0).trim();
            if (!elective.equalsIgnoreCase("Biology") && !elective.equalsIgnoreCase("Economy")) {
                System.out.println("=======================================================================");
                System.out.println();
                System.out.println(" Elective must be 'Biology' or 'Economy'.");
                System.out.println();
                System.out.println("=======================================================================");
                return false;
            }
            subjects.add(elective.equalsIgnoreCase("Biology") ? "Biology" : "Economy");
        }

        String className = "Grade-" + grade;
        Student s = new Student(FakeDatabase.studentIdCounter++, name.trim(), email.trim(), password, age, grade, className, subjects);
        FakeDatabase.students.add(s);
        System.out.println(" Student registered: " + s + "\n");
        return true;
    }

    public static boolean emailExists(String email) {
        for (Student s : FakeDatabase.students) if (s.getEmail().equalsIgnoreCase(email)) return true;
        return false;
    }

    public static Student loginStudent(String email, String password) {
        for (Student s : FakeDatabase.students) {
            if (s.getEmail().equalsIgnoreCase(email) && s.getPassword().equals(password)) {
                return s;
            }
        }
        return null;
    }

    public static List<String> generateSubjectsForGrade(int grade) {
        List<String> subs = new ArrayList<>();
        if (grade >= 1 && grade <= 9) { //1-9
            subs.add("Myanmar");
            subs.add("English");
            subs.add("Mathematics");
            subs.add("Science");
            subs.add("History");
            subs.add("Geography");
        } else { // 10-12
            subs.add("Myanmar");
            subs.add("English");
            subs.add("Mathematics");
            subs.add("Chemistry");
            subs.add("Physical");

        }
        return subs;
    }

    public static void showStudentLoginResponse(Student s) {
        System.out.println("Login successful!");
        System.out.println("username name - " + s.getName());
        System.out.println("age - " + s.getage());
        System.out.println("Grade - " + s.getGrade());
        System.out.print("Subject - ");
        System.out.println(String.join(" , ", s.getSubjects()));
        System.out.println();
    }

    public static void listAllStudents() {
        System.out.println("\n=== All Student ===");
        if (FakeDatabase.students.isEmpty()) {
            System.out.println("(no students)");
        } else {
            for (Student s : FakeDatabase.students) System.out.println(s);
        }
        System.out.println();
    }
}
