package csc1011;

public class Location {

	public Location(int xLoc, int yLoc, String name) {
		super();
		this.xLoc = xLoc;
		this.yLoc = yLoc;
		Name = name;
	}
	private int xLoc;
	private int yLoc;
	private String Name;
	public int getxLoc() {
		return xLoc;
	}
	public void setxLoc(int xLoc) {
		this.xLoc = xLoc;
	}
	public int getyLoc() {
		return yLoc;
	}
	public void setyLoc(int yLoc) {
		this.yLoc = yLoc;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	
}
