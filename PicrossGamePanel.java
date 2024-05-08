import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;
import java.util.Arrays;

public class PicrossGamePanel extends JPanel {

  private int START;
  private int END;

  int stageNumber;

  int SPAC;
  int ROOM;
  int STAGE_ROOM;
  int[][] StageArray;
  int[] AnswerArray;

  int[] color_StageArray = new int[281];

  private int[] EnterArray;
  private Point[] squares;
  private Point[] squares_c = new Point[281];

  int nsquares;
  int nsquares_c;

  private int arraysEqual_INT = 1;

  ArrayList<Integer> fillSquares = new ArrayList<>();
  ArrayList<Integer> grayFillSquares = new ArrayList<>();
  ArrayList<Integer> XSquares_1 = new ArrayList<>();
  ArrayList<Integer> XSquares = new ArrayList<>();
  ArrayList<Integer> deleteSquares = new ArrayList<>();

  private Point[] stringInt = new Point[1];

  private boolean isClicked;
  private boolean isXTrue;
  private boolean isX_DTrue;
  private boolean isD_DTrue;

  private int initialX; // 초기 누름 위치의 x 좌표
  private int initialY;

  private String text;

  private static Stroke oldStroke;

  public PicrossGamePanel() {
  }

  public PicrossGamePanel(int[] _AnswerArray, int[][] _StageArray, int _SPAC, int _ROOM, int _STAGE_ROOM,
      int _stageNumber) {

    nsquares = 0;
    nsquares_c = 0;

    AnswerArray = _AnswerArray;
    StageArray = _StageArray;
    SPAC = _SPAC;
    ROOM = _ROOM;
    STAGE_ROOM = _STAGE_ROOM;

    stageNumber = _stageNumber;

    EnterArray = new int[ROOM];
    squares = new Point[ROOM];

    if (stageNumber == 0)
      SPAC = 60;

    END = SPAC * STAGE_ROOM;

    if (STAGE_ROOM == 20 || STAGE_ROOM == 15)
      START = 150;
    else if (STAGE_ROOM == 10)
      START = 130;
    else if (stageNumber == 0)
      START = 160;
    else
      START = 110;

    isClicked = false;
    isXTrue = true;
    isX_DTrue = false;
    isD_DTrue = false;

    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        initialX = e.getX();
        initialY = e.getY();
        isClicked = true;

        if (initialX >= START && initialX <= START + END && initialY >= START && initialY <= START + END)
          Drawfill(initialX, initialY, e);
        else
          isColorStageArray(initialX, initialY);
      }

