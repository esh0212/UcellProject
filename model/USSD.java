package concurrency.UcellProject.model;

import java.io.Serializable;

public class USSD implements Serializable {
    private String code;
    private String description;

    public USSD(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Faylga yozish uchun string format
    public String toFileString() {
        return code + ";" + description;
    }

    // Fayldan o'qib USSD obyektiga aylantirish
    public static USSD fromFileString(String line) {
        String[] parts = line.split(";");
        return new USSD(parts[0], parts[1]);
    }

    @Override
    public String toString() {
        return code + " - " + description;
    }

}
