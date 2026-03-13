//Use Case 10 : Booking Cancellation & Inventory Rollback
import java.util.*;

class Booking {
    String bookingId;
    String guestName;
    String roomType;
    String roomId;
    boolean active;

    Booking(String bookingId, String guestName, String roomType, String roomId) {
        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.active = true;
    }
}

public class UseCase10BookingCancellation {

    // Booking storage
    static Map<String, Booking> bookings = new HashMap<>();

    // Inventory for room types
    static Map<String, Integer> inventory = new HashMap<>();

    // Rollback stack
    static Stack<String> rollbackStack = new Stack<>();

    static Scanner sc = new Scanner(System.in);

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

        if (!inventory.containsKey(type) || inventory.get(type) == 0) {
            System.out.println("Room not available.");
            return;
        }

        String roomId = type.substring(0,1).toUpperCase() + (inventory.get(type));

        inventory.put(type, inventory.get(type) - 1);

        Booking b = new Booking(id, name, type, roomId);
        bookings.put(id, b);

        System.out.println("Booking Confirmed. Room ID: " + roomId);
    }

    // Cancel booking
    static void cancelBooking() {

        System.out.print("Enter Booking ID to cancel: ");
        String id = sc.next();

        if (!bookings.containsKey(id)) {
            System.out.println("Invalid booking ID.");
            return;
        }

        Booking b = bookings.get(id);

        if (!b.active) {
            System.out.println("Booking already cancelled.");
            return;
        }

        // Push to rollback stack
        rollbackStack.push(b.roomId);

        // Restore inventory
        inventory.put(b.roomType, inventory.get(b.roomType) + 1);

        // Mark booking inactive
        b.active = false;

        System.out.println("Booking cancelled successfully.");
        System.out.println("Room " + b.roomId + " returned to inventory.");
    }

    // Display bookings
    static void showBookings() {

        if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Booking b : bookings.values()) {
            System.out.println(
                    b.bookingId + " | " +
                            b.guestName + " | " +
                            b.roomType + " | " +
                            b.roomId + " | " +
                            (b.active ? "Active" : "Cancelled"));
        }
    }

    public static void main(String[] args) {

        // Initial inventory
        inventory.put("Single", 3);
        inventory.put("Double", 2);

        int choice;

        do {
            System.out.println("\n--- Book My Stay App ---");
            System.out.println("1. Create Booking");
            System.out.println("2. Cancel Booking");
            System.out.println("3. View Bookings");
            System.out.println("4. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createBooking();
                    break;

                case 2:
                    cancelBooking();
                    break;

                case 3:
                    showBookings();
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 4);
    }
}