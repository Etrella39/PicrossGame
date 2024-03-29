import javax.swing.*;
import java.awt.*;

public class PicrossFrame extends JFrame {

    private JButton Begginner_1;
    private JButton Begginner_2;
    private JButton Begginner_3;
    private JButton Begginner_4;
    private JButton Intermediate_1;
    private JButton Intermediate_2;
    private JButton Intermediate_3;
    private JButton Intermediate_4;
    private JButton Advanced_1;
    private JButton Advanced_2;
    private JButton Advanced_3;
    private JButton Advanced_4;
    private JButton High_1;
    private JButton High_2;

    private JButton START_0;

    public PicrossFrame() {
    }

    public PicrossFrame(String s) {

        setTitle(s);
        setDefaultCloseOperation(PicrossFrame.EXIT_ON_CLOSE);
        setSize(400, 500);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // 레이아웃 매니저를 BoxLayout으로 변경

        JLabel title = new JLabel("노노그램");
        JLabel title2 = new JLabel("네모네모로직");
        title2.setForeground(new Color(147, 84, 237));

        title.setFont(new Font("Malgun Gothic", Font.BOLD, 40));
        title.setAlignmentX(Component.CENTER_ALIGNMENT); // 라벨을 가운데 정렬

        title2.setFont(new Font("Malgun Gothic", Font.PLAIN, 16));
        title2.setAlignmentX(Component.CENTER_ALIGNMENT); // 라벨을 가운데 정렬

        panel.add(title);
        panel.add(title2);

        Box b;
        Box Help;
        Box Bb1, Bb2, Bb3, Bb4;
        Bb1 = Box.createHorizontalBox();
        Bb2 = Box.createHorizontalBox();
        Bb3 = Box.createHorizontalBox();
        Bb4 = Box.createHorizontalBox();
        Help = Box.createHorizontalBox();
        b = Box.createVerticalBox();

        START_0 = new JButton("규칙");
        START_0.addActionListener((evt) -> WhatStage(0, 0));
        Help.add(START_0);

        Begginner_1 = new JButton("1");
        Begginner_1.addActionListener((evt) -> WhatStage(1, 5));
        Begginner_2 = new JButton("2");
        Begginner_2.addActionListener((evt) -> WhatStage(2, 5));
        Begginner_3 = new JButton("3");
        Begginner_3.addActionListener((evt) -> WhatStage(3, 5));
        Begginner_4 = new JButton("4");
        Begginner_4.addActionListener((evt) -> WhatStage(4, 5));
        Intermediate_1 = new JButton("1");
        Intermediate_1.addActionListener((evt) -> WhatStage(1, 10));
        Intermediate_2 = new JButton("2");
        Intermediate_2.addActionListener((evt) -> WhatStage(2, 10));
        Intermediate_3 = new JButton("3");
        Intermediate_3.addActionListener((evt) -> WhatStage(3, 10));
        Intermediate_4 = new JButton("4");
        Intermediate_4.addActionListener((evt) -> WhatStage(4, 10));
        Advanced_1 = new JButton("1");
        Advanced_1.addActionListener((evt) -> WhatStage(1, 15));
        Advanced_2 = new JButton("2");
        Advanced_2.addActionListener((evt) -> WhatStage(2, 15));
        Advanced_3 = new JButton("3");
        Advanced_3.addActionListener((evt) -> WhatStage(3, 15));
        Advanced_4 = new JButton("4");
        Advanced_4.addActionListener((evt) -> WhatStage(4, 15));
        High_1 = new JButton("1");
        High_1.addActionListener((evt) -> WhatStage(1, 20));
        High_2 = new JButton("2");
        High_2.addActionListener((evt) -> WhatStage(2, 20));

        Bb1.add(Begginner_1);
        Bb1.add(Box.createHorizontalStrut(5));
        Bb1.add(Begginner_2);
        Bb1.add(Box.createHorizontalStrut(5));
        Bb1.add(Begginner_3);
        Bb1.add(Box.createHorizontalStrut(5));
        Bb1.add(Begginner_4);
        Bb2.add(Intermediate_1);
        Bb2.add(Box.createHorizontalStrut(5));
        Bb2.add(Intermediate_2);
        Bb2.add(Box.createHorizontalStrut(5));
        Bb2.add(Intermediate_3);
        Bb2.add(Box.createHorizontalStrut(5));
        Bb2.add(Intermediate_4);
        Bb3.add(Advanced_1);
        Bb3.add(Box.createHorizontalStrut(5));
        Bb3.add(Advanced_2);
        Bb3.add(Box.createHorizontalStrut(5));
        Bb3.add(Advanced_3);
        Bb3.add(Box.createHorizontalStrut(5));
        Bb3.add(Advanced_4);
        Bb4.add(Box.createHorizontalStrut(5));
        Bb4.add(High_1);
        Bb4.add(Box.createHorizontalStrut(5));
        Bb4.add(High_2);

        Font font = new Font("Arial", Font.PLAIN, 20);

        JLabel label1 = new JLabel("5 X 5");
        label1.setFont(font);
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("10 X 10");
        label2.setFont(font);
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label3 = new JLabel("15 X 15");
        label3.setFont(font);
        label3.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label4 = new JLabel("20 X 20");
        label4.setFont(font);
        label4.setAlignmentX(Component.CENTER_ALIGNMENT);

        b.add(label1);
        b.add(Bb1);
        b.add(new JLabel(" "));
        b.add(label2);
        b.add(Bb2);
        b.add(new JLabel(" "));
        b.add(label3);
        b.add(Bb3);
        b.add(new JLabel(" "));
        b.add(label4);
        b.add(Bb4);

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 10, 10); // 여백 설정

        contentPane.add(panel, c);
        c.gridy += 1;
        contentPane.add(b, c);
        c.gridy += 1;
        Box Bb = Box.createHorizontalBox();
        contentPane.add(Bb, c);
        c.gridy += 1;
        contentPane.add(Help, c);
    }

    private void WhatStage(int stageNumber, int level) {
        if (level == 5) {
            Stage Level_5 = new Stage(stageNumber, level);
            Level_5.createNewFrame();
        } else if (level == 10) {
            Stage Level_10 = new Stage(stageNumber, level);
            Level_10.createNewFrame();
        } else if (level == 15) {
            Stage Level_15 = new Stage(stageNumber, level);
            Level_15.createNewFrame();
        } else if (level == 20) {
            Stage Level_20 = new Stage(stageNumber, level);
            Level_20.createNewFrame();
        } else {
            JFrame newFrame2 = new JFrame("Picross Practice");
            newFrame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the new frame
            newFrame2.setSize(550, 490);
            newFrame2.setResizable(false);

            PicrossPracticePanel practicePanel1 = new PicrossPracticePanel();
            newFrame2.add(practicePanel1);

            newFrame2.setVisible(true);
        }
    }

}
