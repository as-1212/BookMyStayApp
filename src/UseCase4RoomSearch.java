// Use Case 4: Room Search & Availability Check
import java.util.*;
abstract class Room {
    protected String type;
    protected double price;
    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public abstract void displayDetails();
}
class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 1500);
    }
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: ₹" + price);
        System.out.println("Beds: 1");
        System.out.println("-------------------------");
    }
}
class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 2500);
    }
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: ₹" + price);
        System.out.println("Beds: 2");
        System.out.println("-------------------------");
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 5000);
    }
    public void displayDetails() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: ₹" + price);
        System.out.println("Beds: 3");
        System.out.println("-------------------------");
    }
}
class RoomInventory {
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0); // unavailable example
        inventory.put("Suite Room", 2);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public Set<String> getRoomTypes() {
        return inventory.keySet();
    }
}
class RoomSearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomCatalog;
    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
        roomCatalog = new HashMap<>();
        roomCatalog.put("Single Room", new SingleRoom());
        roomCatalog.put("Double Room", new DoubleRoom());
        roomCatalog.put("Suite Room", new SuiteRoom());
    }
    public void searchAvailableRooms() {
        System.out.println("===== Available Rooms =====");
        for (String roomType : inventory.getRoomTypes()) {
            int available = inventory.getAvailability(roomType);
            if (available > 0) {
                Room room = roomCatalog.get(roomType);
                room.displayDetails();
                System.out.println("Available Rooms: " + available);
                System.out.println();
            }
        }
    }
}
public class UseCase4RoomSearch {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        RoomSearchService searchService = new RoomSearchService(inventory);
        searchService.searchAvailableRooms();
    }
}