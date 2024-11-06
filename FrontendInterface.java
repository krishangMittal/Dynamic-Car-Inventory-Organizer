import java.util.List;
import java.util.Scanner;

public interface FrontendInterface {
    /**
     * Constructor to initialize the frontend with a reference to the backend and a Scanner instance.
     *
     * @param backend  The backend of the app.
     * @param scanner  The Scanner instance for reading user input.
     */
     //FrontendInterface(BackendInterface backend, Scanner scanner);

    /**
     * Starts the main command loop for the user.
     */
    public void startMainCommandLoop();

    /**
     * Displays the main menu to the user and handles user input for the main menu.
     */
    public void showMainMenu();

    /**
     * Command to specify and load a data file
     */
    public void loadFile(Scanner c);

    /**
     * Command to list the vehicles with the lowest mileage
     *
     * @return
     */
    public List<String> listLowestMileageVehicles();

    /**
     * Command to list vehicles with mileage at or above a specified threshold
     *
     * @return
     */
    public List<String> listVehiclesAboveThreshold(int threshold);

    /**
     * Command to exit the app
     */
    public void exitCommand();
}


