package csc1011;

import java.util.ArrayList;


public class Hero extends Character {
	
	MainMenu m;
	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	AvailablePlayers player;
	
	public Hero(MainMenu m){
		m.panelGame.setVisible(true);
		this.m = m;
	}
	
	
	
	private ArrayList<Crime> CreateCrimesList(){
		ArrayList<Crime> CrimeList = new ArrayList<Crime>();
		CrimeList.add(new Crime("Robbery", "Bob",Crime.CrimeSeverityLevel.Low, Crime.CrimeLocationsAvailable.Docks));
		CrimeList.add(new Crime("Murder", "Brian",Crime.CrimeSeverityLevel.Medium, Crime.CrimeLocationsAvailable.Suburbs));
		CrimeList.add(new Crime("Pickpocket", "Phil",Crime.CrimeSeverityLevel.Low, Crime.CrimeLocationsAvailable.Downtown));
		
		
		return CrimeList;
	}
	@Override
	public void talk(){
		System.out.println("Talking as a hero!");
		m.panelMenu.setVisible(false);
		m.panelGame.setVisible(true);
		//m.panelMenu.setVisible(true);
	}
	@Override
	public void setupDisplay() {
		m.setCharImageGame(m.imgBat);
		(new Thread(new EnergyCountDown(m))).start();
		//m.setAction(m.startActionLevel);
		this.CrimeList = CreateCrimesList();
		this.Players = definePlayers();
		m.setAction(50);
	}



	@Override
	public ArrayList<Crime> getCrimeList() {
		return this.CrimeList;
	}
	
	@Override
	public ArrayList<AvailablePlayers> definePlayers(){
		ArrayList<AvailablePlayers> Players = new ArrayList<AvailablePlayers>();
		Players.add(new AvailablePlayers("Batman", m.imgBat, 1, 1, 1));
		this.player = Players.get(0);
		return Players;
		
	}



	@Override
	public void adjustAction(int value) {
		m.setAction(m.getAction() + value);
		
	}
}
