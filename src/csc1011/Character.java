package csc1011;

import java.util.ArrayList;
import java.util.Random;


public abstract class Character implements
java.io.Serializable {

	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	String Name = null;
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	int SaveActionBar;
	int SaveEnergyBar;
	
	
	
	
	
	
	public int getSaveActionBar() {
		return SaveActionBar;
	}

	public void setSaveActionBar(int saveActionBar) {
		SaveActionBar = saveActionBar;
	}

	public int getSaveEnergyBar() {
		return SaveEnergyBar;
	}

	public void setSaveEnergyBar(int saveEnergyBar) {
		SaveEnergyBar = saveEnergyBar;
	}
	
	public abstract void ResetM(MainMenu a);

	public abstract void talk();
	
	public abstract void setupDisplay();
	
	public abstract ArrayList<Crime> getCrimeList();
	
	public abstract void adjustAction(int value);
	
	public abstract void checkAction();

	public Crime getRandomCrime(ArrayList<Crime> CrimeList){
		Random random = new Random();
		int index = random.nextInt(CrimeList.size());
		return CrimeList.get(index);
		
	}

	public ArrayList<AvailablePlayers> definePlayers() {
		return null;
	}
	
}
