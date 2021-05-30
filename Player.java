/**
	Class for the player. Collision detection when moving is also present here. When the player moves in a specific direction, the corresponding animation plays.
	@version May 30, 2021
**/

import java.awt.*;
import java.awt.image.*;

public class Player implements Character {

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

    private int boundsX = 25, boundsY = 20, boundsWidth = 20, boundsHeight = 100;
    private String direction;
    

    public Player(Map m, int playerID){
        this.m = m;
        this.playerID = playerID;
        
        left = false;
        right = false;
        up = false;
        direction = "released";

        if(playerID == 1){
            test = ImageLoader.loadImage("P1 Sheet.png");
            sheet = new SpriteSheet(test);
            x = 75;
            y = 273;
        }
        else{
            test = ImageLoader.loadImage("P2 Sheet.png");
            sheet = new SpriteSheet(test);
            x = 75;
            y = 673;
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
	* Gets the win status of the player.
	* 
	* @return boolean
    */

    @Override
    public boolean getWin() {
        return win;
    }

    /**
	* Gets the current direction of the player.
	* 
	* @return String
    */

    public String getCurrentDir(){
        return direction;
    }

    /**
	* Method to move the player. Checks for specific collision conditions: a fail tile or a victory tile.
	* 
	* @return void
    */

    @Override
    public void move(){
        int ty = (y + boundsY + boundsWidth + boundsHeight) / Tile.TILEHEIGHT;
        moveX();
        moveY();
        if(!collisionWithTile((int) (x + boundsX) / Tile.TILEWIDTH, ty) && !collisionWithTile((int) (x + boundsX + boundsWidth) / Tile.TILEWIDTH, ty)){
            yMove = 25;
            y += yMove;
        }
        if(collisionWithFail((int) (x + boundsX) / Tile.TILEWIDTH, ty) && collisionWithFail((int) (x + boundsX + boundsWidth) / Tile.TILEWIDTH, ty)){
            if (playerID == 1){
                x = 75;
                y = 50;
            }
            else{
                x = 75;
                y = 450;
            }
        }
        if(collisionWithVictory((int) (x + boundsX) / Tile.TILEWIDTH, ty) && collisionWithVictory((int) (x + boundsX + boundsWidth) / Tile.TILEWIDTH, ty)){
            win = true;
        }
    }

    /**
	* Method to move the player horizontally. If in collision with a tile, the player cannot move any further.
	* 
	* @return void
    */

    public void moveX(){
        if(left){
            int tx = (x + xMove + boundsX) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + boundsY) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + boundsY + boundsHeight) / Tile.TILEHEIGHT)){
                animLeft.tick();
                xMove = speed;
                x -= xMove;
            }
        }
        else if(right){
            int tx = (x + xMove + boundsX + boundsWidth) / Tile.TILEWIDTH;
            if(!collisionWithTile(tx, (int) (y + boundsY) / Tile.TILEHEIGHT) && !collisionWithTile(tx, (int) (y + boundsY + boundsHeight) / Tile.TILEHEIGHT)){
                animRight.tick();
                xMove = speed;
                x += xMove;
            }
        }
    }

    /**
	* Method to move the player vertically. If in collision with a tile, the player cannot move any further.
	* 
	* @return void
    */

    public void moveY(){
        if(up){
            int ty = (y + boundsY + boundsWidth + boundsHeight) / Tile.TILEHEIGHT;
            if(collisionWithTile((int) (x + boundsX) / Tile.TILEWIDTH, ty) && collisionWithTile((int) (x + boundsX + boundsWidth) / Tile.TILEWIDTH, ty)){
                yMove = 50;
                y -= yMove;
            }
        }
    }

    /**
	* Checks if current tile is solid.
	* 
	* @return void
    */

    protected boolean collisionWithTile(int x, int y){
        return m.getTile(x, y).isSolid();
    }

    /**
	* Checks if current tile is fail.
	* 
	* @return void
    */

    protected boolean collisionWithFail(int x, int y){
        return m.getTile(x, y).isFail();
    }

    /**
	* Checks if current tile is victory.
	* 
	* @return void
    */

    protected boolean collisionWithVictory(int x, int y){
        return m.getTile(x, y).isVictory();
    }

    /**
	* Method to set the direction of the player.
	* 
	* @return void
    * @param dir the direction to set the player in
    */

    @Override
    public void setDirection(String dir){
        direction = dir;
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
	* Method to draw the player. When the player is facing a specific direction, they face that direction.
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
        //g2d.setColor(Color.red);
        //g2d.fillRect((x + boundsX), y + boundsY, boundsWidth, boundsHeight);
    }
}

// https://youtu.be/MYVSIcVuDmM
// https://youtu.be/uitt3vk-Xkk
// https://gamedev.stackexchange.com/questions/128631/trying-to-make-a-character-jump
// https://youtu.be/zUOkojY_Ylo
// https://youtu.be/qNK5B82IXvc