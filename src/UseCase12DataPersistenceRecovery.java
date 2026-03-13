// Use Case 12 : Data Persistence & System Recovery
import java.io.*;
import java.util.*;

// Booking class
class Booking implements Serializable {
    String bookingId;
    String guestName;
    String roomType;

    Booking(String bookingId, String guestName, String roomType) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// System state class
class SystemState implements Serializable {
    Map<String, Booking> bookings;
    Map<String, Integer> inventory;

    SystemState(Map<String, Booking> bookings, Map<String, Integer> inventory) {
        this.bookings = bookings;
        this.inventory = inventory;
    }
}

public class UseCase12DataPersistenceRecovery {

    static Map<String, Booking> bookings = new HashMap<>();
    static Map<String, Integer> inventory = new HashMap<>();

    static final String FILE_NAME = "hotel_state.ser";
    static Scanner sc = new Scanner(System.in);

    // Save system state
    static void saveState() {
        try {
            ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(FILE_NAME));

            SystemState state = new SystemState(bookings, inventory);

            out.writeObject(state);
            out.close();

            System.out.println("System state saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving system state.");
        }
    }

    // Load system state
    static void loadState() {

        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(FILE_NAME));

            SystemState state = (SystemState) in.readObject();

            bookings = state.bookings;
            inventory = state.inventory;

            in.close();

            System.out.println("System state restored from file.");

        } catch (Exception e) {

            System.out.println("No previous state found. Starting fresh.");

            inventory.put("Single", 3);
            inventory.put("Double", 2);
        }
    }

    // Create booking
    static void createBooking() {

        System.out.print("Enter Booking ID: ");
        String id = sc.next();

        if (bookings.containsKey(id)) {
            System.out.println("Booking already exists.");
            return;
        }

        System.out.print("Enter Guest Name: ");
        String name = sc.next();

        System.out.print("Enter Room Type (Single/Double): ");
        String type = sc.next();

        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {

            inventory.put(type, available - 1);

            Booking b = new Booking(id, name, type);
            bookings.put(id, b);

            System.out.println("Booking successful.");

        } else {
            System.out.println("No rooms available.");
        }
    }

    // Show bookings
    static void showBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking b : bookings.values()) {
            System.out.println(
                    b.bookingId + " | " +
                            b.guestName + " | " +
                            b.roomType
            );
        }
    }

    // Show inventory
    static void showInventory() {

        System.out.println("Current Inventory:");

        for (String key : inventory.keySet()) {
            System.out.println(key + " Rooms: " + inventory.get(key));
        }
    }

    public static void main(String[] args) {

        loadState();

        int choice;

        do {

            System.out.println("\n--- Book My Stay App ---");
            System.out.println("1. Create Booking");
            System.out.println("2. View Bookings");
            System.out.println("3. View Inventory");
            System.out.println("4. Save & Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createBooking();
                    break;

                case 2:
                    showBookings();
                    break;

                case 3:
                    showInventory();
                    break;

                case 4:
                    saveState();
                    System.out.println("System shutting down...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }
}