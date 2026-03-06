//Use Case 2 : Basic Room Types & Static Availability
abstract class Room {
    private int beds;
    private double size;
    private double price;
    public Room(int beds, double size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }
    public int getBeds() {
        return beds;
    }
    public double getSize() {
        return size;
    }
    public double getPrice() {
        return price;
    }
    public abstract String getRoomType();
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq.ft");
        System.out.println("Price: ₹" + price);
        System.out.println("---------------------------");
    }
}
class SingleRoom extends Room {
    public SingleRoom() {
        super(1, 200, 1500);
    }
    public String getRoomType() {
        return "Single Room";
    }
}
class DoubleRoom extends Room {
    public DoubleRoom() {
        super(2, 350, 2500);
    }
    public String getRoomType() {
        return "Double Room";
    }
}
class SuiteRoom extends Room {
    public SuiteRoom() {
        super(3, 600, 5000);
    }
    public String getRoomType() {
        return "Suite Room";
    }
}
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        int singleRoomAvailable = 10;
        int doubleRoomAvailable = 7;
        int suiteRoomAvailable = 3;
        Room single = new SingleRoom();
        Room dbl = new DoubleRoom();
        Room suite = new SuiteRoom();
        System.out.println("===== Book My Stay - Room Availability =====\n");
        single.displayRoomDetails();
        System.out.println("Available: " + singleRoomAvailable + "\n");
        dbl.displayRoomDetails();
        System.out.println("Available: " + doubleRoomAvailable + "\n");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteRoomAvailable + "\n");
        System.out.println("Thank you for using Book My Stay!");
    }
}