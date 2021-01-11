package main;

import java.io.File;
import java.nio.file.Paths;
import services.SystemService;
import services.implementation.CarSaverLoaderService;
import services.implementation.MainMenuService;
import views.MainMenuForm;

//Se initializeaza toate serviciile
public class MainClass {

    public final static String ROOT_FOLDER = SystemService.get_path("masini.jar");
    public final static String JSON_FILE_NAME = "masini.json";
    public final static File MASINI_FILE = Paths.get(ROOT_FOLDER, JSON_FILE_NAME).toFile();
    public static MainClass ROOT_REF;
    public MainMenuService mainMenuService;
    public MainMenuForm mainMenu;
    CarSaverLoaderService carService;

    public MainClass() {
        carService = new CarSaverLoaderService();
        mainMenuService = new MainMenuService(carService);
        mainMenu = new MainMenuForm(mainMenuService);
        mainMenu.setLocationRelativeTo(null);
        mainMenu.setVisible(true);
        ROOT_REF=this;
    }
    
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();
    }
}