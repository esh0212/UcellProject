package concurrency.UcellProject.util;

import concurrency.UcellProject.model.Role;
import concurrency.UcellProject.model.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fileutil {
    public static void writeLine(String fileName, String line) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(line + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while((line = reader.readLine()) != null){
                lines.add(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return lines;
    }

    public static String toFileString(User user) {
        return user.getFullName() + ";" + user.getEmail() + ";" + user.getPassword() + ";" +
                user.getRole() + ";" + user.getCreatedAt();
    }

    public static User fromFileString(String line) {
        try {
            String[] parts = line.split(";");
            User user = new User(parts[0], parts[1], parts[2], Role.valueOf(parts[3]));
            user.setCreatedAt(LocalDateTime.parse(parts[4]));
            return user;
        } catch (Exception e) {
            return null;
        }
    }

    public static void overwriteFile(String fileName, List<String> lines) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String line : lines) {
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
