/**
	Class for a tile. The base of the Basic, Transparent, Fail, and Victory tiles.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;
import java.awt.*;

public class Tile {

    public static Tile[] tiles = new Tile[256];
    public static Tile transparentTile = new TransparentTile(0);
    public static Tile basicTile = new BasicTile(1);
    public static Tile failTile = new FailTile(2);
    public static Tile victoryTile = new VictoryTile(3);

    protected BufferedImage texture;
    protected Image animatedTexture;
    protected final int id; //unique tile id

    public static final int TILEWIDTH = 50;
    public static final int TILEHEIGHT = 50;

    /**
	* Constructor for a tile.
	* 
    * @param texture the image of the tile
	* @param id the unique ID of the tile
    */

    public Tile(BufferedImage texture, int id){
        this.texture = texture;
        this.id = id; 

        tiles[id] = this;

    }

    public Tile(Image animatedTexture, int id){
        this.animatedTexture = animatedTexture;
        this.id = id; 

        tiles[id] = this;

    }

    /**
	* Draws the tile.
	* 
	* @param g
    * @param x coordinate
    * @param y coordinate
    */

    public void draw(Graphics g, int x, int y){
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    /**
	* Method that determines whether the tile is solid or not.
	* 
	* @return boolean
    */

    public boolean isSolid(){
        return false;
    }

    /**
	* Method that determines whether the tile will cause a failure or not.
	* 
	* @return boolean
    */

    public boolean isFail(){
        return false;
    }

    /**
	* Method that determines whether the tile will cause a victory or not.
	* 
	* @return boolean
    */

    public boolean isVictory(){
        return false;
    }

    /**
	* Gets the unique ID of the tile.
	* 
	* @return boolean
    */

    public int getId(){
        return id;
    }
}

// https://youtu.be/gZHnM_W5dZE