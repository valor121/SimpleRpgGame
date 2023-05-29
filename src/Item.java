import java.util.Objects;

public class Item {
    private String name;
    private final int price;
    private Objects funktion;
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
}

