package PuissanceP;

import java.awt.Color;

public abstract class Player {
	private String name;
	private Color color;

	public Player(String name, Color color) {
		super();
		this.name = name;
		this.color = color;
	}
	
	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}
	
}
