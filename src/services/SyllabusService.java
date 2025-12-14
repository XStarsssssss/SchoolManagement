package services;

import database.FakeDatabase;
import models.Syllabus;
import models.Teacher;

public class SyllabusService{

    public static boolean addSyllabus(Teacher teacher, String className, String subject, String title, String description) {
        if (teacher == null) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Teacher not logged in.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        if (!FakeDatabase.classes.contains(className)) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" Class does not exist.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        boolean teaches = false;
        for (String s : teacher.getSubjects()) {
            if (s.equalsIgnoreCase(subject)) { teaches = true; break; }
        }
        if (!teaches) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println(" You do not teach this subject.");
            System.out.println();
            System.out.println("=======================================================================");
            return false;
        }
        Syllabus sy = new Syllabus(FakeDatabase.syllabusIdCounter++, teacher.getId(), className, subject, title, description);
        FakeDatabase.syllabuses.add(sy);
        System.out.println(" Syllabus Added: " + sy + "\n");
        showSyllablesByTeacher(teacher.getId());
        return true;
    }

    public static void showSyllablesByTeacher(int teacherId) {
        System.out.println("\n=== All Syllables (your own) ===");
        boolean any = false;
        for (Syllabus sy : FakeDatabase.syllabuses) {
            if (sy.getTeacherId() == teacherId) {
                System.out.println(sy);
                any = true;
            }
        }
        if (!any) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println("(no syllables yet)");
            System.out.println();
            System.out.println("=======================================================================");
        }
    }

    public static void showSyllablesByClass(String className) {
        System.out.println("\n=== Syllables for " + className + " ===");
        boolean any = false;
        for (Syllabus sy : FakeDatabase.syllabuses) {
            if (sy.getClassName().equalsIgnoreCase(className)) {
                System.out.println(sy);
                any = true;
            }
        }
        if (!any) {
            System.out.println("=======================================================================");
            System.out.println();
            System.out.println("(no syllables for this class)");
            System.out.println();
            System.out.println("=======================================================================");
        }
    }
}
