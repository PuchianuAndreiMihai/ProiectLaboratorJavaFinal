package services;

import java.net.URISyntaxException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

//Clasa pentru functionalitati comune (Extragere de path)
public class SystemService {

    public static String getPathToJar() {
        try {
            String x = SystemService.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            StringBuilder b = new StringBuilder(x);
            b.delete(0, 1);
            return b.toString();
        } catch (URISyntaxException ex) {
            Logger.getLogger(SystemService.class.getName()).log(Level.SEVERE, null, ex);
            return "Error";
        }
    }

    public static String get_path(String finalName) {
        String full = getPathToJar();
        StringTokenizer b = new StringTokenizer(full, "/\\");
        String x = "";
        while (b.hasMoreElements()) {
            String y = b.nextToken();
            if (!(y.toLowerCase().equals(finalName.toLowerCase())) && !(y.toLowerCase().equals("store"))) {
                x += y + "\\";
            }
        }
        return x;
    }
}