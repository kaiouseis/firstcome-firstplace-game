/**
	Class of the transparent tile. Has properties of a tile that allows it to be passed through.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;

public class TransparentTile extends Tile{

    private static BufferedImage transparent = ImageLoader.loadImage("transparent tile.png");

    /**
	* Constructor for a transparent tile.
	* 
	* @param id the unique ID of the tile
    */

    public TransparentTile(int id) {
        super(transparent, id);
    }
}
