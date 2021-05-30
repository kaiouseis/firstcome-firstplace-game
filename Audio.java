/**
	Class to import, play, and stop audio. Imports the audio specified in the parameters, and must be present in the folder.
	@version May 30, 2021
**/

import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Audio {

    private Clip clip = null;

    /**
	* Constructor for the Audio.
	* 
    * @param location of the audio file
    */

    public Audio(String location){

        try{
            File path = new File(location);
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(path);
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        }
        catch(IOException | UnsupportedAudioFileException | LineUnavailableException e){
            System.out.println("Error in importing audio.");
        }
    }

    /**
	* Method to play the music. Automatically loops.
	* 
	* @return void
    */
    
    public void startMusic(){
        try{
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

    /**
	* Stops the music.
	* 
	* @return void
    */

    public void stopMusic(){
        clip.stop();
    }
}

// https://www.youtube.com/watch?v=TErboGLHZGA
// https://stackoverflow.com/questions/23986793/java-playing-a-sound-on-top-of-background-music

/* list of audio:
main bgm: https://youtu.be/PXrtP2PtHxI
victory theme: https://youtu.be/R9OvkwXVyJw
defeat theme: https://youtu.be/UpgKc5nKkTc 

*/