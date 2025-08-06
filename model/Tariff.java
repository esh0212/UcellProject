package concurrency.UcellProject.model;

public class Tariff {
    private String name;
    private int price;
    private String description;

    public Tariff(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String toFileString() {
        return name + "," + price + "," + description;

    }

    public static Tariff fromFileString(String line) {
        try {
            String[] parts = line.split(",");
            return new Tariff(parts[0], Integer.parseInt(parts[1]), parts[2]);
        } catch (Exception e) {
            return null;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return name + " - " + price + " so‘m\n" + description;
    }

}
