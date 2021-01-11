package services.implementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;
import main.MainClass;
import models.Car;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import services.interfaces.CarSaverLoaderInterface;

//Service care se ocupa cu scrierea si citirea din fisier
public class CarSaverLoaderService implements CarSaverLoaderInterface {

    /**
     * Citeste un fisier JSON si returneaza array-ul JSON asociat fisierului
     * @param file
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    private JSONArray readJsonArray(File file) throws IOException, ClassNotFoundException {
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            JSONParser parser = new JSONParser();
            String jsonString = "";
            String line;
            while ((line = input.readLine()) != null) {
                jsonString += line;
            }
            JSONArray cars;
            try {
                cars = (JSONArray) parser.parse(jsonString);
            } catch (ParseException ex) {
                cars = new JSONArray();
            }
            return cars;
        }
    }
    
    private void exportJsonArray(JSONArray array, File file) throws IOException, ClassNotFoundException {
        try (FileWriter output = new FileWriter(file)) {
            output.write(array.toJSONString());
        }
    }

    @Override
    public void addCarToDatabase(Car car, File file) throws IOException, ClassNotFoundException {
        JSONObject carJson = new JSONObject();
        carJson.put("model", car.getModel());
        carJson.put("maxSpeed", car.getMaxSpeed());
        carJson.put("isElectric", car.isElectric());
        carJson.put("id", car.getId().toString());
        JSONArray cars = readJsonArray(file);
        cars.add(carJson);
        exportJsonArray(cars, file);
    }

    @Override
    public JSONArray loadCars(File file) throws IOException, ClassNotFoundException {
        return readJsonArray(file);
    }

    @Override
    public void editCarInDatabase(Car car, File file) throws IOException, ClassNotFoundException {
        JSONArray cars = readJsonArray(file);
        boolean found = false;
        for (int i = 0; i < cars.size(); i++) {
            JSONObject listCar = ((JSONObject) cars.get(i));
            if (car.getId().equals(UUID.fromString((String) listCar.get("id")))) {
                listCar.put("model", car.getModel());
                listCar.put("maxSpeed", car.getMaxSpeed());
                listCar.put("isElectric", car.isElectric());
                found = true;
            }
        }
        if (!found) {
            throw new ClassNotFoundException("Nu am gasit masina in baza de date");
        }
        MainClass.ROOT_REF.mainMenuService.listObjectsForm.update(cars);
        exportJsonArray(cars, file);
    }

    @Override
    public void removeCarFromDatabase(Car car, File file) throws IOException, ClassNotFoundException {
        JSONArray cars = readJsonArray(file);
        boolean found = false;
        for (int i = cars.size() - 1; i >= 0; i--) {
            JSONObject listCar = (JSONObject) cars.get(i);
            if (UUID.fromString((String) listCar.get("id")).equals(car.getId())) {
                cars.remove(i);
                found = true;
            }
        }
        if (!found) {
            throw new ClassNotFoundException("Nu am gasit masina pe care vrei sa o stergi");
        }
        MainClass.ROOT_REF.mainMenuService.listObjectsForm.update(cars);
        exportJsonArray(cars, file);
    }
}