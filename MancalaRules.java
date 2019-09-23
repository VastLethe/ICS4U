/*
  *  MancalaPanel.java
 *  Name: Lingwei Gu
 *  Date: June 8, 2019
 *  
 *  		Purpose: It has all the rules of the game
 *			Methods:
 *				MancalaRules() - constructor
 *				moveGem(Hole head, JLabel playerLabel) - returns int
 *				checkNotKeepGoing(Hole head) - returns boolean
 *				checkWinner(Hole leftStore, Hole rightStore) - returns String
 *
 *				
 *				
 */
package testing;

import javax.swing.JLabel;

public class MancalaRules {
	public MancalaRules() { //default constructor
	}
	
	//distribute gems
	public int moveGem(Hole head, JLabel playerLabel) {
		int gemsLeft = head.getGemNum();
		String oppStore = "";
		if (head.getIsMe().equals("Player 2")) { //record the enemy store to skip it
			oppStore = "rightStore";
		} else if (head.getIsMe().equals("Player 1")) {
			oppStore = "leftStore";
		}
		head.setGemNum(0);
		for (int i = 0; i < gemsLeft; i++) { //move the gems
			head = head.getNext();
			if (head.getIsMe().equals(oppStore)) { //skip the enemy store
				i--;
				continue;
			}
			head.setGemNum(head.getGemNum() + 1); //add a gem to each store passed by
		}
		if (head.getGemNum() == 1 && head.getIsMe().equals(playerLabel.getText())) { //capture pieces with landing the last move on an empty hole on your side
			if (head.getOpposite().getGemNum() == 0) {
				return 0;
			}
			int gemsCaptured = head.getGemNum() + head.getOpposite().getGemNum();
			head.setGemNum(0);
			head.getOpposite().setGemNum(0);
			return gemsCaptured;
		}
		return 0;
	}
	
	//check the player gets an extra round
	public boolean checkNotKeepGoing(Hole head) {
		Hole headCopy = head;
		int gemsLeft = head.getGemNum();
		for (int i = 0; i < gemsLeft; i++) { //locate the last landing
			head = head.getNext();
		}
		if (head.getIsMe().equals("Player 1") || head.getIsMe().equals("Player 2")) { //simple and straightforward
			return true;
		} else {
			if (headCopy.getIsMe().equals("Player 1") && head.getIsMe().equals("rightStore")) {
				return false;
			} 
			if (headCopy.getIsMe().equals("Player 2") && head.getIsMe().equals("leftStore")) {
				return false;
			}
		}
		return true;
	}

	//check who the winner is
	public String checkWinner(Hole leftStore, Hole rightStore) {
		int gemCounterP1 = 0;
		int gemCounterP2 = 0;
		Hole startHole = leftStore;
		//check gem numbers for player 1 and 2
		for (int i = 0; i < 14; i++) {
			if (startHole.getIsMe().equals("Player 1")) {
				gemCounterP1 += startHole.getGemNum();
			} else if (startHole.getIsMe().equals("Player 2")) {
				gemCounterP2 += startHole.getGemNum();
			}
			startHole = startHole.getNext();
		}
		//return the result
		if (gemCounterP1 == 0 || gemCounterP2 == 0) {
			//reset the gem number in 2 stores
			leftStore.setGemNum(leftStore.getGemNum() + gemCounterP2);
			rightStore.setGemNum(rightStore.getGemNum() + gemCounterP1);
			if (leftStore.getGemNum() == rightStore.getGemNum()) {
				return "Draw";
			}
			if (leftStore.getGemNum() > rightStore.getGemNum()) {
				return "Winner: Player 2!";
			} else {
				return "Winner: Player 1!";
			}
		}
		return "Keep Going";
	}

}
