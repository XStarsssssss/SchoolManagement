package database;

import models.Student;
import models.Syllabus;
import models.Teacher;
import services.StudentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FakeDatabase {
    public static List<Student> students = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();
    public static List<Syllabus> syllabuses = new ArrayList<>();
    public static List<String> classes = new ArrayList<>();

    public static int studentIdCounter = 1;
    public static int teacherIdCounter = 1;
    public static int syllabusIdCounter = 1;


    public static final String ADMIN_EMAIL = "admin";
    public static final String ADMIN_PASSWORD = "admin";

    static {
        for (int i = 1; i <= 12; i++) classes.add("Grade-" + i);
    }
    static {
        teachers.add(new Teacher(
                teacherIdCounter++,
                "Tr Shin",
                "Shin@mndfs.org",
                "shinn123*",
                Collections.singletonList("Geography"),
                "Grade-6",
                "0912345678910"
        ));
        teachers.add(new Teacher(
                teacherIdCounter++,
                "Tr Moon",
                "moon@mndfs.org",
                "moonn123*",
                Collections.singletonList("Biology"),
                "Grade-12",
                "0912345678911"
        ));
        teachers.add(new Teacher(
                teacherIdCounter++,
                "Sir Cloud",
                "Cloud@mndfs.org",
                "cloud123*",
                List.of("ICT", "Music", "Piano"),
                "Extra Class",
                "0912345678912"
        ));
        teachers.add(new Teacher(
                teacherIdCounter++,
                "Sir Ruby",
                "ruby@mndfs.org",
                "rubyy123*",
                List.of("English", "English 4 Skill"),
                "Grade-12",
                "0912345678913"
        ));
        teachers.add(new Teacher(
                teacherIdCounter++,
                "Sir Autumn",
                "Autumn@mndfs.org",
                "autumn123*",
                List.of("Java", "HTML | CSS | JavaScript", "DataBase"),
                "Extra Class",
                "0912345678912"
        ));
    }
}


