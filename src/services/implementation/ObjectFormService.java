package services.implementation;

import java.io.IOException;
import main.MainClass;
import models.Car;
import models.ObjectFormType;
import services.interfaces.ObjectFormInterface;

public class ObjectFormService implements ObjectFormInterface {

    public CarSaverLoaderService carService;

    public ObjectFormService(CarSaverLoaderService carService) {
        this.carService = carService;
    }

    /**
     * Apelata fie cand adaugam un obiect nou sau cand il modificam
     * @param type
     * @param car
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @Override
    public void confirmPressed(ObjectFormType type, Car car) throws IOException, ClassNotFoundException {
        switch (type) {
            case ModifyCar:
                carService.editCarInDatabase(car, MainClass.MASINI_FILE);
                break;
            case NewCar:
                carService.addCarToDatabase(car, MainClass.MASINI_FILE);
                break;
        }
    }
}
