import java.lang.management.MonitorInfo;
import java.util.ArrayList;
import java.util.List;

public class BackendPlaceholder extends Backend implements BackendInterface {
    private List<Car> carData;

    public BackendPlaceholder() {
        super("string", new IterableMultiKeyRBT<Car>());
        // Initialize car data with hardcoded values
        carData = new ArrayList<>();
        carData.add(new Car("ford", "se", 2011, 2899, 190552.0));
        carData.add(new Car("dodge", "mpv", 2018, 5350, 39590.0));
        carData.add(new Car("ford", "door", 2014, 25000, 64146.0));
        carData.add(new Car("chevrolet", "1500", 2018, 27700, 6654.0));
        carData.add(new Car("dodge", "mpv", 2018, 5700, 45561.0));
        carData.add(new Car("infiniti", "q70", 2016, 18500, 71967.0));
    }

    @Override
    public boolean readFileData(String filePath) {
        // Implement the method to read data from a file if needed.
        return true; // Placeholder return value.
    }

    /**
     * Used to get the list of cars with minimum mileage, by finding all cars who share the lowest
     * mileage value.
     *
     * @return cars with the lowest mileage value.
     */
    @Override
    public List<Car> getCarsWithMinimumMileage() {
        List<Car> result = new ArrayList<>();

        double minMileage = Double.MAX_VALUE; // Set to a high initial value

        for (Car car : carData) {
            double currentMileage = car.getMileage();

            if (currentMileage < minMileage) {
                minMileage = currentMileage; // Update the minimum mileage value
                result.clear(); // Clear the previous result list
                result.add(car); // Add the current car with the new minimum mileage
            } else if (currentMileage == minMileage) {
                result.add(car); // Add another car with the same minimum mileage
            }
        }

        return result;
    }


    /**
     * Used to get the list of cars with mileage above a specific threshold
     *
     * @param mileageThreshold Minimum mileage car should have.
     * @return the list of cars with mileage above minimum threshold
     */
    @Override
    public List<Car> getCarsWithMileageAboveThreshold(float mileageThreshold) {
        List<Car> result = new ArrayList<>();
        for (Car car : carData) {
            if (car.getMileage() > mileageThreshold) {
                result.add(car);
            }
        }
        return result;
    }


}