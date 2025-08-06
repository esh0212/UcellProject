package concurrency.UcellProject.service;

import concurrency.UcellProject.model.Number;
import concurrency.UcellProject.model.Tariff;
import concurrency.UcellProject.model.USSD;
import concurrency.UcellProject.model.User;
import concurrency.UcellProject.util.Fileutil;

import java.util.List;
import java.util.Scanner;

public class UserService {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String NOMER_FILE = "data/numbers.txt";
    private static final String TARIF_FILE = "data/tariffs.txt";
    private static final String USSD_FILE = "data/ussd.txt";
    public static void run(User user, Scanner scanner) {
        while (true) {
            System.out.println("\n--- Foydalanuvchi Paneli ---");
            System.out.println("1. Raqam xarid qilish");
            System.out.println("2. Tariflarni ko‘rish");
            System.out.println("3. USSD menyudan foydalanish");
            System.out.println("0. Orqaga");
            System.out.print("Tanlang: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> buyNomer(String.valueOf(scanner));
                case "2" -> showAllTarifs();
                case "3" -> useUSSDCode();
                case "0" -> { return; }
                default -> System.out.println("Noto‘g‘ri tanlov!");
            }
        }
    }
    public static void buyNomer(String userEmail) {
        System.out.print("Yangi raqam kiriting (masalan: +998901234567): ");
        String number = scanner.nextLine();

        Number nomer = new Number (number, userEmail);
        Fileutil.writeLine(NOMER_FILE, nomer.toFileString());

        System.out.println("Siz raqamni xarid qildingiz: " + number);
    }

    public static void showAllTarifs() {
        List<String> lines = Fileutil.readLines(TARIF_FILE);
        if (lines.isEmpty()) {
            System.out.println("Tariflar mavjud emas.");
            return;
        }

        System.out.println("📶 Mavjud tariflar:");
        for (String line : lines) {
            Tariff tarif = Tariff.fromFileString(line);
            System.out.println("📌 " + tarif.getName() + " - " + tarif.getDescription());
        }
    }

    public static void useUSSDCode() {
        System.out.print("USSD kodni kiriting (masalan: *100#): ");
        String code = scanner.nextLine();

        List<String> lines = Fileutil.readLines(USSD_FILE);
        for (String line : lines) {
            USSD ussd = USSD.fromFileString(line);
            if (ussd.getCode().equals(code)) {
                System.out.println("✅ USSD javobi: " + ussd.getDescription());
                return;
            }
        }

        System.out.println("❌ Bunday USSD kod topilmadi.");
    }
}
