/**
	Class to display the corresponding Victory/Defeat Screen. If either player wins, the methods here will run.
	@version May 30, 2021
**/

import java.awt.*;
import javax.swing.JComponent;
import java.awt.geom.*;
import java.io.*;

public class VictoryDefeatScreen extends JComponent {

    private int w, h;
    private Player you;
    private Color gray;
    private Audio win, lose;

    /**
	* Constructor to make the Victory/Defeat screen.
	* 
	* @param w width of canvas
    * @param h height of canvas
    * @param you the player
    */
    
    public VictoryDefeatScreen(int w, int h, Player you){
        this.w = w;
        this.h = h;
        this.you = you;
        gray = new Color(99, 99, 99, 200);
        win = new Audio("Tenkeyoho.wav");
        lose = new Audio("Sad Jingle.wav");
    }

    /**
	* Plays corresponding music to the player's win status.
	* 
	* @return void
    */

    public void playTheme(){
        if(you.getWin())
            win.startMusic();
        else
            lose.startMusic();
    }

    /**
	* Draws the text and overlay color depending on who won.
	* 
	* @return void
    * @param g2d
    */

    public void draw(Graphics2D g2d){
        Rectangle2D.Double r = new Rectangle2D.Double(0,0,w,h);
        g2d.setColor(gray);
        g2d.fill(r);
        g2d.setColor(Color.WHITE);
        try{
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("pc-senior regular.ttf"));
            ge.registerFont(font);
            font = font.deriveFont(50.0f);
            g2d.setFont(font);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (FontFormatException e){
            e.printStackTrace();
        }
        if(you.getWin()){
            g2d.drawString("YOU WIN!", 400, h/2);
        }
        else{
            g2d.drawString("YOU LOSE!", 400, h/2);
        }
    }

}
