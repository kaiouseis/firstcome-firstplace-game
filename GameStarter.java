/**
	Class to start the client game. Connects to server first, then sets up the GUI so that both players start at the same time.
	@version May 30, 2021
**/

import javax.swing.*;

public class GameStarter{

    /**
	* Main method to run the client program.
	* 
    * @return void
    * @param args
    */
    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
            GameFrame gf = new GameFrame();
            gf.closeSocketOnShutdown();
            gf.connectToServer();
            gf.setUpGUI();
            gf.addKeyBindings(); 
            }
        });
    }
}