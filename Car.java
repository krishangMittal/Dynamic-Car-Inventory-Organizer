public class Car implements CarPropertiesInterface {
    private String brand;
    private String model;
    private int year;
    private double price;
    private double mileage;

    public Car (String brand, String model, int year, double price, double mileage) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.price = price;
        this.mileage = mileage;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public int getYear() {
        return this.year;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public double getMileage() {
        return this.mileage;
    }

    // toString method to represent a Car object as a string
    @Override
    public String toString() {
        return "Car{" +
                "Brand: " + brand +
                ", Model: " + model +
                ", Year: " + year +
                ", Price: $" + price +
                ", Mileage: " + mileage + " miles" +
                '}';
    }


    @Override
    public int compareTo(Car otherCar){
        return Double.compare(this.getMileage(),otherCar.getMileage());
    }
}
