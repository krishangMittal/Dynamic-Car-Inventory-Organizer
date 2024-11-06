//Name: Cole Wissinger
//Email: cwissinger@wisc.edu
//Group: c32
// notes to grader: I recieve no errors in IntelliJ but I am getting the here very confused as to why



import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Backend implements BackendInterface {
    IterableMultiKeySortedCollectionInterface<Car> thisTree;
    public Backend(String filePath,IterableMultiKeyRBT<Car> treeImplementation) {
        thisTree = treeImplementation;
        readFileData(filePath);
    }

    @Override
    public boolean readFileData(String filePath) {
        IterableMultiKeyRBT<Car> returnTree = new IterableMultiKeyRBT<>();

        File carsList = new File(filePath);
        try {
            Scanner scr = new Scanner(carsList);
            scr.nextLine();
            while (scr.hasNextLine()) {
                String indivCarData = scr.nextLine();
                String[] carData = indivCarData.split(",");
                double price = Double.parseDouble(carData[0]);
                String brand = carData[1];
                String model = carData[2];
                Integer year = Integer.parseInt(carData[3]);
                double mileage = Double.parseDouble(carData[5]);

                Car newInsertCar = new Car(brand, model, year, price, mileage);

                thisTree.insertSingleKey(newInsertCar);
            }

        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }


    @Override
    public List<Car> getCarsWithMinimumMileage() {
        LinkedList<Car> listOfMinCars = new LinkedList<>();
        Iterator<Car> iterator = thisTree.iterator();

        Car minimumCarKey = iterator.next();
        listOfMinCars.add(minimumCarKey);
        return listOfMinCars;
    }

    @Override
    public List<Car> getCarsWithMileageAboveThreshold(float mileageThreshold) {

        Car comparisonCar = new Car("na", "na", 0, 0, mileageThreshold);
        thisTree.setIterationStartPoint(comparisonCar);
        Iterator<Car> iterator = thisTree.iterator();
        LinkedList<Car> returnList = new LinkedList<>();



        while (iterator.hasNext()) returnList.add(iterator.next());
        return returnList;
    }


}

