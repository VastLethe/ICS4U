/*
  *  Hole.java
 *  Name: Lingwei Gu
 *  Date: June.8, 2019
 *  
 *  		Purpose: It's a hole as well as a node
 *			Methods:
 *				Hole() - default constructor
 *				Hole(int gemNum) - overloaded constructor
 */
package testing;

public class Hole extends TemplateHole {
	public Hole() {
		super();
	}
	public Hole(int gemNum) {
		this.gemNum = gemNum;
	}
}
