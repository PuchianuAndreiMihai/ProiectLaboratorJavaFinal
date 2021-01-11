package services.implementation;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.MainClass;
import models.ObjectFormType;
import services.interfaces.MainMenuInterface;
import views.ListObjectsForm;
import views.MainMenuForm;
import views.ObjectForm;

public class MainMenuService implements MainMenuInterface {

    final ObjectFormService objectFormService;
    final CarSaverLoaderService carService;
    ListObjectsForm listObjectsForm;

    public MainMenuService(CarSaverLoaderService carService) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenuForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        objectFormService = new ObjectFormService(carService);
        this.carService = carService;
        listObjectsForm = new ListObjectsForm(objectFormService, carService);
    }

    /**
     * Apelat cand utilizatorul da click pe "Adauga obiect"
     */
    @Override
    public void addObject() {
        ObjectForm objectForm = new ObjectForm(ObjectFormType.NewCar, objectFormService);
        objectForm.setLocationRelativeTo(null);
        objectForm.setVisible(true);
    }

    @Override
    public void listObjects() {
        try {

            listObjectsForm.update(carService.loadCars(MainClass.MASINI_FILE));
            listObjectsForm.setVisible(true);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(MainMenuService.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Nu am putut incarca baza de date");
            MainClass.ROOT_REF.mainMenu.setEnabled(true);
            MainClass.ROOT_REF.mainMenu.toFront();
        }
    }

    @Override
    public void exitProgram() {
        int choice = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa iesi din program?");
        if (choice == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
}
