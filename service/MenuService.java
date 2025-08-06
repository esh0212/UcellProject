package concurrency.UcellProject.service;

import concurrency.UcellProject.model.User;

import java.util.Scanner;

public class MenuService {
    public static void run(User user, Scanner scanner) {
        if (user == null || user.getRole() == null) {
            System.out.println("Foydalanuvchi yoki roli aniqlanmadi.");
            return;
        }

        switch (user.getRole()) {
            case SUPER_ADMIN -> SuperAdminService.run(scanner);
            case ADMIN -> AdminService.run(scanner);
            case MANAGER -> ManagerService.run(scanner);
            case USER -> UserService.run(user, scanner);
            default -> System.out.println("Noma'lum rol: " + user.getRole());
        }
    }
}
