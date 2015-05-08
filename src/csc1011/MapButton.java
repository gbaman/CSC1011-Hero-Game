package csc1011;

import java.awt.List;

import javax.swing.JButton;

public class MapButton extends JButton implements
java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3945925613314547956L;

	
	public String getName() {
		return this.CLocation.getName();
	}

	public void setName(String name) {
		this.CLocation.setName(name);
	}

	public int getxLoc() {
		return this.CLocation.getxLoc();
	}

	public void setxLoc(int xLoc) {
		this.CLocation.setxLoc(xLoc);
	}

	public int getyLoc() {
		return this.CLocation.getyLoc();
	}

	public void setyLoc(int yLoc) {
		this.CLocation.setyLoc(yLoc);
	}
	
	public Crime getCrime() {
		return crime;
	}

	public void setCrime(Crime crime) {
		this.crime = crime;
	}


	private Crime crime;
	private Location CLocation;

	public MapButton(String text, String name, int x, int y) {
		super(text);
		Location Clocation = new Location(x, y, name);
		this.CLocation = Clocation;
		this.setCrime(null);
		
	}

	public Location getCLocation() {
		return CLocation;
	}

	public void setCLocation(Location cLocation) {
		CLocation = cLocation;
	}

}
