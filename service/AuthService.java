package concurrency.UcellProject.service;

import concurrency.UcellProject.model.Role;
import concurrency.UcellProject.model.User;
import concurrency.UcellProject.util.Fileutil;
import concurrency.UcellProject.util.Validator;

import java.util.List;
import java.util.Scanner;

public class AuthService {
    private static final String USER_FILE = "data/users.txt";
    private static final String LOG_FILE = "data/logs.txt";

    public static User register(Scanner scanner) {
        System.out.println("To‘liq ismingizni kiriting:");
        String fullName = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();
        if (!Validator.isValidEmail(email)) {
            System.out.println(" Email noto'g'ri formatda");
            return null;
        }

        System.out.println("Parolni kiriting:");
        String password = scanner.nextLine();

        Role role = null;
        while (role == null) {
            System.out.println("Roleni tanlang (SUPER_ADMIN / ADMIN / MANAGER / USER):");
            String roleInput = scanner.nextLine().trim().toUpperCase();

            try {
                role = Role.valueOf(roleInput); //
            } catch (IllegalArgumentException e) {
                System.out.println(" Bunday rol mavjud emas. Qayta urinib ko‘ring.");
            }
        }
        User user = new User(fullName, email, password, role);

        Fileutil.writeLine(USER_FILE, Fileutil.toFileString(user));
        Fileutil.writeLine(LOG_FILE, "Ro'yxatdan o'tdi: " + email + " | " + user.getCreatedAt());

        System.out.println("✅ Muvaffaqiyatli ro'yxatdan o'tdingiz!");
        return user;
    }


    public static User login(Scanner scanner) {
        System.out.println("Emailingizni kirtiting");
        String email = scanner.nextLine();
        System.out.println("Parolingizni kiriting");
        String password = scanner.nextLine();

        List<String> lines = Fileutil.readLines(USER_FILE);
        for (String line : lines) {
            User user = Fileutil.fromFileString(line);
            if (user != null && user.getEmail().equals(email) && user.getPassword().equals(password)) {
                Fileutil.writeLine(LOG_FILE, "Tizimga kirdi: " + email + "|" + java.time.LocalDateTime.now());
                return user;
            }
        }
        System.out.println("Email yoki parol xato!!! ");
        return null;
    }
}

