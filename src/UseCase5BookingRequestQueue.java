// Use Case 5: Booking Request (First-Come-First-Served)
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
    public void displayReservation() {
        System.out.println("Guest: " + guestName + " requested " + roomType);
    }
}
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;
    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }
    public void addRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Booking request added to queue.");
    }
    public void displayQueue() {
        System.out.println("\n===== Booking Request Queue =====");
        if (requestQueue.isEmpty()) {
            System.out.println("No booking requests.");
            return;
        }
        for (Reservation r : requestQueue) {
            r.displayReservation();
        }
    }
}
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Suite Room");
        Reservation r3 = new Reservation("Charlie", "Double Room");
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);
        bookingQueue.displayQueue();
    }
}