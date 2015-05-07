package csc1011;

import java.util.ArrayList;
import java.util.Random;


public abstract class Character implements
java.io.Serializable {

	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	ArrayList<Items> InventoryArmor;
	int money = 0;
	public int getMoney() {
		return this.money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public ArrayList<AvailablePlayers> getPlayers() {
		return Players;
	}

	public void setPlayers(ArrayList<AvailablePlayers> players) {
		Players = players;
	}

	public ArrayList<Items> getInventoryArmor() {
		return InventoryArmor;
	}

	public void setInventoryArmor(ArrayList<Items> inventoryArmor) {
		InventoryArmor = inventoryArmor;
	}

	public ArrayList<Items> getInventoryGadget() {
		return InventoryGadget;
	}

	public void setInventoryGadget(ArrayList<Items> inventoryGadget) {
		InventoryGadget = inventoryGadget;
	}

	ArrayList<Items> InventoryGadget;
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
