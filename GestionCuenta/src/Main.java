import gui.FormularioCuenta;
import operaciones.OperacionesBanco;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> new FormularioCuenta(new OperacionesBanco()).setVisible(true));
    }
}
