/*
  *  MancalaPanel.java
 *  Name: Lingwei Gu
 *  Date: June 8, 2019
 *  
 *  		Purpose: It initializes the Mancala panel
 *			Methods:
 *				MancalaPanel() - default constructor
 *				actionPerformed(ActionEvent e) - no return
 *				buttonInput(Hole head) - no return
 *				resetGame() - no return
 *				buildButton(Hole tempHole, int width, int length) - no return
 *				paint() - no return
 *			Class:
 *				ReClickException extends Exception		
 */

package testing;

import java.awt.Font; //needed to use components setting methods (e.g., colors, fonts) 
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.*; 

public class MancalaPanel extends JPanel implements ActionListener, MouseListener{

	 
	private JButton 	exitButton, resetButton;
	private JLabel    playerLabel, statusLabel; //private JLabel    playerLabel, statusLabel;
	private Font      playerFont, fancyFont;
	private Hole head, headConstant, headCopy;
	private Hole leftStore = new Hole(0);
	private Hole rightStore = new Hole(0);
	
	private static int bWidth = 90;
	private static int bHeight = 50;
	
	//constructor
	public MancalaPanel() {
		playerFont = new Font("Dialog", Font.BOLD, 20);
		fancyFont = new Font("Serif", Font.ITALIC, 24);
		//create instance of Mancala
		
		// set the layout for the panel to not have a layout manager
		setLayout(null);
		
		// Create and add a player JLabel 
		playerLabel = new JLabel("Player 1"); 
		playerLabel.setFont(playerFont);
		playerLabel.setBounds(400, 25, 240, 45);
		add(playerLabel);
			
		// Create a 2nd JLabel with a different font 
		statusLabel = new JLabel("No Winner"); 
		statusLabel.setFont(fancyFont);
		statusLabel.setBounds(380, 160, 300, 180);
		add(statusLabel); // Add the label to this Frame
					
		// Create a JButton (14 buttons - 2D)
			Hole tempHole = new Hole(4);
			head = tempHole;
			headConstant = tempHole;
			for (int row = 0; row < 2; row++){ //loop for rows
				for (int col = 0; col < 6; col++) { //loop for columns
					tempHole.setNext(new Hole(4));
					buildButton(tempHole, 165+(col*bWidth), 100+(row*bHeight)); //set location of buttons
	    			
					//assign team
					if (row == 0) {
	    				tempHole.setIsMe("Player 2");
	    			} else {
	    				tempHole.setIsMe("Player 1");
	    			}
			    	
					//insert rightStore
				    if (col == 5 && row == 0) {
						rightStore.setNext(tempHole);
						leftStore.setNext(tempHole.getNext());
						buildButton(rightStore, 730, 125);
					}
				    rightStore.setIsMe("rightStore");
				    
				    //insert leftStore
				    if (row == 1 && col == 5) {
				    	tempHole.setNext(rightStore);
						buildButton(leftStore, 50, 125);
				    	break;
				    }
				    leftStore.setIsMe("leftStore");
				    
				    //update the hole
				    tempHole = tempHole.getNext();
				    
				}
			}
			
			//redirection - make the nodes counter-clock wise
			for (int i = 0; i < 5; i++) {
				headCopy = headConstant;
				for (int a = 0; a < 4 - i; a++) {
					headCopy = headCopy.getNext();
				}
				headCopy.getNext().setNext(headCopy);
			}
			headConstant.setNext(leftStore);
			
			//locate opposite holes
			head = headConstant.getNext().getNext();
			for (int a = 0; a < 6; a++) {
				headCopy = head;
				for (int i = 0; i < 12 - a*2; i++) {
					headCopy = headCopy.getNext();
				}
				head.opposite = headCopy;
				headCopy.opposite = head;
				head = head.getNext();
			}

			
			//Add exit button
			exitButton = new JButton("Exit"); 
			exitButton.setBackground(SystemColor.control); 
			exitButton.setLocation(440, 270); 
		    exitButton.setSize(100,40); 
		    add(exitButton);
		    
		    //Add reset button to allow for a new game    
		    resetButton = new JButton("New Game"); 
			resetButton.setBackground(SystemColor.control); 
			resetButton.setLocation(310, 270); 
		    resetButton.setSize(125,40); 
		    add(resetButton);
		    
		    // Add event listeners for the buttons
		    exitButton.addActionListener(this);
		    resetButton.addActionListener(this);
		    exitButton.addMouseListener(this);
		    resetButton.addMouseListener(this);
		    
		    validate();
		}	
				
			
//This is the event handler for the button 
    public void actionPerformed(ActionEvent e) { 
    	if (e.getActionCommand().equals("Exit")) {
        	System.exit(0);
        }
       
        if (e.getActionCommand().equals("New Game")){
    	   // Call the method to reset the game
    	    resetGame();
        }
        
        //catch exception / button response
    	for (int i = 0; i < 14; i++) {
	    	if (e.getSource() == head.getButton()) {
	    		try { //catch the ReClickException
					buttonInput(head);
				} catch (ReClickException ex) {
					System.out.println("The button is already clicked.");
				}
	    		break;
	    	}
	    	head = head.getNext();
    	}
    	paint();
    }
    
