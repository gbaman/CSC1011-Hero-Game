package csc1011;

import java.util.ArrayList;


public class Hero extends Character {
	
	MainMenu m;
	ArrayList<Crime> CrimeList;
	
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
		
	}



	@Override
	public ArrayList<Crime> getCrimeList() {
		return this.CrimeList;
	}
}
