// Use Case 3: Centralized Room Inventory Management
import java.util.HashMap;
import java.util.Map;
class RoomInventory {
    private HashMap<String, Integer> inventory;
    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 7);
        inventory.put("Suite Room", 3);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void updateAvailability(String roomType, int count) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            inventory.put(roomType, current + count);
        }
    }
    public void displayInventory() {
        System.out.println("===== Current Room Inventory =====");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
        System.out.println("----------------------------------");
    }
}
public class UseCase3InventorySetup {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.displayInventory();
        System.out.println("\nBooking 1 Single Room...\n");
        inventory.updateAvailability("Single Room", -1);
        inventory.displayInventory();
    }
}