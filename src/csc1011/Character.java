package csc1011;

import java.util.ArrayList;
import java.util.Random;


public abstract class Character {

	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	String Name;
	
	public abstract void talk();
	
	public abstract void setupDisplay();
	
	public abstract ArrayList<Crime> getCrimeList();
	
	public abstract void adjustAction(int value);

	public Crime getRandomCrime(ArrayList<Crime> CrimeList){
		Random random = new Random();
		int index = random.nextInt(CrimeList.size());
		return CrimeList.get(index);
		
	}

	public ArrayList<AvailablePlayers> definePlayers() {
		return null;
	}
	
}
