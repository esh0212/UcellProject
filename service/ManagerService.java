package concurrency.UcellProject.service;

import concurrency.UcellProject.model.Number;
import concurrency.UcellProject.model.USSD;
import concurrency.UcellProject.util.Fileutil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagerService {
    public static final Scanner scanner = new Scanner(System.in);
    private static final String NOMER_FILE = "data/numbers.txt";
    private static final String USSD_FILE = "data/ussd.txt";

    public static void run(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Manager Panel ---");
            System.out.println("1. USSD menyu qo‘shish");
            System.out.println("2. USSD menyular ro‘yxati");
            System.out.println("3. Raqamni bloklash/blokdan chiqarish");
            System.out.println("0. Orqaga");
            System.out.print("Tanlang: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addUSSDMenu();
                case "2" -> showAllUSSDMenus();
                case "3" -> blockNumber();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Noto‘g‘ri tanlov!");
            }
        }
    }

    public static void blockNumber() {
        System.out.println("Bloklamoqchi bo'lgan raqamingizni kiriting");
        String number = scanner.nextLine();
        List<String> lines = Fileutil.readLines(NOMER_FILE);
        List<String> updated = new ArrayList<>();

        boolean found = false;
        for (String line : lines) {
            Number number1 = Number.fromFileString(line);
            if (number1.getNumber().equals(number)) {
                number1.setBlocked(true);
                number1.setBlockedTime(LocalDateTime.now());
                found = true;
            } else {
                updated.add(line);
            }

        }
        if (found) {
            Fileutil.overwriteFile(NOMER_FILE, updated);
            System.out.println("Raqam blokdan chiqarildi.");
        } else {
            System.out.println("Raqam topilmadi.");
        }

    }

    public static void addUSSDMenu() {
        System.out.print("USSD kodni kiriting (masalan: *109#): ");
        String code = scanner.nextLine();

        System.out.print("USSD mazmunini kiriting: ");
        String description = scanner.nextLine();

        USSD ussd = new USSD(code, description);
        Fileutil.writeLine(USSD_FILE, ussd.toFileString());

        System.out.println("✅ USSD menyu qo‘shildi.");
    }

    public static void showAllUSSDMenus() {
        List<String> lines = Fileutil.readLines(ManagerService.USSD_FILE);
        if (lines.isEmpty()) {
            System.out.println("Hech qanday USSD menyu mavjud emas.");
            return;
        }
        System.out.println("📱 USSD menyular:");
        for (String line : lines) {
            USSD ussd = USSD.fromFileString(line);
            System.out.println(ussd.getCode() + " - " + ussd.getDescription());
        }

    }
}

