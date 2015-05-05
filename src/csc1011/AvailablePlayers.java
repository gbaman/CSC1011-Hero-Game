package csc1011;

import javax.swing.ImageIcon;

public class AvailablePlayers implements
java.io.Serializable {

	
	public AvailablePlayers(String name, ImageIcon image, int speed, int health,
			int strength) {
		super();
		this.name = name;
		this.image = image;
		this.speed = speed;
		this.health = health;
		this.strength = strength;
	}
	String name;
	ImageIcon image;
	int speed;
	int health;
	int strength;
}
