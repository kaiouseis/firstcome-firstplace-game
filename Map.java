/**
	Class to set up a map. Draws the tiles on screen according to a text file containing the corresponding tile IDs.
	@version May 30, 2021
**/

import java.awt.Graphics;

public class Map {

    private int width, height;
    private int[][] tiles;

    /**
	* Constructor to make a map, which runs the loadMap method.
	* 
    * @param path location of text file
    */

    public Map(String path){
        loadMap(path);
    }

    /**
	* Draws the tiles on screen according to the map text file.
	* 
	* @return void
    * @param g
    */

    public void draw(Graphics g){
        for(int y = 0; y < height; y++){
            for(int x = 0;x < width; x++){
                getTile(x,y).draw(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);
            }
        }
    }

    /**
	* Gets the tile at a specific x and y index, and identifies what tile it is.
	* 
	* @return Tile
    * @param x coordinate
    * @param y coordinate
    */

    public Tile getTile(int x, int y){
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.basicTile;
        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null){
            return Tile.transparentTile;
        }
        return t;
    }

    /**
	* Loads the text file, and also splits the text by whitespaces, and then parses each index (minus whitespace) into an integer.
	* 
	* @return int
    * @param path location of text file
    */

    private void loadMap(String path){
        String file = Utility.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utility.parseInt(tokens[0]);
        height = Utility.parseInt(tokens[1]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles[x][y] = Utility.parseInt(tokens[(x + y * width + 2)]); //+ 2 to ignore the width and height set a the begnning of the text file
            }
        }
    }
}
// https://youtu.be/gZHnM_W5dZE
// https://youtu.be/kh0vGi9GrGY
// https://youtu.be/DadImcUt9Nk