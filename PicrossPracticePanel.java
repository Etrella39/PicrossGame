import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class PicrossPracticePanel extends JPanel {

    public PicrossPracticePanel() {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                if (x >= 380 && x <= 380 + 80 && y >= 360 && y <= 360 + 40) {
                    createPracticePanel();
                }
            }
        });
    }

    public void createPracticePanel() {
        Stage Level_0 = new Stage(0, 0);
        Level_0.createNewFrame();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.BLACK);

        int START_x = 20;
        int START_y = 40;
        int RECT_START_x = 50;
        int RECT_START_y = START_y + 50;

        int RECT_1 = START_y + 70;
        int RECT_SAI = 40;
        int RECT_WIDTH = 150;
        int RECT_HEIGHT = 30;

        g.setFont(new Font("Malgun Gothic", Font.BOLD, 17));
        g.drawString("기본 규칙", START_x, START_y);
        g.drawString("1. 하나의 숫자는 연속해서 채울 수 있는 칸의 수를 의미합니다.", START_x, START_y + 30);

        g.drawString("5", START_x + 10, RECT_1);
        g.drawString("4", START_x + 10, RECT_1 + RECT_SAI);
        g.drawString("4", START_x + 10, RECT_1 + RECT_SAI * 2);

        g.setColor(Color.GRAY);
        g.fillRect(RECT_START_x, RECT_START_y, RECT_WIDTH, RECT_HEIGHT);
        g.fillRect(RECT_START_x + RECT_HEIGHT, RECT_START_y + RECT_SAI, RECT_WIDTH - RECT_HEIGHT, RECT_HEIGHT);
        g.fillRect(RECT_START_x, RECT_START_y + RECT_SAI * 2, RECT_WIDTH - RECT_HEIGHT, RECT_HEIGHT);

        g.setColor(Color.BLACK);
        g.drawRect(RECT_START_x, RECT_START_y, RECT_WIDTH, RECT_HEIGHT);
        g.drawRect(RECT_START_x, RECT_START_y + RECT_SAI, RECT_WIDTH, RECT_HEIGHT);
        g.drawRect(RECT_START_x, RECT_START_y + RECT_SAI * 2, RECT_WIDTH, RECT_HEIGHT);
        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + RECT_HEIGHT * i, RECT_START_y, RECT_START_x + RECT_HEIGHT * i,
                    RECT_START_y + RECT_HEIGHT);
        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + RECT_HEIGHT * i, RECT_START_y + RECT_SAI, RECT_START_x + RECT_HEIGHT * i,
                    RECT_START_y + RECT_SAI + RECT_HEIGHT);
        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + RECT_HEIGHT * i, RECT_START_y + RECT_SAI * 2, RECT_START_x + RECT_HEIGHT * i,
                    RECT_START_y + RECT_SAI * 2 + RECT_HEIGHT);

        int START_2 = 250;
        int RECT_2 = START_2 + 70;
        int RECT_START_y_2 = START_2 + 50;

        g.drawString("2. 숫자가 여러개인 경우 숫자와 숫자 사이에는", START_x, START_2);
        g.drawString("반드시 한 칸 이상의 공백이 필요합니다.", START_x + 20, START_2 + 23);

        g.drawString("1 2", START_x + 5, RECT_2);
        g.drawString("1 2", START_x + 5, RECT_2 + RECT_SAI);
        g.drawString("1 2", START_x + 5, RECT_2 + RECT_SAI * 2);

        g.setColor(Color.GRAY);
        g.fillRect(RECT_START_x + 10, RECT_START_y_2, RECT_HEIGHT, RECT_HEIGHT);
        g.fillRect(RECT_START_x + 10 + RECT_HEIGHT * 3, RECT_START_y_2, RECT_HEIGHT * 2, RECT_HEIGHT);
        g.fillRect(RECT_START_x + 10, RECT_START_y_2 + RECT_SAI, RECT_HEIGHT, RECT_HEIGHT);
        g.fillRect(RECT_START_x + 10 + RECT_HEIGHT * 2, RECT_START_y_2 + RECT_SAI, RECT_HEIGHT * 2, RECT_HEIGHT);
        g.fillRect(RECT_START_x + 10 + RECT_HEIGHT, RECT_START_y_2 + RECT_SAI * 2, RECT_HEIGHT, RECT_HEIGHT);
        g.fillRect(RECT_START_x + 10 + RECT_HEIGHT * 3, RECT_START_y_2 + RECT_SAI * 2, RECT_HEIGHT * 2, RECT_HEIGHT);

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(380, 360, 80, 40);

        g.setColor(Color.BLACK);
        g.drawRect(RECT_START_x + 10, RECT_START_y_2, RECT_WIDTH, RECT_HEIGHT);
        g.drawRect(RECT_START_x + 10, RECT_START_y_2 + RECT_SAI, RECT_WIDTH, RECT_HEIGHT);
        g.drawRect(RECT_START_x + 10, RECT_START_y_2 + RECT_SAI * 2, RECT_WIDTH, RECT_HEIGHT);

        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + 10 + RECT_HEIGHT * i, RECT_START_y_2, RECT_START_x + 10 + RECT_HEIGHT * i,
                    RECT_START_y_2 + RECT_HEIGHT);
        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + 10 + RECT_HEIGHT * i, RECT_START_y_2 + RECT_SAI,
                    RECT_START_x + 10 + RECT_HEIGHT * i,
                    RECT_START_y_2 + RECT_SAI + RECT_HEIGHT);
        for (int i = 1; i < 5; i++)
            g.drawLine(RECT_START_x + 10 + RECT_HEIGHT * i, RECT_START_y_2 + RECT_SAI * 2,
                    RECT_START_x + 10 + RECT_HEIGHT * i,
                    RECT_START_y_2 + RECT_SAI * 2 + RECT_HEIGHT);

        g.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        g.drawRect(380, 360, 80, 40);
        g.drawString("NEXT", 380 + 22, 360 + 25);

    }

}
