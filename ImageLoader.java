/**
	Class to load an image as a BufferedImage. Cannot read gifs.
	@version May 30, 2021
**/

import java.awt.image.*;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageLoader {

    /**
	* Method to return a selected image.
	* 
    * @return BufferedImage
    * @param filepath location of image
    */
    
    public static BufferedImage loadImage(String filepath){
        try {
            return ImageIO.read(ImageLoader.class.getResource(filepath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
