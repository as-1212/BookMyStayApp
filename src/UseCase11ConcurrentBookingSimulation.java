// Use Case 11 : Concurrent Booking Simulation
import java.util.*;

// Booking request class
class BookingRequest {
    String guestName;
    String roomType;

    BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared booking system
class BookingSystem {

    // Shared inventory
    private Map<String, Integer> inventory = new HashMap<>();

    // Shared queue
    private Queue<BookingRequest> bookingQueue = new LinkedList<>();

    public BookingSystem() {
        inventory.put("Single", 3);
        inventory.put("Double", 2);
    }

    // Add request to queue
    public synchronized void addRequest(BookingRequest request) {
        bookingQueue.add(request);
        System.out.println(request.guestName + " submitted booking request for " + request.roomType);
    }

    // Process request safely
    public synchronized void processRequest() {

        if (bookingQueue.isEmpty()) {
            return;
        }

        BookingRequest request = bookingQueue.poll();

        int roomsAvailable = inventory.getOrDefault(request.roomType, 0);

        if (roomsAvailable > 0) {

            inventory.put(request.roomType, roomsAvailable - 1);

            System.out.println(
                    Thread.currentThread().getName()
                            + " allocated " + request.roomType
                            + " room to " + request.guestName
            );

        } else {

            System.out.println(
                    Thread.currentThread().getName()
                            + ": No " + request.roomType
                            + " rooms available for " + request.guestName
            );
        }
    }

    // Show inventory
    public void showInventory() {
        System.out.println("\nRemaining Inventory:");
        for (String key : inventory.keySet()) {
            System.out.println(key + " Rooms: " + inventory.get(key));
        }
    }
}

// Thread class
class BookingProcessor extends Thread {

    BookingSystem system;

    BookingProcessor(BookingSystem system, String name) {
        super(name);
        this.system = system;
    }

    public void run() {

        for (int i = 0; i < 3; i++) {

            system.processRequest();

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        BookingSystem system = new BookingSystem();

        // Simulated guest requests
        system.addRequest(new BookingRequest("Alice", "Single"));
        system.addRequest(new BookingRequest("Bob", "Single"));
        system.addRequest(new BookingRequest("Charlie", "Double"));
        system.addRequest(new BookingRequest("David", "Single"));
        system.addRequest(new BookingRequest("Eva", "Double"));
        system.addRequest(new BookingRequest("Frank", "Single"));

        // Multiple threads simulating concurrent processing
        BookingProcessor t1 = new BookingProcessor(system, "Processor-1");
        BookingProcessor t2 = new BookingProcessor(system, "Processor-2");
        BookingProcessor t3 = new BookingProcessor(system, "Processor-3");

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        system.showInventory();
    }
}