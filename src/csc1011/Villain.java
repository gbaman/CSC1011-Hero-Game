package csc1011;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Villain extends Character {
	
	MainMenu m;
	ArrayList<Crime> CrimeList;
	ArrayList<AvailablePlayers> Players;
	AvailablePlayers player;
	public void setCrimeList(ArrayList<Crime> crimeList) {
		this.CrimeList = crimeList;
	}
	
	public Villain(MainMenu m){
		m.panelGame.setVisible(true);
		this.m = m;
		this.InventoryArmor = new ArrayList<Items>();
		this.InventoryGadget = new ArrayList<Items>();
		this.CLocation = new Location(156,143, "City Centre");
		
	}
	
	private ArrayList<Crime> CreateCrimesList(){
		ArrayList<Crime> CrimeList = new ArrayList<Crime>();
		CrimeList.add(new Crime("Robbery",Crime.CrimeSeverityLevel.easy));
		CrimeList.add(new Crime("Murder",Crime.CrimeSeverityLevel.medium));
		CrimeList.add(new Crime("Pickpocket",Crime.CrimeSeverityLevel.difficult));
		
		
		return CrimeList;
	}
	
	@Override
	public void setupDisplay() {
		m.setCharImageGame(m.imgJoke);
		System.out.println(this.Name);
		if (this.Name == null){
			m.setName(m.askName());
		}else{
			m.setName(this.Name);
		}
		m.setBackgroundCrime(true);
		System.out.println(m.lblCharImageGame.getIcon());
		this.CrimeList = CreateCrimesList();
		this.Players = definePlayers();
		m.progressBarStatus.setString("Villain");
		
	}
	@Override
	public ArrayList<Crime> getCrimeList() {
		return this.CrimeList;
	}
	@Override
	public void adjustAction(int value) {
		m.setAction(m.getAction() - value);
		checkAction();
		
	}
	@Override
	public void checkAction() {
		if (m.getAction() < 1){
			JOptionPane.showMessageDialog(null, "You have won!", "Victory!", JOptionPane.INFORMATION_MESSAGE);
			m.hideAllPanels();
			m.panelMenu.setVisible(true);
		}else{
			if (m.getAction() < 20){
				m.progressBarStatus.setString("SuperVillain");
			}
		}
		
	}
	@Override
	public void ResetM(MainMenu a) {
		this.m = a;
	}
	
}
