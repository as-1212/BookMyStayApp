//UseCase9ErrorHandlingValidation
import java.util.HashMap;
import java.util.Map;

// Custom Exception for invalid bookings
class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

// Reservation class
class Reservation {
    private String guestName;
    private String roomType;
    private int nights;

    public Reservation(String guestName, String roomType, int nights) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public void display() {
        System.out.println("Guest Name : " + guestName);
        System.out.println("Room Type  : " + roomType);
        System.out.println("Nights     : " + nights);
        System.out.println("----------------------------");
    }
}

// Booking system with validation
class BookingSystem {

    private Map<String, Integer> roomInventory = new HashMap<>();

    public BookingSystem() {
        roomInventory.put("Standard", 2);
        roomInventory.put("Deluxe", 2);
        roomInventory.put("Suite", 1);
    }

    // Booking method with validation
    public Reservation bookRoom(String guestName, String roomType, int nights)
            throws InvalidBookingException {

        // Validate guest name
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        // Validate nights
        if (nights <= 0) {
            throw new InvalidBookingException("Number of nights must be greater than zero.");
        }

        // Validate room type
        if (!roomInventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }

        // Check room availability
        int available = roomInventory.get(roomType);
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for type: " + roomType);
        }

        // Update inventory safely
        roomInventory.put(roomType, available - 1);

        return new Reservation(guestName, roomType, nights);
    }

    public void showInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (String type : roomInventory.keySet()) {
            System.out.println(type + " : " + roomInventory.get(type));
        }
    }
}

// Main class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        system.showInventory();

        try {
            Reservation r1 = system.bookRoom("Alice", "Deluxe", 3);
            System.out.println("\nBooking Successful:");
            r1.display();

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        try {
            // Invalid booking example
            Reservation r2 = system.bookRoom("", "Premium", -1);

            System.out.println("\nBooking Successful:");
            r2.display();

        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        system.showInventory();
    }
}