/**
	Class for the GameFrame. Sets up the GUI, and connects to the server.
	@version May 30, 2021
**/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.util.*;

public class GameFrame{
    private JFrame frame;
    private GameCanvas gc;
    private Container cp;
    private Socket socket;
    private int playerID, port;
    private String ipAddress;

    private ReadFromServer rfsRunnable;
    private WriteToServer wtsRunnable;
    public static Audio bgm;
    
    /**
	* Constructor for the frame.
	* 
    */

    public GameFrame(){
        frame = new JFrame();
    }

    /**
	* Method to connect to the GameServer. Sets the playerID of the Canvas.
	* 
    * @return void
    */

    public void connectToServer(){
        try{
            Scanner console = new Scanner(System.in);
            System.out.print("Type IP Address: ");
            ipAddress = console.nextLine();
            System.out.print("Type Port Address: ");
            port = Integer.parseInt(console.nextLine());
            console.close();
            socket = new Socket(ipAddress,port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            playerID = in.readInt();
            System.out.println("You are Player " + playerID);
            gc = new GameCanvas(1200, 850, playerID);
            if(playerID == 1){
                System.out.println("Waiting for Player 2 to connect.");
            }
            
            rfsRunnable = new ReadFromServer(in, gc);
            wtsRunnable = new WriteToServer(out, gc);
            rfsRunnable.waitForStart();
            
        }
        catch(IOException ex){
            System.out.println("Client error from connectToSever().");
        }
    }

    /**
	* Method to close the socket when client closes.
	* 
    * @return void
    */

    public void closeSocketOnShutdown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
          try {
            socket.close();
          } catch (IOException e) {
            System.out.println("IOException from closeSocketOnShutdown() method.");
          }
        }));
      }

    /**
	* Class to read data from the server. Sets the X & Y coordinates, direction, and win status of the Enemy.
	* 
    */

    private class ReadFromServer implements Runnable{
        private DataInputStream dataIn;

        /**
        * Constructor to ReadFromServer.
        * 
        * @param in
        * @param canvas
        */
        
        public ReadFromServer(DataInputStream in, GameCanvas canvas){
            gc = canvas;
            dataIn = in;
        }
        
        /**
        * Runs the method to retrieve data.
        * 
        */
        
        public void run(){
            try{
                while(true){
                    if(gc != null){
                        gc.getEnemy().setX(dataIn.readInt());
                        gc.getEnemy().setY(dataIn.readInt());
                        gc.getEnemy().setDirection(dataIn.readUTF());
                        gc.getEnemy().setWin(dataIn.readBoolean());
                    }
                }
            }
            catch(IOException ex){
                System.out.println("Server error from ReadFromServer().");
            }
        }

        /**
        * Waits for a message to be sent from the GameServer.
        * 
        * @return void
        */

        public void waitForStart(){
            try{
                String start = dataIn.readUTF();
                System.out.println(start);
                Thread readThread = new Thread(rfsRunnable);
                Thread writeThread = new Thread(wtsRunnable);
                readThread.start();
                writeThread.start();
            }
            catch(IOException ex){
                System.out.println("Error in waitForStart().");
            }
        }
    }

    /**
    * Class to send data to the GameServer. Sends the X & Y coordinates of the player, direction, and win status.
    * 
    */

    private class WriteToServer implements Runnable{
        private DataOutputStream dataOut;
        
        /**
        * Constructor to WriteToServer.
        * 
        * @param out
        * @param canvas
        */

        public WriteToServer(DataOutputStream out, GameCanvas canvas){
            gc = canvas;
            dataOut = out;
        }

        /**
        * Runs the method to send data.
        * 
        * @return void
        */

        public void run(){
            try{
                while(true){
                    if(gc != null){
                        dataOut.writeInt(gc.getPlayer().getX());
                        dataOut.writeInt(gc.getPlayer().getY());
                        dataOut.writeUTF(gc.getPlayer().getCurrentDir());
                        dataOut.writeBoolean(gc.getPlayer().getWin());
                        dataOut.flush();
                    }
                    try{
                        Thread.sleep(25);
                    }
                    catch(InterruptedException ex){
                        System.out.println("InterruptedException in WriteToServer().");
                    }
                }
            }
            catch(IOException ex){
                System.out.println("Error in WriteToServer.");
            }
        }
    }

    /**
    * Sets up the GUI.
    * 
    * @return void
    */

    public void setUpGUI(){
        frame.setTitle("First Come, First Place! Player: " + playerID);
        frame.add(gc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        bgm = new Audio("Sky High.wav");
        bgm.startMusic();
    }

    /**
    * Sets the keybindings of the game. W is Up, A is left, and S is right. When the key is released, it sets off released.
    * 
    * @param in
    * @param canvas
    */

    public void addKeyBindings(){
        JPanel cp = (JPanel) frame.getContentPane();
        cp.setFocusable(true);

        ActionMap am = cp.getActionMap();
        InputMap im = cp.getInputMap();

        am.put("upPress", new MoveAction("up"));
        am.put("downPress", new MoveAction("down"));
        am.put("rightPress", new MoveAction("right"));
        am.put("leftPress", new MoveAction("left"));
        am.put("release", new MoveAction("released"));

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "upPress");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), "downPress");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "leftPress");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), "rightPress");

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "release");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), "release");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "release");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), "release");
    }

    /**
    * Class to change the direction of the player via keybindings.
    * 
    */

    public class MoveAction extends AbstractAction{
        private String direction;

        /**
        * Constructor to set direction.
        * 
        * @param dir direction
        */

        public MoveAction(String dir){
            direction = dir;
        }

        /**
        * Method to change the direction of the player according to the pressed key.
        * 
        * @param in
        * @param canvas
        */

        @Override
        public void actionPerformed(ActionEvent e) {
            gc.getPlayer().setDirection(direction);
        }
    }   
}


// https://youtu.be/AhNJCfn_Odc
// https://stackoverflow.com/questions/25656426/java-code-returning-null-instead-of-a-string