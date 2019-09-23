/*
  *  TemplateHole.java
 *  Name: Lingwei Gu
 *  Date: June.8, 2019
 *  
 *  		Purpose: It's a template for a hole in Mancala as a linked list node
 *			Methods:
 *				TemplateHole() - default constructor
 *				TemplateHole(int gemNum) - overloaded constructor
 *				getNext() - returns Hole
				getOpposite() - returns Hole
				getGemNum() - returns int
				getButton() - returns JButton
				getIsMe() - returns String
				setNext(Hole next) - no return
				setOpposite(Hole opposite) - no return
				setGemNum(int gemNum) - no return
				setButton(JButton button) - no return
				setIsMe(String isMe) - no return
 */

package testing;

import javax.swing.JButton;

public abstract class TemplateHole{
	protected String isMe;
	protected Hole next;
	protected Hole opposite;
	protected int gemNum;
	protected JButton button;
	public TemplateHole() { //default constructor
		this.isMe = "null";
		this.next = null;
		this.opposite = null;
		this.gemNum = 0;
	}
	public TemplateHole(int gemNum) { //overloaded constructor
		this.isMe = "null";
		this.next = null;
		this.opposite = null;
		this.gemNum = gemNum;
	}
	
	
	
	//getters
	public Hole getNext() {
		return this.next;
	}
	public Hole getOpposite() {
		return this.opposite;
	}
	public int getGemNum() {
		return this.gemNum;
	}
	public JButton getButton() {
		return button;
	}
	public String getIsMe() {
		return isMe;
	}
	
	//setters
	public void setNext(Hole next) {
		this.next = next;
	}
	public void setOpposite(Hole opposite) {
		this.opposite = opposite;
	}
	public void setGemNum(int gemNum) {
		this.gemNum = gemNum;
	}
	public void setButton(JButton button) {
		this.button = button;
	}
	public void setIsMe(String isMe) {
		this.isMe = isMe;
	}
}
