import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Felix Mann
 * @version 1.0
 * @since 2020-Juli-09
 */

public class GameFrame extends JFrame {
    private final GameMap map;
    private final Schalange snake;
    private final Berry berry;
    private final JPanel mainPanel;
    private final JPanel gamePanel;
    private char direction, lastdirection;

    public GameFrame() {
        map = new GameMap();
        snake = new Schalange();
        berry = new Berry();

        mainPanel = new JPanel();
        gamePanel = new JPanel();
        direction = 'd';
        lastdirection = 'd';


        init();

        paintSnake();
        paintBerry();
        renderField();

        setSize(820, 820);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);


        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                paintHandler();
                renderField();
            }
        }, 500, 400);
    }

    private void paintHandler(){
        handleSnake();
        paintSnake();
        paintBerry();
    }

    private void paintBerry() {
        if (berry.isEaten()){
            berry.setEaten(false);
            berry.setRandomPos();

            while (map.getField(berry.getYPos(), berry.getXPos()) != ' '){
                berry.setRandomPos();
            }
        }

        map.setField(berry.getYPos(), berry.getXPos(), 'b');
    }

    private void readInput(char keyChar) {
        if (isNotOpposite(keyChar)){
            switch (keyChar) {
                case 'w':
                    direction = 'w';
                    break;
                case 'a':
                    direction = 'a';
                    break;
                case 's':
                    direction = 's';
                    break;
                case 'd':
                    direction = 'd';
                    break;
            }
            lastdirection = direction;
        }
    }

    private boolean isNotOpposite(char keyChar){
        switch (lastdirection){
            case 'w':
                if (keyChar == 's') return false;
                break;
            case 'a':
                if (keyChar == 'd') return false;
                break;
            case 's':
                if (keyChar == 'w') return false;
                break;
            case 'd':
                if (keyChar == 'a') return false;
                break;
        }
        return true;
    }

    private void renderField() {
        gamePanel.removeAll();
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                gamePanel.add(new Field(map.getField(i, j)));
            }
        }
        gamePanel.revalidate();
    }

    private void handleSnake() {
        switch (direction){
            case 'w':
                snake.setYPos(snake.getYPos(0) - 1);
                snake.setXPos(snake.getXPos(0));
                break;
            case 'a':
                snake.setYPos(snake.getYPos(0));
                snake.setXPos(snake.getXPos(0) - 1);
                break;
            case 's':
                snake.setYPos(snake.getYPos(0) + 1);
                snake.setXPos(snake.getXPos(0));
                break;
            case 'd':
                snake.setYPos(snake.getYPos(0));
                snake.setXPos(snake.getXPos(0) + 1);
                break;
        }
        checkBerry();
        checkOOB();
        checkEatTail();

        snake.removeLastIndex();
    }

    private void checkEatTail() {
        switch (direction){
            case 'w':
                if (!(snake.getYPos(0) - 1 <= 0)){
                    if (map.getField(snake.getYPos(0), snake.getXPos(0)) == 's'){
                        fertig();
                    }
                }
                break;
            case 'a':
                if (!(snake.getXPos(0) - 1 <= 0)){
                    if (map.getField(snake.getYPos(0), snake.getXPos(0)) == 's'){
                        fertig();
                    }
                }
                break;
            case 'd':
                if (!(snake.getXPos(0) + 1 <= 20)){
                    if (map.getField(snake.getYPos(0), snake.getXPos(0)) == 's'){
                        fertig();
                    }
                }
                break;
            case 's':
                if (!(snake.getYPos(0) + 1 <= 20)){
                    if (map.getField(snake.getYPos(0), snake.getXPos(0)) == 's'){
                        fertig();
                    }
                }
                break;
        }
    }

    private void fertig() {
        new Snake();
    }

    private void checkOOB(){
        if (snake.getXPos(0) > 19 || snake.getXPos(0) < 0 ||  snake.getYPos(0) > 19 || snake.getYPos(0) < 0) {
            //TODO game beenden und neu starten
            System.err.println("Fertig");
            System.exit(0);
        }
    }

    private void checkBerry(){
        if (map.getField(snake.getYPos(0),snake.getXPos(0)) == 'b'){
            snake.grow();
            snake.setYPos(berry.getYPos());
            snake.setXPos(berry.getXPos());

            berry.setEaten(true);
        }
    }

    private void paintSnake() {
        map.resetMap();

        for (int i = snake.getLegth() - 1; i >= 0; i--) {
            if (i == 0){
                map.setField(snake.getYPos(i), snake.getXPos(i), 'h');
            } else {
                map.setField(snake.getYPos(i), snake.getXPos(i), 's');
            }
        }
    }

    private void init() {
        getContentPane().add(mainPanel);

        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setPreferredSize(new Dimension(800, 800));
        mainPanel.setBackground(new Color(77, 210, 68));
        mainPanel.add(gamePanel);

        gamePanel.setLayout(new GridLayout(20, 20));

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                readInput(e.getKeyChar());
            }
        });
    }
}
