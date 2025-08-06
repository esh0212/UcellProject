package concurrency.UcellProject.service;

import concurrency.UcellProject.model.Role;
import concurrency.UcellProject.model.User;
import concurrency.UcellProject.util.Fileutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SuperAdminService {

     private static final String USER_FILE = "data/users.txt";
     public static void run(Scanner scanner){
         while(true){
             System.out.println("\n1. Admin qo'shish");
             System.out.println("2. Manager qo'shish");
             System.out.println("3. User o'chirish");
             System.out.println("4. Barcha foydalanuvchilar");
             System.out.println("0. Orqaga");
             System.out.print("Tanlang: ");
             String choice = scanner.nextLine();
             switch (choice) {
                 case "1" -> addUser(scanner, Role.ADMIN);
                 case "2" -> addUser(scanner, Role.MANAGER);
                 case "3" -> deleteUser(scanner);
                 case "4" -> listUsers();
                 case "0" -> {
                     return;
                 }
                 default -> System.out.println("Noto'g'ri tanlov!");
     }
}}
    private static void addUser(Scanner scanner, Role role) {
        System.out.print("To'liq ism: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Parol: ");
        String password = scanner.nextLine();

        User user = new User(name, email, password, role);
        Fileutil.writeLine(USER_FILE, Fileutil.toFileString(user));
        System.out.println("✅ " + role + " muvaffaqiyatli qo'shildi!");
    }

    private static void deleteUser(Scanner scanner) {
        System.out.print("O'chirmoqchi bo'lgan email: ");
        String email = scanner.nextLine();
        List<String> lines = Fileutil.readLines(USER_FILE);
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains(email)) {
                updated.add(line);
            }
        }
        Fileutil.overwriteFile(USER_FILE, updated);
        System.out.println("✅ Foydalanuvchi o'chirildi (agar mavjud bo'lsa)");
    }

    private static void listUsers() {
        List<String> lines = Fileutil.readLines(USER_FILE);
        for (String line : lines) {
            User user = Fileutil.fromFileString(line);
            if (user != null) {
                System.out.println("Ism: " + user.getFullName() + " | email" + user.getEmail() + " |  Yaratilgan: " + user.getCreatedAt());
            }
        }
    }
}