      public void mouseReleased(MouseEvent e) {
        isXTrue = true;
        isX_DTrue = true;
        isD_DTrue = true;
        if (initialX >= START && initialX <= START + END && initialY >= START && initialY <= START + END)
          repaint();
      }
    });

    addMouseMotionListener(new MouseAdapter() {
      public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (initialX >= START && initialX <= START + END && initialY >= START && initialY <= START + END)
          Drawfill(x, y, e);
      }
    });
  }

  public void Drawfill(int x, int y, MouseEvent e) {
    if (SwingUtilities.isRightMouseButton(e)) {
      if (ColoringIs(x, y, fillSquares) && isD_DTrue) {
        isXTrue = false;
        isX_DTrue = false;
        DeleteDraw(x, y, fillSquares, true);
        for (int i = 0; i < deleteSquares.size(); i++)
          EnterArray[deleteSquares.get(i)] = 0;
        deleteSquares.clear();
      }
      if (ColoringIs(x, y, XSquares) && isX_DTrue) {
        isXTrue = false;
        isD_DTrue = false;
        DeleteDraw(x, y, XSquares, false);
        deleteSquares.clear();
      } else if (!ColoringIs(x, y, fillSquares) && !ColoringIs(x, y, XSquares) && isXTrue) {
        isD_DTrue = false;
        isX_DTrue = false;
        XFill(x, y);
      }
    } else {
      if (!ColoringIs(x, y, XSquares))
        GrayFill(x, y);
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    oldStroke = g2d.getStroke();

    drawString_1(g);

    if (stageNumber != 0) {
      g.setColor(new Color(147, 84, 237));
      g2d.setStroke(new BasicStroke(2f));

      g.setFont(new Font("Malgun Gothic", Font.BOLD, 15));

      if (STAGE_ROOM == 20) {
        g.drawString("우클릭 : X 표시, 지우기", END + 15, 20);
        g.drawString("숫자 클릭 : 붉게 표시", END + 15, 40);
      } else {
        g.drawString("우클릭 : X 표시, 지우기", END + 50, 20);
        g.drawString("숫자 클릭 : 붉게 표시", END + 50, 40);
      }

      if (STAGE_ROOM == 5) {
        g.drawString("5 X 5", 20, 30);
      } else if (STAGE_ROOM == 10) {
        g.drawString("10 X 10", 20, 30);
      } else if (STAGE_ROOM == 15) {
        g.drawString("15 X 15", 20, 30);
      } else {
        g.drawString("20 X 20", 20, 30);
      }
      g.setFont(new Font("Arial", Font.BOLD, 23));
      g.drawString("Stage " + Integer.toString(stageNumber), 20, 60);
    } else {
      g.setFont(new Font("Malgun Gothic", Font.BOLD, 15));
      g.drawString("우클릭 : X 표시, 지우기", 450, 550);
      g.drawString("숫자 클릭 : 붉게 표시", 450, 570);
    }

    g.setColor(Color.BLACK);
    g.drawRect(START, START, END, END);
    g2d.setStroke(oldStroke);

    g.setColor(Color.GRAY);

    for (int i = 1; i < STAGE_ROOM; i++) {
      g.drawLine(START, START + SPAC * i, START + END, START + SPAC * i);
      g.drawLine(START + SPAC * i, START, START + SPAC * i, START + END);
    }

    if (isClicked) {
      draw(g);
      isClicked = false;
    } else {
      if (nsquares == 0) {
        for (int i = 0; i < STAGE_ROOM; i++) {
          for (int j = 0; j < STAGE_ROOM; j++) {
            g.drawRect(START + SPAC * j, START + SPAC * i, SPAC, SPAC);
            add(START + SPAC * j, START + SPAC * i);
          }
        }
      }
    }

    g.setColor(Color.BLACK);
    if (nsquares_c == 0)
      DrawNumber(g, true);
    else
      DrawNumber(g, false);

    g.setColor(Color.BLACK);
    g2d.setStroke(new BasicStroke(2f));
    g.drawRect(START, START, END, END);
    if (STAGE_ROOM != 5) {
      for (int i = 1; i <= STAGE_ROOM / 5 - 1; i++) {
        g.drawLine(START, START + SPAC * 5 * i, START + END, START + SPAC * 5 * i);
        g.drawLine(START + SPAC * 5 * i, START, START + SPAC * 5 * i, START + END);
      }
    }

    if (stageNumber == 0)
      HelpGame(g, g2d);

    boolean arraysEqual = Arrays.equals(AnswerArray, EnterArray);
    if (arraysEqual) {
      g.setColor(new Color(147, 84, 237));
      g.setFont(new Font("Malgun Gothic", Font.BOLD, 35));
      g.drawString("정답입니다!", START + END / 2 - 90, START + END + 45);
    }

    g.dispose();

  }

  public void add(int x, int y) {
    squares[nsquares] = new Point(x, y);
    nsquares++;
  }

  public void add_color(int x, int y) {
    squares_c[nsquares_c] = new Point(x, y);
    nsquares_c++;
  }

  public void draw(Graphics g) {

    Graphics2D g2d = (Graphics2D) g;

    g.setColor(Color.BLACK);

    for (int i = 0; i < grayFillSquares.size(); i++)
      if (!fillSquares.contains(grayFillSquares.get(i))) {
        fillSquares.add(grayFillSquares.get(i));
      }
    grayFillSquares.clear();

    for (int i = 0; i < XSquares_1.size(); i++)
      if (!XSquares.contains(XSquares_1.get(i))) {
        XSquares.add(XSquares_1.get(i));
      }
    XSquares_1.clear();

    for (int i = 0; i < fillSquares.size(); i++) {
      g.fillRect(squares[fillSquares.get(i)].x + 2, squares[fillSquares.get(i)].y + 2, SPAC - 4, SPAC - 4);
      EnterArray[fillSquares.get(i)] = 1;
    }

    g.setColor(Color.PINK);
    for (int i = 0; i < XSquares.size(); i++) {
      g2d.setStroke(new BasicStroke(2f));
      g.drawLine(squares[XSquares.get(i)].x + 5, squares[XSquares.get(i)].y + SPAC - 5,
          squares[XSquares.get(i)].x + SPAC - 5, squares[XSquares.get(i)].y + 5);
      g.drawLine(squares[XSquares.get(i)].x + 5, squares[XSquares.get(i)].y + 5, squares[XSquares.get(i)].x + SPAC - 5,
          squares[XSquares.get(i)].y + SPAC - 5);
      g2d.setStroke(oldStroke);
    }

  }

  public void DrawNumber(Graphics g, boolean numberNew) {

    int GAP;
    int H_GAP;

    if (STAGE_ROOM == 5) {
      GAP = 25;
      H_GAP = GAP * StageArray[0].length + 10;
    } else if (STAGE_ROOM == 10) {
      GAP = 23;
      H_GAP = GAP * StageArray[0].length + 8;
    } else {
      GAP = 20;
      H_GAP = GAP * StageArray[0].length + 5;
    }

    if (STAGE_ROOM == 15)
      g.setFont(new Font("Arial", Font.BOLD, 20));
    else if (STAGE_ROOM == 20)
      g.setFont(new Font("Arial", Font.BOLD, 19));
    else
      g.setFont(new Font("Arial", Font.BOLD, 25));

    for (int i = 0; i < StageArray.length / 2; i++) {
      int GAP_1 = 0;
      for (int j = 0; j < StageArray[i].length; j++) {
        GAP_1 += GAP;
        if (StageArray[i][j] != 0) {
          if (!numberNew) {
            if (color_StageArray[StageArray[i].length * i + j] == 1) {
              g.setColor(new Color(255, 133, 202));
              DrawNumberWhatis(H_GAP, GAP_1, i, j, g, false);
            } else {
              DrawNumberWhatis(H_GAP, GAP_1, i, j, g, true);
            }
          } else {
            DrawNumberWhatis(H_GAP, GAP_1, i, j, g, true);
          }

          g.setColor(Color.BLACK);
        }
        if (numberNew)
          add_color(squares[i].x + SPAC / 2 - 7 - 5, squares[i].y - H_GAP + GAP_1 - GAP);
      }
      GAP_1 = 0;
    }

    int a = 0;
    for (int i = StageArray.length / 2; i < StageArray.length; i++) {
      int GAP_1 = 0;
      for (int j = 0; j < StageArray[i].length; j++) {
        int L_GAP = H_GAP + 10;
        GAP_1 += GAP;

        if (StageArray[i][j] != 0) {

          if (!numberNew) {
            if (color_StageArray[StageArray[i].length * i + j] == 1) {
              g.setColor(new Color(255, 133, 202));
              if (StageArray[i][j] >= 10) {
                L_GAP += 9;
              }
            } else {
              if (StageArray[i][j] >= 10) {
                g.setColor(Color.BLUE);
                L_GAP += 9;
              } else
                g.setColor(Color.BLACK);
            }
            g.drawString(Integer.toString(StageArray[i][j]), squares[STAGE_ROOM * a].x - L_GAP + GAP_1, squares[STAGE_ROOM * a].y + SPAC / 2 + 7);
          } else {
            if (StageArray[i][j] >= 10) {
              L_GAP += 9;
              g.setColor(Color.BLUE);
            }
            g.drawString(Integer.toString(StageArray[i][j]), squares[STAGE_ROOM * a].x - L_GAP + GAP_1, squares[STAGE_ROOM * a].y + SPAC / 2 + 7);
            g.setColor(Color.BLACK);
          }
        }
        if (numberNew)
          add_color(squares[STAGE_ROOM * a].x - L_GAP + GAP_1 - 5, squares[STAGE_ROOM * a].y + SPAC / 2 + 7 - GAP);
      }
      GAP_1 = 0;
      a += 1;
    }

  }

  public void DrawNumberWhatis(int H_GAP, int GAP_1, int i, int j, Graphics g, boolean b) {
    if (StageArray[i][j] >= 10) {
      if (b)
        g.setColor(Color.BLUE);
      g.drawString(Integer.toString(StageArray[i][j]), squares[i].x + SPAC / 2 - 7 - 6,
          squares[i].y - H_GAP + GAP_1);
    } else {
      if (b)
        g.setColor(Color.BLACK);
      g.drawString(Integer.toString(StageArray[i][j]), squares[i].x + SPAC / 2 - 7,
          squares[i].y - H_GAP + GAP_1);
    }
  }

  public void GrayFill(int x, int y) {
    Graphics g = getGraphics();
    Graphics2D g2d = (Graphics2D) g;
    DrawGR(x, y, grayFillSquares, g, g2d, Color.lightGray);

    g.setColor(Color.BLUE);
    text = Integer.toString(grayFillSquares.size());

    int mouse_x = x - 10;
    int mouse_y = y;

    g.setXORMode(getBackground());

    DrawString(g, 0);
    stringInt[0].x = mouse_x;
    stringInt[0].y = mouse_y;
    DrawString(g, 0);

  }

  public void XFill(int x, int y) {
    Graphics g = getGraphics();
    Graphics2D g2d = (Graphics2D) g;
    DrawGR(x, y, XSquares_1, g, g2d, Color.PINK);
  }

  public void DrawGR(int x, int y, ArrayList<Integer> drawSquares, Graphics g, Graphics2D g2d, Color color) {
    for (int i = 0; i < nsquares; i++) {
      if ((x >= squares[i].x && x <= squares[i].x + SPAC && y >= squares[i].y && y <= squares[i].y + SPAC)) {
        if (!drawSquares.contains(i)) {
          drawSquares.add(i);
          g.setColor(color);
          for (int j = 0; j < drawSquares.size(); j++) {
            if (color == Color.lightGray) {
              g.fillRect(squares[drawSquares.get(j)].x + 2, squares[drawSquares.get(j)].y + 2, SPAC - 4, SPAC - 4);
              drawString_1(g);
            } else if (color == Color.PINK) {
              g2d.setStroke(new BasicStroke(2f));
              g.drawLine(squares[drawSquares.get(j)].x + 5, squares[drawSquares.get(j)].y + SPAC - 5,
                  squares[drawSquares.get(j)].x + SPAC - 5, squares[drawSquares.get(j)].y + 5);
              g.drawLine(squares[drawSquares.get(j)].x + 5, squares[drawSquares.get(j)].y + 5,
                  squares[drawSquares.get(j)].x + SPAC - 5, squares[drawSquares.get(j)].y + SPAC - 5);
              g2d.setStroke(oldStroke);
            }
          }
        }
      }
    }
  }

  public void DeleteDraw(int x, int y, ArrayList<Integer> DelLists, boolean ISFILL) {
    Graphics g = getGraphics();

    for (int i = 0; i < nsquares; i++) {
      if ((x >= squares[i].x + 1 && x <= squares[i].x - 1 + SPAC && y >= squares[i].y + 1
          && y <= squares[i].y + SPAC - 1)) {
        if (!deleteSquares.contains(i)) {
          deleteSquares.add(i);
          if (ISFILL) {
            g.setColor(Color.RED);
            for (int j = 0; j < deleteSquares.size(); j++) {
              g.fillRect(squares[deleteSquares.get(j)].x + 2, squares[deleteSquares.get(j)].y + 2, SPAC - 4, SPAC - 4);
            }
          }
        }
      }
    }
    for (int i = 0; i < deleteSquares.size(); i++) {
      int valueToRemove = deleteSquares.get(i);
      DelLists.removeIf(DelList -> DelList == valueToRemove);
    }
  }

  public void HelpGame(Graphics g, Graphics2D g2d) {
    g.setFont(new Font("Malgun Gothic", Font.BOLD, 18));
    g.setColor(new Color(147, 84, 237));
    g2d.setStroke(new BasicStroke(2f));

    if (EnterArray[20] == 1 && EnterArray[21] == 1 && EnterArray[22] == 1 && EnterArray[23] == 1 && EnterArray[24] == 1)
      arraysEqual_INT = 2;
    if (EnterArray[15] == 1 && EnterArray[16] == 1 && EnterArray[17] == 0 && EnterArray[18] == 1 && EnterArray[19] == 1)
      arraysEqual_INT = 3;
    if (EnterArray[2] == 1 && EnterArray[7] == 0 && EnterArray[12] == 1 && EnterArray[17] == 0 && EnterArray[22] == 1)
      arraysEqual_INT = 4;
    if (EnterArray[0] == 0 && EnterArray[5] == 0 && EnterArray[10] == 1 && EnterArray[15] == 1 && EnterArray[20] == 1)
      arraysEqual_INT = 5;
    if (EnterArray[1] == 0 && EnterArray[6] == 1 && EnterArray[11] == 1 && EnterArray[16] == 1 && EnterArray[21] == 1)
      arraysEqual_INT = 6;

    if (arraysEqual_INT == 1) {
      g.drawLine(120, 470, 120, 430);
      g.drawLine(120, 430, 130, 430);
      g.drawString("① 연속으로 5개 채우면 된다는 뜻입니다.", 100, 500);
    }

    if (arraysEqual_INT == 2) {
      g.drawLine(70, 475, 70, 370);
      g.drawLine(70, 370, 100, 370);
      g.drawString("② 숫자(2)와 숫자(2) 사이에 반드시", 30, 500);
      g.drawString("한 칸 이상의 공백이 있어야 합니다.", 50, 520);
    }

    if (arraysEqual_INT == 3) {
      g.drawString("③ 숫자(1)와 숫자(1) 사이에 반드시", 280, 50);
      g.drawString("한 칸 이상의 공백이 있어야 합니다.", 300, 70);
    }

    if (arraysEqual_INT == 4) {
      g.drawLine(190, 78, 190, 125);
      g.drawString("④ 하나의 숫자는 연속해서 채울 수 있는 칸의 수를", 100, 50);
      g.drawString("의미하므로 정답 칸은 오직 한 곳입니다.", 120, 70);
    }

    if (arraysEqual_INT == 5) {
      g.drawLine(250, 78, 250, 125);
      g.drawString("⑤ 하나의 숫자는 연속해서 채울 수 있는", 160, 50);
      g.drawString("칸의 수를 의미합니다.", 180, 70);
    }

    if (arraysEqual_INT == 6) {
      g.drawString("나머지도 채워보세요.", 220, 50);
    }

  }

  public boolean ColoringIs(int x, int y, ArrayList<Integer> isList) {
    for (int i = 0; i < isList.size(); i++) {
      if (x >= squares[(int) isList.get(i)].x - 1 && x <= squares[(int) isList.get(i)].x + SPAC + 1
          && y >= squares[(int) isList.get(i)].y - 1 && y <= squares[(int) isList.get(i)].y + SPAC + 1)
        return true;
    }
    return false;
  }

  public void isColorStageArray(int _x, int _y) {
    for (int i = 0; i < nsquares_c; i++) {
      if (_x >= squares_c[i].x && _x <= squares_c[i].x + 25 && _y >= squares_c[i].y && _y <= squares_c[i].y + 25) {
        if (color_StageArray[i] == 0)
          color_StageArray[i] = 1;
        else if (color_StageArray[i] == 1)
          color_StageArray[i] = 0;
        repaint();
      }
    }
  }

  public void DrawString(Graphics g, int i) {
    if (STAGE_ROOM == 15 || STAGE_ROOM == 20)
      g.setFont(new Font("Arial", Font.BOLD, 15));
    else
      g.setFont(new Font("Arial", Font.BOLD, 20));
    g.drawString(text, stringInt[i].x, stringInt[i].y);
  }

  public void drawString_1(Graphics g) {
    text = "0";
    stringInt[0] = new Point(-10, -10);
    DrawString(g, 0);
  }
}