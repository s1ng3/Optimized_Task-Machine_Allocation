import java.util.*;
public class Part {
    private int id;
    private String name;
    private int quantity;

    public Part(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - " + quantity + " items";
    }

    public int getId() {
        return id;
    }
}
