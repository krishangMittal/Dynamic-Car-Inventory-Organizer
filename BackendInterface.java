import java.util.List;

public interface BackendInterface {

    /**
     * Used to read data from a car file, and store it as a Red Black tree.
     *
     * @param filePath direct path to the file
     * @return true if the data was correctly read and stored, false if not
     */
    public boolean readFileData(String filePath);

    /**
     * Used to get the list of cars with minimum mileage, by finding all cars who share the lowest
     * mileage value.
     *
     * @return cars with the lowest mileage value.
     */
    public List<Car> getCarsWithMinimumMileage();

    /**
     * Used to get the list of cars with mileage above a specific threshold
     *
     * @param mileageThreshold Minimum mileage car should have.
     * @return the list of cars with mileage above minimum threshold
     */
    public List<Car> getCarsWithMileageAboveThreshold(float mileageThreshold);
}

