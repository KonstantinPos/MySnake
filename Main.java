import javax.swing.*;


public class Main extends JFrame {

    GameField g=new GameField();
    String NAMEGAME = "Snake";



    public Main() {
        setTitle( NAMEGAME+": "+g.getDots());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(320, 345);
        setLocationRelativeTo(null);
        add(new GameField());
        setVisible(true);
        setResizable(false);


    }

    public static void main(String[] args) {
        Main main = new Main();
    }
}
