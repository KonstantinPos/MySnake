import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class GameField extends JPanel implements ActionListener {

    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private int appleX;
    private int appleY;
    private int[] SnakeX = new int[ALL_DOTS];
    private int[] SnakeY = new int[ALL_DOTS];
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
   private int dots = 3;

    public int getDots() {
        return dots;
    }

    public void setDots(int dots) {
        this.dots = dots;
    }

    Timer  timer = new Timer(250, this);

    public GameField() {
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);
        timer.start();
    }

    public void initGame() {
        for (int i = 0; i < dots; i++) {
            SnakeX[i] = 48 - i * DOT_SIZE;
            SnakeY[i] = 48;
        }
        createApple();
    }


    public void createApple() {
        appleX = new Random().nextInt(20)*DOT_SIZE;
        appleY = new Random().nextInt(20)*DOT_SIZE;
    }


    public void paint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, SIZE, SIZE);

        if (inGame) {
            for (int i = 0; i<dots; i++) {
                g.setColor(Color.RED);
                g.fillRect(SnakeX[i], SnakeY[i] , DOT_SIZE, DOT_SIZE);
            }
            g.setColor(Color.YELLOW);
            g.fillRect(appleX, appleY, DOT_SIZE, DOT_SIZE);
        } else {
            String str = "Game Over";
            g.setColor(Color.BLUE);
            g.drawString(str, 125, SIZE / 2);
        }

    }

    public void move() {
        for (int i = dots; i > 0; i--) {
            SnakeX[i] = SnakeX[i - 1];
            SnakeY[i] = SnakeY[i - 1];
        }
        if(left){
            SnakeX[0] -= DOT_SIZE;
        }
        if(right){
            SnakeX[0] += DOT_SIZE;
        } if(up){
            SnakeY[0] -= DOT_SIZE;
        } if(down){
            SnakeY[0] += DOT_SIZE;
        }
//        if (SnakeX[0] > SIZE) {
//            SnakeX[0] = 0;
//        }
//        if (SnakeX[0] < 0) {
//            SnakeX[0]=SIZE-1;
//        }
    }

    public void checkApple() {
        if (SnakeX[0] == appleX && SnakeY[0] == appleY) {
           dots++;
            createApple();
        }
    }

    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && SnakeX[0] == SnakeX[i] && SnakeY[0] == SnakeY[i]) {
                inGame = false;
            }
        }
        if (SnakeX[0] > SIZE) {
           //SnakeX[0] = 0;
            inGame = false;
        }
        if (SnakeX[0] < 0) {
           //SnakeX[0]=SIZE-1;
            inGame = false;
        }
        if (SnakeY[0] > SIZE) {
            inGame = false;
        }
        if (SnakeY[0] < 0) {
            inGame = false;
        }
    }

        public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();
        }
        repaint();
    }

    class FieldKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            super.keyPressed(e);
            int key = e.getKeyCode();
            if(key == KeyEvent.VK_LEFT && !right){
                left = true;
                up = false;
                down = false;
            }
            if(key == KeyEvent.VK_RIGHT && !left){
                right = true;
                up = false;
                down = false;
            }

            if(key == KeyEvent.VK_UP && !down){
                right = false;
                up = true;
                left = false;
            }
            if(key == KeyEvent.VK_DOWN && !up){
                right = false;
                down = true;
                left = false;
            }

            }

        }
    }



