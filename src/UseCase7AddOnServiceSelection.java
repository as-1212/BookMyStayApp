//Use Case 7: Add-On Service Selection
import java.util.*;

class Service {
    private String serviceName;
    private double cost;

    public Service(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return serviceName + " (Rs." + cost + ")";
    }
}

class AddOnServiceManager {

    private Map<String, List<Service>> reservationServices;

    public AddOnServiceManager() {
        reservationServices = new HashMap<>();
    }

    public void addService(String reservationId, Service service) {

        reservationServices.putIfAbsent(reservationId, new ArrayList<>());

        reservationServices.get(reservationId).add(service);

        System.out.println(service.getServiceName() + " added to Reservation " + reservationId);
    }

    public void displayServices(String reservationId) {

        List<Service> services = reservationServices.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        System.out.println("Selected Services:");

        for (Service s : services) {
            System.out.println("- " + s);
        }
    }

    public double calculateAdditionalCost(String reservationId) {

        double total = 0;

        List<Service> services = reservationServices.get(reservationId);

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}

public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID: ");
        String reservationId = sc.nextLine();

        while (true) {

            System.out.println("\nChoose Add-On Service");
            System.out.println("1. Breakfast - Rs.500");
            System.out.println("2. Airport Pickup - Rs.1200");
            System.out.println("3. Extra Bed - Rs.800");
            System.out.println("4. Show Selected Services");
            System.out.println("5. Calculate Additional Cost");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    manager.addService(reservationId, new Service("Breakfast", 500));
                    break;

                case 2:
                    manager.addService(reservationId, new Service("Airport Pickup", 1200));
                    break;

                case 3:
                    manager.addService(reservationId, new Service("Extra Bed", 800));
                    break;

                case 4:
                    manager.displayServices(reservationId);
                    break;

                case 5:
                    double cost = manager.calculateAdditionalCost(reservationId);
                    System.out.println("Total Additional Cost: Rs." + cost);
                    break;

                case 6:
                    System.out.println("Exiting Add-On Service Module");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}