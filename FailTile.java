/**
	Class for a FailTile. Has properties of a tile that will cause the player to start over.
	@version May 30, 2021
**/

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.*;

public class FailTile extends Tile{

    private static Image fail = new ImageIcon("fail tile.gif").getImage();

    /**
	* Constructor for a fail tile.
	* 
	* @param id the unique ID of the fail tile
    */

    public FailTile(int id) {
        super(fail, id);
    }

    /**
	* Method that determines whether the tile will cause a failure or not.
	* 
	* @return boolean
    */

    @Override
    public boolean isFail(){
        return true;
    }

    /**
	* Overriden draw method to draw the appropriate Image file.
	* 
	* @return void
    * @param g
    * @param x coordinate
    * @param y coordinate
    */

    @Override
    public void draw(Graphics g, int x, int y){
        g.drawImage(fail, x, y, TILEWIDTH, TILEHEIGHT, null);
    }
}
