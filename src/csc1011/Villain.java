package csc1011;

import java.util.ArrayList;

public class Villain extends Character {
	public Villain(MainMenu m){
		
	}
	public void talk(){
		System.out.println("Talking as a Villain!");
	}
	@Override
	public void setupDisplay() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Crime> getCrimeList() {
		// TODO Auto-generated method stub
		return null;
	}
}
