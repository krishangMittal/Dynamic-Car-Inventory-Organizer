// --== CS400 Fall 2023 File Header Information ==--
// Name: Krishang Mittal
// Email: kmmittal@wisc.edu
// Group: C32
// TA: Anvay Grover
// Lecturer: Florian
// Notes to Grader: <optional extra notes>

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ROLETests {

    private static Frontend frontend;
    @BeforeAll
    public static void setUp() {
        // Initialize the Frontend with a BackendPlaceholder for testing
        frontend = new Frontend(new BackendPlaceholder());
    }

    /**
     * Test the startMainCommandLoop method.
     */
    @Test
    public void testStartMainCommandLoop() {
        // Create a new TextUITester object for each test, and
        // pass the text that you'd like to simulate a user typing as the only argument.
        TextUITester tester = new TextUITester("q\n");
        frontend.startMainCommandLoop();
        String output = tester.checkOutput();
        String expectedOutput = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";
        assertEquals( expectedOutput,output);
    }

    /**
     * Test the showMainMenu method.
     */
    @Test
    public void testShowMainMenu() {
        TextUITester tester = new TextUITester("1\nq\n");
        frontend.startMainCommandLoop();
        String output = tester.checkOutput();
        String expectedOutput = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Main Menu:\n" +
                "Press 2 to Load File\n" +
                "Press 3 to List Lowest Mileage Vehicles\n" +
                "Press 4 to List Vehicles Above Threshold\n" +
                "Press q to Exit\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";
        assertEquals( expectedOutput,output);
    }

    /**
     * Test the loadFile method.
     */
    @Test
    public void testLoadFile() {
        // Create a new TextUITester object for the test and provide the simulated user input.
        TextUITester tester = new TextUITester("2\nfilename.txt\nq\n");

        frontend.startMainCommandLoop();
        String expectedValue = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Enter the file path: File loaded successfully.\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";
        // Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        assertEquals( expectedValue,output);
    }

    /**
     * Test the listLowestMileageVehicles method.
     */
    @Test
    public void testListLowestMileageVehicles() {
        // Create a new TextUITester object for the test and provide the simulated user input.
        TextUITester tester = new TextUITester("3\nq\n");
        frontend.startMainCommandLoop();
        String expectedValue = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Cars with the lowest mileage:\n" +
                "Car{Brand: chevrolet, Model: 1500, Year: 2018, Price: $27700.0, Mileage: 6654.0 miles}\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";
        // Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        assertEquals(expectedValue,output);
    }

    /**
     * Test the listVehiclesAboveThreshold method.
     */
    @Test
    public void testListVehiclesAboveThreshold() {
        // Create a new TextUITester object for the test and provide the simulated user input.
        TextUITester tester = new TextUITester("4\n50000\nq\n");

        frontend.startMainCommandLoop();
        String expectedValue = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Enter the mileage threshold: Cars with mileage above 50000:\n" +
                "Car{Brand: ford, Model: se, Year: 2011, Price: $2899.0, Mileage: 190552.0 miles}\n" +
                "Car{Brand: ford, Model: door, Year: 2014, Price: $25000.0, Mileage: 64146.0 miles}\n" +
                "Car{Brand: infiniti, Model: q70, Year: 2016, Price: $18500.0, Mileage: 71967.0 miles}\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";

        // Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        assertEquals( expectedValue,output);
    }

    /**
     * Test if the menu is able to handle unknown Commands.
     */
    @Test
    public void testUnknownCommand() {
        // Create a new TextUITester object for the test and provide the simulated user input.
        TextUITester tester = new TextUITester("rfwef\nq\n");

        frontend.startMainCommandLoop();
        String expectedValue = "Welcome to this Demo\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Unknown command: rfwef\n" +
                "\n" +
                "Type:\n" +
                "[1] Show Main Menu\n" +
                "[2] Load File\n" +
                "[3] List Lowest Mileage Vehicles\n" +
                "[4] List Vehicles Above Threshold\n" +
                "[Q] to exit\n" +
                "Exiting the program.\n";

        // Check whether the output printed to System.out matches your expectations.
        String output = tester.checkOutput();
        assertEquals( expectedValue,output);
    }

    /**
     * Test integration between the Frontend and Backend for listing vehicles above a threshold.
     */
    @Test
    public void testIntegrationListVehiclesAboveThreshold() {
        Backend backend = new Backend("C:\\Users\\Krishang\\IdeaProjects\\IndividualFrontendInterface\\src\\cars.csv", new IterableMultiKeyRBT<Car>());
        Frontend frontend = new Frontend(backend);

        // Call the method you want to test (getCarsWithMileageAboveThreshold)
        List<Car> expectedCars = backend.getCarsWithMileageAboveThreshold(10000);
        List<String> expectedValue = expectedCars.stream()
                .map(Car::toString)
                .collect(Collectors.toList());

        List<String> actualValue = frontend.listVehiclesAboveThreshold(10000);

        // Check whether the output matches your expectations.
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test integration between the Frontend and Backend for listing vehicles with the lowest mileage.
     */
    @Test
    public void testIntegrationListLowestMileageVehicles() {
        Backend backend = new Backend("C:\\Users\\Krishang\\IdeaProjects\\IndividualFrontendInterface\\src\\cars.csv", new IterableMultiKeyRBT<Car>());
        Frontend frontend = new Frontend(backend);

        List<String> expectedValue = backend.getCarsWithMinimumMileage().stream().map(Car::toString)
                .collect(Collectors.toList());
        List<String> actualValue = frontend.listLowestMileageVehicles();

        // Check whether the output matches your expectations.
        assertEquals(expectedValue, actualValue);
    }


}