package models;

import java.util.UUID;
import org.json.simple.JSONObject;

// Atributele obiectului ales
public class Car {

    private UUID id;
    private String model;
    private double maxSpeed;
    private boolean isElectric;

    public static Car fromExistingUUID(UUID id, String model, double maxSpeed, boolean isElectric) {
        return new Car(id, model, maxSpeed, isElectric);
    }

    protected Car(UUID id, String model, double maxSpeed, boolean isElectric) {
        this.id = id;
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.isElectric = isElectric;
    }

    public Car(String model, double maxSpeed, boolean isElectric) {
        id = UUID.randomUUID();
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.isElectric = isElectric;
    }

    public Car(JSONObject object) {
        this.model = (String) object.get("model");
        this.maxSpeed = (double) object.get("maxSpeed");
        this.isElectric = (boolean) object.get("isElectric");
        this.id = UUID.fromString((String) object.get("id"));
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setIsElectric(boolean isElectric) {
        this.isElectric = isElectric;
    }

    public String getModel() {
        return model;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    public boolean isElectric() {
        return isElectric;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(
                "Modelul masinii: %s\n"
                + "Viteza maxima: %s\n"
                + "Electrica: %s\n"
                + "Id: %s\n",
                model, maxSpeed, isElectric ? "Da" : "Nu", id.toString()
        );
    }
}