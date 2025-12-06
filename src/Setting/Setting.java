package Setting;

public class Setting {

    // Name
    public static boolean validName(String name) {
        if (name == null) return false;
        name = name.trim();
        return !name.isEmpty() && name.matches("[A-Za-z ]+");
    }

    // Age
    public static boolean validAge(int age) {
        return age >= 6 && age <= 18;
    }

    // Grade
    public static boolean validGrade(int grade) {
        return grade >= 1 && grade <= 12;
    }

    // Mail
    public static boolean validEmailFormat(String email) {
        if (email == null) return false;
        return email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[A-Za-z]{2,}$");
    }

    // Password
    public static boolean validPassword(String pwd) {
        if (pwd == null) return false;
        int letters = 0, digits = 0, symbols = 0;
        for (char c : pwd.toCharArray()) {
            if (Character.isLetter(c)) letters++;
            else if (Character.isDigit(c)) digits++;
            else if (!Character.isWhitespace(c)) symbols++;
        }
        return letters >= 5 && digits >= 3 && symbols >= 1;
    }
}
