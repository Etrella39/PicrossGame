import javax.swing.*;
import java.awt.GraphicsEnvironment;

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });


        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        // 사용 가능한 모든 폰트 이름을 출력합니다.
        for (String fontName : fontNames) {
            System.out.println(fontName);
        }
    }

    private static void createAndShowGUI() {
        PicrossFrame frame = new PicrossFrame("Picross Game");
        frame.setVisible(true);
    };
}