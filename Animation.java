/**
	Class to switch between frames of a selected BufferedImage. By switching frames through indices, it creates the illusion of animation.
	@version May 30, 2021
**/

import java.awt.image.BufferedImage;

public class Animation {
    private int speed, index;
    private long lastTime, time;
    private BufferedImage[] frames;

    /**
	* Constructor for the animation. Has values for speed, and how many frames should be present.
	* 
	* @param speed how frequently to animate
    * @param frames how many frames present
    */

    public Animation(int speed, BufferedImage[] frames){
        this.speed = speed;
        this.frames = frames;
        index = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
	* The method that goes into a timer to update every frame ever amount of speed.
	* 
	* @return void
    */

    public void tick(){
        time += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(time > speed){
            index++;
            time = 0;
            if (index >= frames.length)
                index = 0;
        }
    }

    /**
	* Method to get the current frame the animation is on. Returns the index number of current frame.
	* 
	* @return BufferedImage
    */

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }
}

// https://youtu.be/uitt3vk-Xkk