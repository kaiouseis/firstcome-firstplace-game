/**
	Class of the enemy. By receiving data of the opposing player's coordinates, the enemy sprite can move accordingly.
	@version May 30, 2021
**/

import java.awt.*;
import java.awt.image.*;

public class Enemy implements Character {

    private int x;
    private int y;
    private int speed = 10;
    private int xMove = 0, yMove = 0;
    private static SpriteSheet sheet;
    private static BufferedImage test;
    private boolean left, right, up;
    private boolean win = false;
    private int playerID;
    public static BufferedImage[]  playerJump, playerLeft, playerRight;
    private Animation animRight, animLeft;
    private Map m;

    public Enemy(int playerID){
        this.playerID = playerID;
        
        left = false;
        right = false;
        up = false;

        if(playerID == 1){
            test = ImageLoader.loadImage("P2 Sheet.png");
            sheet = new SpriteSheet(test);
            x = 75;
            y = 673;
            
        }
        else{
            test = ImageLoader.loadImage("P1 Sheet.png");
            sheet = new SpriteSheet(test);
            x = 75;
            y = 273;
        }

        
        playerLeft = new BufferedImage[2];
        playerRight = new BufferedImage[2];

        playerLeft[0] = sheet.crop(0,130,80,130);
        playerLeft[1] = sheet.crop(80,130,80,130);
        playerRight[0] = sheet.crop(0,260,80,130);
        playerRight[1] = sheet.crop(80,260,80,130);

        animRight = new Animation(speed,playerRight);
        animLeft = new Animation(speed,playerLeft);
    }

    /**
	* Method to get the X coordinate of the character.
	* 
	* @return int
    */

    @Override
    public int getX() {
        return x;
    }

    /**
	* Method to get the Y coordinate of the character.
	* 
	* @return int
    */

    @Override
    public int getY() {
        return y;
    }

    /**
	* Method to set the X coordinate of the character.
	* 
	* @return void
    * @param n the X coordinate
    */

    public void setX(int n) {
        xMove = n;
    }

    /**
	* Method to set the Y coordinate of the character.
	* 
	* @return void
    * @param n the Y coordinate
    */

    public void setY(int n) {
        yMove = n;
    }

    /**
	* Method to set the direction of the enemy.
	* 
	* @return void
    * @param dir the direction to set the enemy in
    */

    @Override
    public void setDirection(String dir) {
        if(dir.equals("up")){
            up = true;
        }
        else if(dir.equals("left")){
            left = true;
            right = false;
        }
        else if(dir.equals("right")){
            left = false;
            right = true;
        }
        else if(dir.equals("released")){
            up = false;
            left = false;
            right = false;
        }
    }

    /**
	* Method to move the enemy. When the enemy moves, the animation plays.
	* 
	* @return void
    */

    @Override
    public void move() {
        x = xMove;
        animLeft.tick();
        y = yMove;
        animRight.tick();
    }

    /**
	* Method to draw the enemy. When the enemy is facing a specific direction, they face that direction.
	* 
	* @return void
    * @param g2d
    */

    @Override
    public void draw(Graphics2D g2d) {
        if(right){
            g2d.drawImage(animRight.getCurrentFrame(),x,y,null);
        }
        else if(left){
            g2d.drawImage(animLeft.getCurrentFrame(),x,y,null);
        }
        else{
            g2d.drawImage(sheet.crop(0,0,80,130),x,y,null);
        }
        
    }

    /**
	* Sets the win status of the enemy.
	* 
	* @return boolean
    */

    public void setWin(boolean win){
        this.win = win;
    }

    /**
	* Gets the win status of the enemy.
	* 
	* @return boolean
    */

    @Override
    public boolean getWin() {
        return win;
    }
}