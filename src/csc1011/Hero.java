package csc1011;

import java.util.ArrayList;

import javax.swing.JOptionPane;


public class Hero extends Character {
	
	MainMenu m;
	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	AvailablePlayers player;
	public Hero(MainMenu m){
		m.panelGame.setVisible(true);
		this.m = m;
	}
	
	public void ResetM(MainMenu a){
		this.m = a;
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
		System.out.println(this.Name);
		if (this.Name == null){
			m.setName(m.askName());
		}else{
			m.setName(this.Name);
		}
		m.setBackgroundCrime(true);
		System.out.println(m.lblCharImageGame.getIcon());
		//m.setAction(m.startActionLevel);
		this.CrimeList = CreateCrimesList();
		this.Players = definePlayers();
		m.progressBarStatus.setString("Hero");
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
		checkAction();
		
	}

	@Override
	public void checkAction() {
		if (m.getAction() > 99){
			JOptionPane.showMessageDialog(null, "You have won!", "Victory!", JOptionPane.ERROR_MESSAGE);
			m.displayEnd();
		}else{
			if (m.getAction() > 80){
				m.progressBarStatus.setString("Superhero");
			}
		}
		
		
	}
}
