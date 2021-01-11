package services.interfaces;

import java.io.IOException;
import models.Car;
import models.ObjectFormType;

public interface ObjectFormInterface {

    void confirmPressed(ObjectFormType type, Car car) throws IOException, ClassNotFoundException;
}