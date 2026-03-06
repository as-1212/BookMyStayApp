// Use Case 6: Reservation Confirmation & Room Allocation
import java.util.*;
class Reservation {
    private String guestName;
    private String roomType;
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
    public String getGuestName() {
        return guestName;
    }
    public String getRoomType() {
        return roomType;
    }
}
class InventoryService {
    private HashMap<String, Integer> inventory;
    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
        inventory.put("Double Room", 1);
        inventory.put("Suite Room", 1);
    }
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }
    public void decrementRoom(String roomType) {
        if (inventory.containsKey(roomType)) {
            inventory.put(roomType, inventory.get(roomType) - 1);
        }
    }
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
class BookingService {
    private Queue<Reservation> requestQueue;
    private InventoryService inventory;
    private HashMap<String, Set<String>> allocatedRooms;
    public BookingService(Queue<Reservation> requestQueue, InventoryService inventory) {
        this.requestQueue = requestQueue;
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }
    public void processBookings() {
        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.poll();
            String roomType = reservation.getRoomType();
            System.out.println("\nProcessing booking for: " + reservation.getGuestName());
            if (inventory.getAvailability(roomType) > 0) {
                String roomId = roomType.substring(0, 2).toUpperCase() + (new Random().nextInt(900) + 100);
                allocatedRooms.putIfAbsent(roomType, new HashSet<>());
                while (allocatedRooms.get(roomType).contains(roomId)) {
                    roomId = roomType.substring(0, 2).toUpperCase() + (new Random().nextInt(900) + 100);
                }
                allocatedRooms.get(roomType).add(roomId);
                inventory.decrementRoom(roomType);
                System.out.println("Reservation Confirmed!");
                System.out.println("Guest: " + reservation.getGuestName());
                System.out.println("Room Type: " + roomType);
                System.out.println("Assigned Room ID: " + roomId);
            }
            else {
                System.out.println("Booking Failed: No rooms available for " + roomType);
            }
        }
    }
    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }
}
public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        InventoryService inventory = new InventoryService();
        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Alice", "Single Room"));
        requestQueue.add(new Reservation("Bob", "Double Room"));
        requestQueue.add(new Reservation("Charlie", "Single Room"));
        requestQueue.add(new Reservation("David", "Suite Room"));
        BookingService bookingService = new BookingService(requestQueue, inventory);
        bookingService.processBookings();
        bookingService.displayAllocatedRooms();
        inventory.displayInventory();
    }
}