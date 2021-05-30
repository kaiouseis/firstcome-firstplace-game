/**
	Class to make the sprite sheets usable. Crops portions of the sheet to return a new image.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    /**
	* Method to crop the sprite sheet by a specific size.
	* 
	* @return BufferedImage
    * @param x coordinate
    * @param y coordinate
    * @param width of the new image
    * @param height of the new image
    */

    public BufferedImage crop(int x, int y, int width, int height){// crops the spritesheet to only display a portion
        return sheet.getSubimage(x, y, width, height);
    }
    
}

// https://www.youtube.com/watch?v=Vmpe6mht3qE
