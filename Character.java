/**
	Interface for a standard character. Serves as the base for both the player and the enemy.
	@version May 30, 2021
**/

import java.awt.*;

public interface Character {

    /**
	* Method to get the X coordinate of the character.
	* 
	* @return int
    */

    int getX();

    /**
	* Method to get the Y coordinate of the character.
	* 
	* @return int
    */

    int getY();

    /**
	* Method to set the direction of the character.
	* 
	* @return void
    * @param dir the direction to set the character in
    */

    void setDirection(String dir);

    /**
	* Method to move the character.
	* 
	* @return void
    */

    void move();

    /**
	* Method to draw the character.
	* 
	* @return void
    * @param g2d
    */

    void draw(Graphics2D g2d);

    /**
	* Gets the win status of the character.
	* 
	* @return boolean
    */

    boolean getWin();
    
}
