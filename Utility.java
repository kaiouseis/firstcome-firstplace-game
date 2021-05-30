/**
	Class containing utility methods. Includes loading a text file and parsing each line into a String, and parses Strings into Integers.)
	@version May 30, 2021
**/

import java.io.*;

public class Utility {

    /**
	* Method to load a selected file as a collection of Strings. Once the end of the line is reached, it adds a new line.
	* 
	* @return String
    * @param path to access the file
    */

    public static String loadFileAsString(String path){
        StringBuilder builder = new StringBuilder();

        try{
            BufferedReader br = new BufferedReader(new FileReader (path));
            String line;
            while((line = br.readLine()) != null)
                builder.append(line + "\n");
            br.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        return builder.toString();
    }

    /**
	* Parses the String into an integer.
	* 
	* @return int
    * @param number a number in a String
    */

    public static int parseInt(String number){
        try{
            return Integer.parseInt(number);
        }catch(NumberFormatException e){
            e.printStackTrace();
            return 0;
        }
    }
    
}
