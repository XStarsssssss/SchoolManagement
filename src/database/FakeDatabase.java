package database;

import models.Student;
import models.Syllabus;
import models.Teacher;

import java.util.ArrayList;
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


}


