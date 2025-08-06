package concurrency.UcellProject.model;

import java.time.LocalDateTime;

public class Number {
    private String code;
    private String number;
    private boolean isSold;
    private boolean isBlocked;
    private String ownerEmail;
    private LocalDateTime blockedTime;

    public Number(String code, String number) {
        this.code = code;
        this.number = number;
        this.isSold = false;
        this.isBlocked = false;
        this.ownerEmail = null;
        this.blockedTime = null;

    }

    public String getFullNumber() {
        return code + number;

    }

    public String toFileString() {
        return code + ";" + number + ";" + isSold + ";" + isBlocked + ";" +
                (ownerEmail != null ? ownerEmail : "null") + ";" +
                (blockedTime != null ? blockedTime.toString() : "null");
    }

    public static Number fromFileString(String line) {
        try {
            String[] parts = line.split(";");
            Number num = new Number(parts[0], parts[1]);
            num.setSold(Boolean.parseBoolean(parts[2]));
            num.setBlocked(Boolean.parseBoolean(parts[3]));
            num.setOwnerEmail("null".equals(parts[4]) ? null : parts[4]);
            num.setBlockedTime("null".equals(parts[5]) ? null : LocalDateTime.parse(parts[5]));
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    public void setBlockedTime(LocalDateTime blockedTime) {
        this.blockedTime = blockedTime;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public LocalDateTime getBlockedTime() {
        return blockedTime;
    }

    public String getCode() {
        return code;
    }

    public String getNumber() {
        return number;
    }

    public boolean isSold() {
        return isSold;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    @Override
    public String toString() {
        return "+998" + code + number + (isBlocked ? " [Bloklangan]" : " [Bloklanmagan]") + number + (isSold ? " [Sotilgan]" : " [Bo‘sh]");
    }
}
