package services;

import Setting.Setting;
import database.FakeDatabase;
import models.Teacher;

import java.util.List;

public class TeacherService {

    // Admin registers teacher
    public static boolean registerTeacherByAdmin(String name, String email, String password, List<String> subjects, String className, String phone) {
        if (!Setting.validName(name)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid name. Use letters A-Z only (spaces allowed).");
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
            System.out.println(" Invalid password. Must contain at least 5 letters, 3 numbers and 1 symbol.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (className == null || !FakeDatabase.classes.contains(className)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Invalid class. Choose existing class like Grade-1 .. Grade-12.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        Teacher t = new Teacher(FakeDatabase.teacherIdCounter++, name.trim(), email.trim(), password, subjects, className, phone);
        FakeDatabase.teachers.add(t);
        System.out.println("✔ Teacher registered: " + t + "\n");
        return true;
    }

    public static boolean emailExists(String email) {
        for (Teacher t : FakeDatabase.teachers) if (t.getEmail().equalsIgnoreCase(email)) return true;
        return false;
    }

    public static Teacher loginTeacher(String email, String password) {
        for (Teacher t : FakeDatabase.teachers) {
            if (t.getEmail().equalsIgnoreCase(email) && t.getPassword().equals(password)) {
                return t;
            }
        }
        return null;
    }

    public static void showTeacherAccountInfo(Teacher t) {
        System.out.println("Tr Name - " + t.getName());
        System.out.println("Show All Class: " + String.join(", ", t.getclassName()));
        System.out.println("Show All Subject: " + String.join(" , ", t.getSubjects()));
        System.out.println("========================================================================");
        System.out.println();
    }

    public static void listAllTeachers() {
        System.out.println("\n=== All Teachers ===");
        if (FakeDatabase.teachers.isEmpty()) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println("(no teachers)");
            System.out.println();
            System.out.println("=======================================================================");
        } else {
            for (Teacher t : FakeDatabase.teachers) System.out.println(t);
        }
        System.out.println();
    }
}
