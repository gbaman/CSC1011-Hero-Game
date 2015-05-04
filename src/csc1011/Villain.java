package csc1011;

import java.util.ArrayList;

public class Villain extends Character {
	
	MainMenu m;
	
	public Villain(MainMenu m){
		this.m = m;
		
	}
	public void talk(){
		System.out.println("Talking as a Villain!");
	}
	@Override
	public void setupDisplay() {
		m.setCharImageGame(m.imgJoke);
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Crime> getCrimeList() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void adjustAction(int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void checkAction() {
		// TODO Auto-generated method stub
		
	}
	
}
