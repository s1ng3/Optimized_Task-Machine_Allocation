import java.util.*;
public class Machine {
    private int id;
    private String name;
    private String capacity;
    private String cooldownTime;

    public Machine(int id, String name, String capacity, String cooldownTime) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.cooldownTime = cooldownTime;
    }

    @Override
    public String toString() {
        return id + ". " + name + " - Capacity: " + capacity + ", Cooldown time: " + cooldownTime;
    }

    public String getName() {
        return name;
    }
}
