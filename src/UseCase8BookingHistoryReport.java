//Use Case 8 : Booking History & Reporting
import java.util.ArrayList;
import java.util.List;

// Reservation class
class Reservation {
    private String bookingId;
    private String customerName;
    private String roomType;
    private int nights;

    public Reservation(String bookingId, String customerName, String roomType, int nights) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomType = roomType;
        this.nights = nights;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getNights() {
        return nights;
    }

    public void display() {
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Customer   : " + customerName);
        System.out.println("Room Type  : " + roomType);
        System.out.println("Nights     : " + nights);
        System.out.println("-----------------------------");
    }
}


// Booking History class
class BookingHistory {

    // List preserves insertion order
    private List<Reservation> reservations = new ArrayList<>();

    // Add confirmed reservation
    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    // Retrieve booking history
    public List<Reservation> getReservations() {
        return reservations;
    }

    // Display history
    public void showHistory() {
        System.out.println("\n---- Booking History ----");

        if (reservations.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        for (Reservation r : reservations) {
            r.display();
        }
    }
}


// Report Service class
class BookingReportService {

    public void generateSummary(List<Reservation> reservations) {

        System.out.println("\n==== Booking Report Summary ====");

        int totalBookings = reservations.size();
        int totalNights = 0;

        for (Reservation r : reservations) {
            totalNights += r.getNights();
        }

        System.out.println("Total Bookings : " + totalBookings);
        System.out.println("Total Nights   : " + totalNights);

        if (totalBookings > 0) {
            System.out.println("Average Nights : " + (totalNights / totalBookings));
        }

        System.out.println("================================");
    }
}


// Main class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();
        BookingReportService reportService = new BookingReportService();

        // Simulate confirmed bookings
        Reservation r1 = new Reservation("B101", "Alice", "Deluxe", 3);
        Reservation r2 = new Reservation("B102", "Bob", "Suite", 2);
        Reservation r3 = new Reservation("B103", "Charlie", "Standard", 4);

        // Add bookings to history
        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        // Admin views booking history
        history.showHistory();

        // Admin requests report
        reportService.generateSummary(history.getReservations());
    }
}