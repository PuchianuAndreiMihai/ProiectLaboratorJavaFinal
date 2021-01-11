package services.interfaces;

import java.io.File;
import java.io.IOException;
import models.Car;
import org.json.simple.JSONArray;

public interface CarSaverLoaderInterface {

    void addCarToDatabase(Car car, File file) throws IOException, ClassNotFoundException;

    JSONArray loadCars(File file) throws IOException, ClassNotFoundException;

    void editCarInDatabase(Car car, File file) throws IOException, ClassNotFoundException;

    void removeCarFromDatabase(Car car, File file) throws IOException, ClassNotFoundException;
}