    //dealing with the input
    public void buttonInput(Hole head) throws ReClickException{
    	boolean notKeepGoing = true;
    	String winCondition = "";
		//throw the exception
    	if (head.getButton().getText().equals("0") || !statusLabel.getText().equals("No Winner") || !head.getIsMe().equals(playerLabel.getText())) {
    		throw new ReClickException(); //Then throw it!
    	}
    	MancalaRules rules = new MancalaRules();
    	
    	notKeepGoing = rules.checkNotKeepGoing(head);
    	if (head.getIsMe().equals("Player 1")) { //operate gem number
    		rightStore.setGemNum(rules.moveGem(head, playerLabel) + rightStore.getGemNum());
    	} else {
    		leftStore.setGemNum(rules.moveGem(head, playerLabel) + leftStore.getGemNum());
    	}
    	
    	if (notKeepGoing) {//check who goes next
			playerLabel.setText(head.getOpposite().isMe);
		}
		head.getButton().setText(Integer.toString(head.getGemNum()));
		winCondition = rules.checkWinner(leftStore, rightStore); //check winner
		if(!winCondition.equals("Keep Going")) { //reset panel
			statusLabel.setText(winCondition);
			for (int i = 0; i < 14; i++) {
				if (headCopy.getIsMe().equals("leftStore") || headCopy.getIsMe().equals("rightStore")) {
					continue;
				}
				headCopy.setGemNum(0);
				headCopy = headCopy.getNext();
			}
			paint();
		}
		
    }

 // change everything back to the beginning  
	public void resetGame(){
		playerLabel.setText("Player 1");
		statusLabel.setText("No Winner");
		for (int i = 0; i < 14; i++) {
			head.setGemNum(4);
			head = head.getNext();
		}
		leftStore.setGemNum(0);
		rightStore.setGemNum(0);
		paint();
	}
	
	//build a button to avoid repetitive code
	public void buildButton(Hole tempHole, int width, int length) {
		tempHole.setButton(new JButton("")); //set content
		tempHole.getButton().setBackground(SystemColor.control); //set colour
		tempHole.getButton().setLocation(width, length); //set location
		tempHole.getButton().setSize(bWidth, bHeight);  //set size 
	    add(tempHole.getButton());
	    
	    //Set all buttons to work with the event handlers
	    tempHole.getButton().addActionListener(this);
	    tempHole.getButton().addMouseListener(this);
	    tempHole.getButton().setText(Integer.toString(tempHole.getGemNum()));
	}
	
	//repaint the panel
	public void paint() {
		for (int i = 0; i < 14; i++) {
			head.getButton().setText(Integer.toString(head.getGemNum()));
			head = head.getNext();
		}
	}

	public void mousePressed(MouseEvent event) { 
	}
	public void mouseReleased(MouseEvent event) { 
		  // Good for dragging situations
	  }
	
	public void mouseEntered(MouseEvent event) { 
	  	//Brings focus to the button 
	}
		
	public void mouseExited(MouseEvent event) { 
	  	//Removes focus from the button
	}
	   
	public void mouseClicked(MouseEvent event) { 
	        
	}
}	
	
class ReClickException extends Exception{
	public ReClickException() {
		super();
	}
}
