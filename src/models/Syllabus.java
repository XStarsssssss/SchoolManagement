package models;

public class Syllabus {
    private int id;
    private int teacherId;
    private String className;
    private String subject;
    private String title;
    private String description;

    public Syllabus(int id, int teacherId, String className, String subject, String title, String description) {
        this.id = id;
        this.teacherId = teacherId;
        this.className = className;
        this.subject = subject;
        this.title = title;
        this.description = description;
    }

    public int getId() { return id; }
    public int getTeacherId() { return teacherId; }
    public String getClassName() { return className; }
    public String getSubject() { return subject; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return "[" + className + "] " + subject + " - " + title + ": " + description + " (by teacherId:" + teacherId + ")";
    }
}

