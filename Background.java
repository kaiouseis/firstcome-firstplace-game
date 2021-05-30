/**
	Class to create the background. Uses an ImageIcon to allow gifs.
	@version May 30, 2021
**/

import java.awt.*;

import javax.swing.ImageIcon;

public class Background {
    private static Image bg;
    private int x, y;

    /**
	* Constructor to make the background. Uses a gif as the background.
	* 
	* @param x coordinate
    * @param y coordinate
    */
    public Background(int x, int y){
        this.x = x;
        this.y = y;
        bg = new ImageIcon("background 2.gif").getImage();
    }

    /**
	* The method that draws the background.
	* 
	* @return void
    */

    public void draw(Graphics2D g2d) {
        g2d.drawImage(bg,x,y,null);
    }
}
// https://stackoverflow.com/questions/22240328/how-to-draw-a-gif-animation-in-java