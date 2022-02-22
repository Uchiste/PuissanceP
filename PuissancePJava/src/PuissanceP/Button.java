package PuissanceP;

import javax.swing.*;

public class Button{
	private JButton jbutton;
	private int x,y;
	public Button(int x, int y) {
		jbutton=new JButton();
		this.x = x;
		this.y = y;
	}
	public JButton getButton() {
		return jbutton;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
}
