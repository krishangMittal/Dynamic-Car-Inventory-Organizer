import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Frontend implements FrontendInterface {

    private Backend Backend;
    private BackendPlaceholder BackendPlaceholder;



    public static void main(String[] args) {
        Backend backend = new Backend("", new IterableMultiKeyRBT<Car>());
        Frontend frontend = new Frontend(backend);
        frontend.startMainCommandLoop();
    }


    public Frontend(Backend Backend) {
        this.Backend = Backend;
    }

    @Override
    public void startMainCommandLoop() {
        System.out.println("Welcome to this Demo");
        System.out.println();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Type:");
            System.out.println("[1] Show Main Menu");
            System.out.println("[2] Load File");
            System.out.println("[3] List Lowest Mileage Vehicles");
            System.out.println("[4] List Vehicles Above Threshold");
            System.out.println("[Q] to exit");
            String input = inputScanner.nextLine().toLowerCase();
            if (input.startsWith("q")) {
                exitCommand();
                break;
            } else if (input.startsWith("1")) {
                showMainMenu();
            } else if (input.startsWith("2")) {
                loadFile(inputScanner);
            } else if (input.startsWith("3")) {
                listLowestMileageVehicles();
            } else if (input.startsWith("4")) {
                System.out.print("Enter the mileage threshold: ");
                int threshold = inputScanner.nextInt();
                listVehiclesAboveThreshold(threshold);
                inputScanner.nextLine();
            } else {
                System.out.println("Unknown command: " + input);
            }
            System.out.println();
        }
        inputScanner.close();
    }

    @Override
    public void showMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("Press 2 to Load File");
        System.out.println("Press 3 to List Lowest Mileage Vehicles");
        System.out.println("Press 4 to List Vehicles Above Threshold");
        System.out.println("Press q to Exit");
    }

    @Override
    public void loadFile(Scanner scanner) {
        System.out.print("Enter the file path: ");
        String filePath = scanner.nextLine();

        boolean success = Backend.readFileData(filePath);
        if (success) {
            System.out.println("File loaded successfully.");
        } else {
            System.out.println("Failed to load the file.");
        }
    }

    @Override
    public List<String> listLowestMileageVehicles() {
        // Placeholder implementation: Get a list of cars with the lowest mileage from the inventory and display them.
        List<Car> cars = Backend.getCarsWithMinimumMileage();
        List<String> cars1 = new ArrayList<>();  // Initialize a new ArrayList for strings

        if (cars.isEmpty()) {
            System.out.println("No cars found with the lowest mileage.");
        } else {
            System.out.println("Cars with the lowest mileage:");
            for (Car car : cars) {
                String carInfo = car.toString();
                System.out.println(carInfo);
                cars1.add(carInfo);
            }
        }
        return cars1;
    }

    @Override
    public List<String> listVehiclesAboveThreshold(int threshold) {
        // Placeholder implementation: Get a list of cars with mileage above a threshold and display them.
        List<Car> cars = Backend.getCarsWithMileageAboveThreshold(threshold);
        List<String> cars1 = new ArrayList<>();  // Initialize a new ArrayList for strings

        if (cars.isEmpty()) {
            System.out.println("No cars found above the threshold of " + threshold);
        } else {
            System.out.println("Cars with mileage above " + threshold + ":");
            for (Car car : cars) {
                String carInfo = car.toString();
                System.out.println(carInfo);
                cars1.add(carInfo);
            }
        }
        return cars1;
    }


    @Override
    public void exitCommand() {
        System.out.println("Exiting the program.");
    }



}