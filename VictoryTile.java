/**
	Class of a victory tile. Has properties of a tile that allow the player to win when jumped on.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;

public class VictoryTile extends Tile{

    private static BufferedImage victory = ImageLoader.loadImage("trophy.png");

    /**
	* Constructor for a victory tile.
	* 
	* @param id the unique ID of the tile
    */

    public VictoryTile(int id) {
        super(victory, id);
    }

    /**
	* Method that determines whether the tile will cause a victory or not.
	* 
	* @return boolean
    */

    @Override
    public boolean isVictory(){
        return true;
    }
}
