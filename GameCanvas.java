/**
	Class of the GameCanvas. Draws everything, alongside starting and stopping the timer.
	@version May 30, 2021
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.*;
import java.io.*;

public class GameCanvas extends JComponent {

    private int width;
    private int height;
    public Player you;
    public Enemy enemy;
    private Background bg;
    public static Timer timer;
    private Map map;
    public int playerID;
    private Color gray;
    private VictoryDefeatScreen screen;
    
    /**
	* Constructor to make the canvas.
	* 
    * @param w width of canvas
    * @param h height of canvas
    * @param playerID ID of the current player
    */
    
    public GameCanvas(int w, int h, int playerID){
        width = w;
        height = h;
        this.playerID = playerID;
        map = new Map("layout.txt");
        you = new Player(map,playerID);
        enemy = new Enemy(playerID);
        bg = new Background(0,0);
        screen = new VictoryDefeatScreen(width, height, you);
        ActionListener listener = new TimeListener();
        timer = new Timer(100,listener);
        timer.start();
        gray = new Color(99, 99, 99, 200);
        setPreferredSize(new Dimension(width, height));
    }

    /**
	* Draws the assets onto the canvas. Also displays who the player and enemy is on screen.
	* 
    * @param g
    */

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        bg.draw(g2d);
        map.draw(g2d);
        you.draw(g2d);
        enemy.draw(g2d);
        g2d.setColor(Color.WHITE);
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("pc-senior regular.ttf"));
            ge.registerFont(font);
            font = font.deriveFont(20.0f);
            g2d.setFont(font);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (FontFormatException e){
            e.printStackTrace();
        }
        if(playerID == 1){
            g2d.drawString("You", 20, 50);
            g2d.drawString("Enemy", 20, 500);
        }
        else{
            g2d.drawString("You", 20, 500);
            g2d.drawString("Enemy", 20, 50);
        }

        if(you.getWin() || enemy.getWin()){
            screen.draw(g2d);
        }
    }

    /**
	* The timer. Constantly updates the visuals for the player and enemy movement, and constantly checks if a player won.
	* 
    * @param e
    */

    class TimeListener implements ActionListener{ 
        @Override
        public void actionPerformed(ActionEvent e){
            you.move();
            enemy.move();
            winCondition();
            repaint();
        }
    }

    /**
	* Gets the player.
	* 
    * @return Player
    */

    public Player getPlayer(){
        return you;
    }

    /**
	* Gets the enemy.
	* 
    * @return Enemy
    */

    public Enemy getEnemy(){
        return enemy;
    }

    /**
	* Gets the width of the canvas.
	* 
    * @return int
    */

    public int getWidth(){
        return width;
    }

    /**
	* Gets the height of the canvas.
	* 
    * @return int
    */

    public int getHeight(){
        return height;
    }

    /**
	* Method to stop the timer if either player wins.
	* 
    * @return void
    */

    public void winCondition(){
        if(you.getWin() || enemy.getWin()){
            timer.stop();
            GameFrame.bgm.stopMusic();
            screen.playTheme();
        }
    }
}

// https://youtu.be/JTulErIM7Ec
// https://www.reddit.com/r/javagamedev/comments/6qoc6r/pixel_art_scaling/
// https://stackoverflow.com/questions/24759926/graphics2d-antialias-a-nearest-neighbor-along-pixel-borders
// https://youtu.be/ABMYBWDNgn0
// https://stackoverflow.com/questions/1830603/drawing-text-within-a-jpanel
// https://stackoverflow.com/questions/18249592/how-to-change-font-size-in-drawstring-java
// https://youtu.be/X0WNwCW_-m4
// https://www.codeproject.com/Questions/1100526/How-do-I-change-drawstring-color-in-java
// https://stackoverflow.com/questions/6101886/changing-font-size-in-java
// https://youtu.be/HQoWN28H80w
// https://youtu.be/aIaFFPatJjY
// https://stackoverflow.com/questions/16423503/java-graphics2d-transparent-background
// font: https://www.1001fonts.com/pc-senior-font.html