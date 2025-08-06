package concurrency.UcellProject.service;

import concurrency.UcellProject.model.USSD;
import concurrency.UcellProject.util.Fileutil;

import java.util.List;
import java.util.Scanner;

public class USSDService {
        private static final Scanner scanner = new Scanner(System.in);
        private static final String USSD_FILE = "ussd.txt";

        public void addUSSDMenu() {
            System.out.print("USSD kodni kiriting (masalan: *100#): ");
            String code = scanner.nextLine();

            System.out.print("USSD tavsifini kiriting: ");
            String description = scanner.nextLine();

            USSD ussd = new USSD(code, description);
            Fileutil.writeLine(USSD_FILE, ussd.toFileString());

            System.out.println("USSD menyu qo‘shildi.");
        }

        public void showAllUSSDMenus() {
            List<String> lines = Fileutil.readLines(USSD_FILE);
            if (lines.isEmpty()) {
                System.out.println("Hech qanday USSD menyu mavjud emas.");
                return;
            }

            System.out.println("📱 Mavjud USSD menyular:");
            for (String line : lines) {
                USSD ussd = USSD.fromFileString(line);
                System.out.println("• " + ussd.getCode() + " - " + ussd.getDescription());
            }
        }
}
