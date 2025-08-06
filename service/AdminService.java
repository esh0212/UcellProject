package concurrency.UcellProject.service;

import concurrency.UcellProject.util.Fileutil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminService {
    private static final String NUMBER_FILE = "data/numbers.txt";
    private static final String TARIFF_FILE = "data/tariffs.txt";
    public static void run(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Admin menyusi ---");
            System.out.println("1. Raqam qo‘shish");
            System.out.println("2. Tarif qo‘shish");
            System.out.println("0. Orqaga");

            System.out.print("Tanlang: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> {manageNumbers(scanner);}
                case "2" -> {manageTariffs(scanner);}
                case "0" -> {
                    return; // orqaga chiqish
                }
                default -> System.out.println("Noto‘g‘ri tanlov!");
            }
        }
    }    public static void manageNumbers(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Nomer qo'shish");
            System.out.println("2. Nomer o'chirish");
            System.out.println("3. Barcha nomerlar");
            System.out.println("0. Orqaga");
            System.out.print("Tanlang: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addNumber(scanner);
                case "2" -> deleteNumber(scanner);
                case "3" -> listNumbers();
                case "0" -> {
                    return;
                }
                default -> System.out.println("Noto‘g‘ri tanlov! ");
            }
        }
    }

    private static void addNumber(Scanner scanner) {
        System.out.print("Kod (93 yoki 94): ");
        String code = scanner.nextLine();
        System.out.print("Raqam: ");
        String number = scanner.nextLine();

        if (!code.equals("93") && !code.equals("94")) {
            System.out.println("Faqat 93 yoki 94 ruxsat etiladi!");
            return;
        }

        Fileutil.writeLine(NUMBER_FILE, code + number + ";available");
        System.out.println("Nomer qo‘shildi");
    }

    private static void deleteNumber(Scanner scanner) {
        System.out.print("O‘chirmoqchi bo‘lgan raqam: ");
        String numToDelete = scanner.nextLine();
        List<String> lines = Fileutil.readLines(NUMBER_FILE);
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (!line.startsWith(numToDelete)) {
                updated.add(line);
            }
        }
        Fileutil.overwriteFile(NUMBER_FILE, updated);
        System.out.println("Nomer o‘chirildi (agar mavjud bo‘lsa)");
    }

    private static void listNumbers() {
        List<String> lines = Fileutil.readLines(NUMBER_FILE);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public static void manageTariffs(Scanner scanner) {
        while (true) {
            System.out.println("\n1. Tarif qo‘shish");
            System.out.println("2. Tarif o‘chirish");
            System.out.println("3. Tariflar ro‘yxati");
            System.out.println("0. Orqaga");
            System.out.print("Tanlang: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addTariff(scanner);
                case "2" -> deleteTariff(scanner);
                case "3" -> listTariffs();
                case "0" -> {
                    return;
                }
                default -> System.out.println(" Noto‘g‘ri tanlov!");
            }
        }
    }

    private static void addTariff(Scanner scanner) {
        System.out.print("Tarif nomi: ");
        String name = scanner.nextLine();
        System.out.print("Narxi: ");
        String price = scanner.nextLine();
        System.out.print("Tavsifi: ");
        String description = scanner.nextLine();

        Fileutil.writeLine(TARIFF_FILE, name + ";" + price + ";" + description);
        System.out.println(" Tarif qo‘shildi");
    }

    private static void deleteTariff(Scanner scanner) {
        System.out.print("O‘chirmoqchi bo‘lgan tarif nomi: ");
        String tariffToDelete = scanner.nextLine();
        List<String> lines = Fileutil.readLines(TARIFF_FILE);
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (!line.startsWith(tariffToDelete + ";")) {
                updated.add(line);
            }
        }
        Fileutil.overwriteFile(TARIFF_FILE, updated);
        System.out.println("✅ Tarif o‘chirildi (agar mavjud bo‘lsa)");
    }

    private static void listTariffs() {
        List<String> lines = Fileutil.readLines(TARIFF_FILE);
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
