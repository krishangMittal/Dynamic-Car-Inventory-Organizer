/**
 * Interface for a class the defines a car and provides access to the properties of the car
 */
public interface CarPropertiesInterface extends Comparable<Car>  {

  /**
   * Gets the brand of the car
   *
   * @return the brand of the car
   */
  public String getBrand();

  /**
   * Gets the model of the car
   *
   * @return the model of the car
   */
  public String getModel();

  /**
   * Gets the year of the car
   *
   * @return the year of the car
   */
  public int getYear();

  /**
   * Gets the price of the car
   *
   * @return the price of the car
   */
  public double getPrice();

  /**
   * Gets the mileage of the car
   *
   * @return the mileage of the car
   */
  public double getMileage();
  int compareTo(Car o);
  /**
   * Compares two cars based on their mileage
   *
   * @param otherCar The other car to compare to
   * @return <0 if this car's mileage is less than the other car, 0 is this car's mileage is the
   * same as the other car, >0 if this car's mileage is greater than the other car
   */

}
