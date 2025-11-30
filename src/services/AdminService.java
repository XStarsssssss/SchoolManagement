package services;

import database.FakeDatabase;

public class AdminService {

    public static boolean loginAsAdmin(String email, String password) {
        if (FakeDatabase.ADMIN_EMAIL.equalsIgnoreCase(email) && FakeDatabase.ADMIN_PASSWORD.equals(password)) {
            System.out.println("===========================================");
            System.out.println("✔ services.AdminService login successful.");
            System.out.println("===========================================");
            return true;
        }
        else {
            System.out.println("========================================");
            System.out.println(" services.AdminService login failed.");
            System.out.println("========================================");
            return false;
        }
    }
}
