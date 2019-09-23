/*
 *  Mancala.java
 *  Name: Lingwei Gu
 *  Date: June 8, 2019
 *  
 *  		Purpose: It includes the main class that runs the game.
 *			Methods:
 *				Mancala(String title) - constructor
 *				main(String[] args) - no return
 */

package testing;

import javax.swing.JFrame;  //needed to use swing components e.g. JFrame

import java.awt.Color;
import java.awt.Container;

public class Mancala extends JFrame{
	
// Set up constants for width and height of frame 
	static final int WIDTH = 870; 
	static final int HEIGHT = 360;
	
	// constructor
	public Mancala(String title) { 
	   	// Set the title of the frame, must be before variable declarations 
	   	super(title);
			   
	   	MancalaPanel basicPanel; 
	   	Container container;
	   	
	   	// Instantiate and add the SimplePanel to the frame 
	   	basicPanel = new MancalaPanel();
	   	basicPanel.setBackground(Color.getHSBColor(120, 200, 150));
	   	container = getContentPane();
	   	
	   	//container.setLayout(null);
	    setLocationByPlatform(true);
	   	container.add(basicPanel);
	   	container.validate();
	} 
	    
	public static void main(String args[]) { 
		// Instantiate a FirstApplication object so you can display it 
		Mancala frame =  new Mancala("Mancala Game"); 
	    frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
	    // Set the size of the application window (frame) 
	    frame.setLocation(0,0);
		frame.setSize(WIDTH, HEIGHT);
			  
	    frame.setVisible(true); // Show the application (frame) 
	} 
}

