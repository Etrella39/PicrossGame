import javax.swing.*;
import java.awt.GraphicsEnvironment;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }
    private static void createAndShowGUI() {
        PicrossFrame frame = new PicrossFrame("Picross Game");
        frame.setVisible(true);
    };
}