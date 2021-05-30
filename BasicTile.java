/**
	Class for a BasicTile. Has properties of a solid tile, and cannot be passable.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;

public class BasicTile extends Tile{

    private static BufferedImage basic = ImageLoader.loadImage("basic tile.png");

    /**
	* Constructor for a basic tile.
	* 
	* @param id the unique ID of the tile
    */

    public BasicTile(int id) {
        super(basic, id);
    }

    /**
	* Method that determines whether the tile is solid or not.
	* 
	* @return boolean
    */
    
    @Override
    public boolean isSolid(){
        return true;
    }
}
