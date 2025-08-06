package concurrency.UcellProject;

import concurrency.UcellProject.model.User;
import concurrency.UcellProject.service.AuthService;
import concurrency.UcellProject.service.MenuService;

import java.util.Scanner;

import static concurrency.UcellProject.service.ManagerService.scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\n1. Ro'yxatdan o'tish");
            System.out.println("2. Tizimga kirish");
            System.out.println("0. Chiqish");
            System.out.print("Tanlang: ");

            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> currentUser = AuthService.register(sc);
                case "2" -> currentUser = AuthService.login(sc);
                case "0" -> {
                    System.out.println("Dasturdan chiqildi. ");
                    return;
                }
                default -> System.out.println("Noto'g'ri tanlov");
            }
            if (currentUser != null) {
                MenuService.run(currentUser, sc);
            }
        }


    }
}
