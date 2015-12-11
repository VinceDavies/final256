//Vinzent Davies
//Java 256
//10.30.15
//Midterm

package midterm;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Circ extends JPanel {
	private int myXStart = 20;
	private int myYStart = 20;
	private int myWidth = 50;
	private int myHeight = 50;
	private String myBackColor = "Blue";
	private String myForeColor = "Green";
	private String myFillColor = "Yes";

	public Circ() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d= (Graphics2D)g;
		if (myBackColor.equalsIgnoreCase("blue")) {
			this.setBackground(Color.BLUE);
		}
		if (myBackColor.equalsIgnoreCase("green")) {
			this.setBackground(Color.GREEN);
		}
		if (myBackColor.equalsIgnoreCase("yellow")) {
			this.setBackground(Color.YELLOW);
		}
		
		
		if (myFillColor.equalsIgnoreCase("yes")) {
			if(myForeColor.equalsIgnoreCase("blue")){
				this.setForeground(Color.BLUE);
			}
			
			if(myForeColor.equalsIgnoreCase("green")){
				this.setForeground(Color.GREEN);
			}
			g2d.fillOval(myXStart, myYStart, myWidth, myHeight);
		} else {
			g2d.drawOval(myXStart, myYStart, myWidth, myHeight);
		}
	}
	


	public int getMyXStart() {
		return myXStart;
	}

	public void setMyXStart(int myXStart) {
		this.myXStart = myXStart;
		this.repaint();
	}

	public int getMyYStart() {
		return myYStart;
	}

	public void setMyYStart(int myYStart) {
		this.myYStart = myYStart;
		this.repaint();
	}

	public int getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		this.repaint();
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
		this.repaint();
	}

	public String getMyBackClolor() {
		return myBackColor;
	}

	public void setMyBackClolor(String myBackClolor) {
		this.myBackColor = myBackClolor;
		System.out.println(myBackClolor);
		this.repaint();
	}

	public String getMyForeColor() {
		return myForeColor;
	}

	public void setMyForeColor(String myForeColor) {
		this.myForeColor = myForeColor;
		this.repaint();
	}

	public String getMyFillColor() {
		return myFillColor;
	}

	public void setMyFillColor(String myFillColor) {
		this.myFillColor = myFillColor;
		this.repaint();
	}

}

