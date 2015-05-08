package csc1011;

import java.awt.Color;
import java.awt.EventQueue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JProgressBar;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JRadioButton;

public class MainMenu extends JFrame implements
java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void main1() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
					frame.frame = frame;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	MainMenu frame;
	Boolean GameRunning;
	Boolean BackgroundCrime;
	int player;
	JPanel panelGame;
	JPanel panelMenu;
	JPanel panelFight;
	JProgressBar progressBarStatus;
	Character c;
	JProgressBar progressBarEnergy;
	JProgressBar progressBarAction;
	JLabel lblCharImageGame;
	JLabel lblCharName;
	ImageIcon imgBat = new ImageIcon(getClass().getResource("/The_Batman.jpg"));
	ImageIcon imgBatSleep = new ImageIcon(getClass().getResource("/The_Batman_Sleeping.jpg"));
	ImageIcon imgJoke = new ImageIcon(getClass().getResource("/JokerMed.jpg"));
	ImageIcon imgJokeSmall = new ImageIcon(getClass().getResource("/JokerSmall.jpg"));
	ImageIcon imgBankRobber = new ImageIcon(getClass().getResource("/bank-robber.jpg")); 
	ImageIcon goth1 = new ImageIcon(getClass().getResource("/large/gothback1.jpg"));
	ImageIcon smallBat = new ImageIcon(MainMenu.class.getResource("/Large/The_BatmanSmall.jpg"));
	int startActionLevel = 60;
	JButton btnSleep;
	JButton btnGenCrime;
	JButton btnPunch;
	Boolean deductEnergy = true;
	JLabel lblSleeping;
	Timer timer;
	private int sleepCounter;
	private ArrayList<MapButton> MapButtons;
	private JPanel panelGameSetup;
	private JRadioButton rdbtnHard;
	private JRadioButton rdbtnEasy;
	private boolean easyMode;
	private JButton btnSave;
	private JButton btnOpenshop;
	private int punchLeft;
	private int punchTimerLeft;
	private JLabel lblTimeRemaining;
	private Boolean punchWin;
	private double TravelModifier = 1;
	private double FightEnergyModifier = 1;
	private double ActionMonifier = 1;
	public double getTravelModifier() {
		return TravelModifier;
	}
	public void setTravelModifier(double travelModifier) {
		TravelModifier = travelModifier;
	}
	public double getFightEnergyModifier() {
		return FightEnergyModifier;
	}
	public void setFightEnergyModifier(double fightEnergyModifier) {
		FightEnergyModifier = fightEnergyModifier;
	}
	public double getActionMonifier() {
		return ActionMonifier;
	}
	public void setActionMonifier(double actionMonifier) {
		ActionMonifier = actionMonifier;
	}
	public JLabel getLblTimeRemaining() {
		return lblTimeRemaining;
	}
	public void setLblTimeRemaining(JLabel lblTimeRemaining) {
		this.lblTimeRemaining = lblTimeRemaining;
	}
	private JLabel lblPunchesRemaining;

	public int getPunchTimerLeft() {
		return punchTimerLeft;
	}
	public void setPunchTimerLeft(int punchTimerLeft) {
		this.punchTimerLeft = punchTimerLeft;
	}
	public int getPunchLeft() {
		return punchLeft;
	}
	public void setPunchLeft(int punchLeft) {
		this.punchLeft = punchLeft;
	}
	public boolean isEasyMode() {
		return easyMode;
	}
	public void setEasyMode(boolean easyMode) {
		this.easyMode = easyMode;
	}
	public Boolean getGameRunning() {
		return GameRunning;
	}
	public void setGameRunning(Boolean gameRunning) {
		System.out.println("Setting gameRunning to " + gameRunning);
		GameRunning = gameRunning;
	}

	public int getRandom(int min, int max){
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	public Boolean getRandom(){
		Random random = new Random();
		return random.nextBoolean();
	}

	private ImageIcon resizeImage(ImageIcon icon, JLabel lbl){
		return icon;
	}

	public Boolean getBackgroundCrime() {
		return BackgroundCrime;
	}

	public void setBackgroundCrime(Boolean backgroundCrime) {
		//System.out.println("Setting backgroundCrime to " + backgroundCrime);
		BackgroundCrime = backgroundCrime;
	}

	public void setEnergy(int value){
		this.progressBarEnergy.setValue(value);
		//System.out.println(value);
		//System.out.println(this.getEnergy());
	}

	public int getEnergy(){
		return this.progressBarEnergy.getValue();
	}

	public void setCharImageGame(ImageIcon imgBat2){
		this.lblCharImageGame.setIcon(resizeImage(imgBat2, this.lblCharImageGame));
	}

	public int getAction(){
		return this.progressBarAction.getValue();
	}

	public void setAction(int value){
		this.progressBarAction.setValue(value);
	}

	public void setName(String name){
		this.lblCharName.setText(name);
	}

	public String getName(){
		return this.lblCharName.getText();
	}

	public ArrayList<MapButton> getMapButtons() {
		return MapButtons;
	}
	public void setMapButtons(ArrayList<MapButton> mapButtons) {
		MapButtons = mapButtons;
	}
	public Boolean getDeductEnergy() {
		return deductEnergy;
	}
	public void setDeductEnergy(Boolean deductEnergy) {
		this.deductEnergy = deductEnergy;
	}


	public void RunGame(Character a){
		this.c = a;
		this.GameRunning = true;
		this.setBackgroundCrime(false);
		if (this.rdbtnEasy.isSelected() == true){
			this.easyMode = true;
		}else{
			this.easyMode = false;
		}
		if (this.isEasyMode() == false){
			this.btnGenCrime.setEnabled(false);
		}
		else{
			this.btnGenCrime.setEnabled(true);
		}
		
		(new Thread(new EnergyCountDown(this))).start();
		(new Thread(new GameBackgroundThread(this))).start();
		c.ResetM(this);
		this.GetModifiers();
		DISABLEDEVTEST();
		c.setupDisplay();

	}
	
	public void DISABLEDEVTEST(){
		this.c.setMoney(100000);
	}

	public void SaveGame(){
		try {
			FileOutputStream f_out = new FileOutputStream("myobject.data");
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			c.setSaveActionBar(this.getAction());
			c.setSaveEnergyBar(this.getEnergy());
			c.setSaveMapButtons(this.getMapButtons());
			obj_out.writeObject (c);
			obj_out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void LoadGame(){
		FileInputStream f_in;
		try {
			f_in = new FileInputStream("myobject.data");
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
			Object obj = obj_in.readObject();

			if (obj instanceof Character)
			{
				Character player = (Character) obj;
				obj_in.close();
				this.setAction(player.getSaveActionBar());
				this.setEnergy(player.getSaveEnergyBar());
				this.setMapButtons(player.getSaveMapButtons());
				System.out.println(player.getSaveActionBar());
				System.out.println("Hello");
				System.out.println(this.getMapButtons().get(3).getCrime().getCrimeSeverity());
				this.setDeductEnergy(true);
				this.hideAllPanels();
				panelGame.setVisible(true);
				RunGame(player);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String askName(){
		Boolean notDone = true;
		String name = "";
		this.setDeductEnergy(false);
		while (notDone){
			name = JOptionPane.showInputDialog(null,"Enter a name for your character", "Name", JOptionPane.PLAIN_MESSAGE);
			c.setName(name);
			if (name == null){
				JOptionPane.showMessageDialog(null, "You must enter a name!", "Failed!", JOptionPane.ERROR_MESSAGE);
			}else{


				if (name.length() == 0){
					JOptionPane.showMessageDialog(null, "You must enter a name!", "Failed!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					notDone = false;
				}
			}

		}
		this.setDeductEnergy(true);
		return name;
	}

	public Hero CreateHero(){
		Hero menu = new Hero(this);
		this.setEnergy(100);
		this.setAction(50);
		return menu;
	}

	public Villain CreateVillain(){
		Villain menu = new Villain(this);
		this.setEnergy(100);
		this.setAction(50);
		return menu;
	}

	public void displayEnd(){
		JOptionPane.showMessageDialog(null,"Game over", "You have lost the game.", JOptionPane.WARNING_MESSAGE);
		this.hideAllPanels();
		this.panelMenu.setVisible(true);
	}

	public void runTest(){
		//SaveGame();
		LoadGame();
		//punchMiniGame();
		//System.out.println(this.panelFight.getWidth());
		///System.out.println(this.panelFight.getHeight());
		/*JOptionPane.showInputDialog(null, "Please choose a name", "Example 1",
		        JOptionPane.QUESTION_MESSAGE, null, new Object[] { "Amanda",
		            "Colin", "Don", "Fred", "Gordon", "Janet", "Jay",
		            "Joe", "Judie", "Kerstin", "Lotus", "Maciek", "Mark",
		            "Mike", "Mulhern", "Oliver", "Peter", "Quaxo", "Rita",
		            "Sandro", "Tim", "Will" }, "Joe");
		 */
	}

	public String dialogRun(String DialogType){
		String message = null;
		try {
			this.setDeductEnergy(false);
			this.setBackgroundCrime(false);

			CustomDialog dialog = new CustomDialog(this);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//CustomDialog.creatething(this);
			switch (DialogType) {
			case "CrimeDialog" : message = dialog.CrimeDialog(this); break;
			case "SelectCharacterDialog" : message = dialog.SelectCharacterDialog(this);
			case "OpenShopDialog" : message = dialog.DisplayShopList(this); break;
			}
			System.out.println(message);


		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setDeductEnergy(true);
		this.setBackgroundCrime(true);
		return message;
	}

	public void startNewSleep(){
		this.btnSleep.setText("Awake");
		this.btnSave.setEnabled(false);
		this.btnGenCrime.setEnabled(false);
		this.btnOpenshop.setEnabled(false);
		this.setDeductEnergy(false);
		
	}
	
	public void endNewSleep(){
		this.setDeductEnergy(true);
		this.btnSleep.setText("Sleep");
		this.btnGenCrime.setEnabled(true);
		this.btnSave.setEnabled(true);
		this.btnOpenshop.setEnabled(true);
		if (this.isEasyMode()){
			this.btnGenCrime.setEnabled(true);
		}
	}
	
	
	public void startSleep(){
		this.setBackgroundCrime(false);
		this.lblSleeping.setEnabled(true);
		this.btnSleep.setEnabled(false);
		this.btnGenCrime.setEnabled(false);
		this.lblCharImageGame.setIcon(imgBatSleep);
		this.btnSave.setEnabled(false);
		this.setDeductEnergy(false); 
		
		this.sleepCounter = 5;
		this.lblSleeping.setText("Sleeping.. " + this.sleepCounter);
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { 
				checkSleep();
			}
		});
		this.timer = timer;
		timer.setRepeats(true);
		timer.start();	
	}

	public void checkSleep(){
		if (this.sleepCounter == 0){
			this.timer.stop();
			endSleep(20);
		}else{
			int rand = getRandom(1, 21);
			System.out.println("Checking energy in random sleep " + rand);
			if ((rand > 19) && ((this.getEnergy()) > 20)){
				this.timer.stop();
				GenerateCrime();
				endSleep((20 - (this.sleepCounter * 5)));

			}else{
				this.sleepCounter = this.sleepCounter - 1;
				this.lblSleeping.setText("Sleeping.. " + this.sleepCounter);
			}
		}
	}

	public void endSleep(int energyAdd){
		this.lblSleeping.setText("");
		this.lblCharImageGame.setIcon(imgBat);
		this.setDeductEnergy(true);
		this.setBackgroundCrime(true);
		this.btnSleep.setEnabled(true);
		this.btnGenCrime.setEnabled(true);
		this.btnSave.setEnabled(true);
		setEnergy(getEnergy() + energyAdd);
	}

	public void CrimeDialog(){
		this.setDeductEnergy(false); 
		this.setBackgroundCrime(false);
		String crimeResponse = this.dialogRun("CrimeDialog");
		this.endNewSleep();
		if (crimeResponse == "yes"){
			int EnergyLoss;
			int ActionGained = 0;
			int MoneyGained = 0;
			if (getRandom() || getRandom()){
				ActionGained = 10;
				MoneyGained = 50;
				EnergyLoss = getRandom(5, 15);
				EnergyLoss = (int) (EnergyLoss * this.getFightEnergyModifier());
				setEnergy(getEnergy() - EnergyLoss);
				c.adjustAction(ActionGained);
				JOptionPane.showMessageDialog(null, "Crime successfully stopped! You lost " + EnergyLoss + " energy!", "Crime stopped!", JOptionPane.INFORMATION_MESSAGE);
				this.c.setMoney(this.c.getMoney() + MoneyGained);

			}else
			{	
				EnergyLoss = (int) 20;// * this.FightEnergyModifier ;
				JOptionPane.showMessageDialog(null, "Crime stopping failed! You lost " + EnergyLoss + " energy!", "Failed!", JOptionPane.ERROR_MESSAGE);
				setEnergy(getEnergy() - EnergyLoss);
				c.adjustAction(-5);
			}

		}else{
			c.adjustAction(-5);
		}

		this.setDeductEnergy(true); 
		this.setBackgroundCrime(true);
	}

	public void GenerateCrime(){
		this.setBackgroundCrime(false);
		if (getEnergy() < 20){
			//JOptionPane.showMessageDialog(null, "Your energy is below 20, you must sleep!", "Failed!", JOptionPane.ERROR_MESSAGE);
			this.setBackgroundCrime(true);
		}
		else{
			this.CrimeDialog();
		}
		this.setDeductEnergy(true); 
		this.setBackgroundCrime(true);
	}


	public void MapCrime(){
		int ranIndex = this.getRandom(0,  this.getMapButtons().size());
		if (this.getMapButtons().get(ranIndex).getCrime() == null){
			ArrayList<Crime> CL = this.c.getCrimeList();    //Get the list of crimes
			int ranIndex2 = this.getRandom(0,  CL.size());  //Generate a random number for selecting a crime
			MapButton updatedZone = (this.getMapButtons().get(ranIndex));
			Crime IndividualCrime = CL.get(ranIndex2);
			IndividualCrime.setCrimeExpire(20);
			updatedZone.setCrime(IndividualCrime);
			ArrayList<MapButton> MB = this.getMapButtons();
			MB.set((ranIndex), updatedZone);
			this.setMapButtons(MB); 
			playDing();

		}

	}
	
	private void punchMiniGameEnd(Boolean win){
		this.timer.stop();
		if (win){
			
			System.out.println("You win");
			this.punchWin = true;
		}else{
			System.out.println("You loose");
			this.punchWin = false;
		}
		
	}
	
	private Boolean punchMiniGameSetup(int punches){
		this.punchWin = null;
		this.setPunchLeft(punches);
		this.setPunchTimerLeft(10);
		this.punchMiniGameBackground();
		this.hideAllPanels();
		this.panelFight.setVisible(true);
		punchMiniGame();
		/*while (this.punchWin == null){
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		return this.punchWin;
	}
	

	public void punchMiniGame(){
		
		int x = getRandom(0,this.getWidth() - 300);
		int y = getRandom(0,this.getHeight() - 100);
		this.btnPunch.setLocation(x, y);
		System.out.println(x);
		System.out.println(y);
		punchMiniGameUpdate();
	}
	
	private void punchMiniGameUpdate(){
		this.setPunchLeft(this.getPunchLeft() - 1);
		this.lblPunchesRemaining.setText("Punches reamining - " + this.getPunchLeft());
		if (this.getPunchLeft() < 1){
			punchMiniGameEnd(true);
		}
	}
	
	private void punchMiniGameAdjust(){
		this.setPunchTimerLeft(this.getPunchTimerLeft() - 1);
		this.lblTimeRemaining.setText(""+this.getPunchTimerLeft());
		if (this.getPunchTimerLeft() < 1){
			punchMiniGameEnd(false);
		}
	}
	
	public void punchMiniGameBackground(){
		Timer timer = new Timer(1000, new ActionListener() {

			@Override
		    public void actionPerformed(ActionEvent arg0) { 
				punchMiniGameAdjust();
				System.out.println("Timer hit!");
			}
		});
		this.timer = timer;
		timer.setRepeats(true);
		timer.start();	
	}
	
	public void GetModifiers(){
		ArrayList<Items> Gadgets = this.c.InventoryGadget;
		for (int count = 0; count < Gadgets.size(); count++) {
			switch (Gadgets.get(count).getModifierText()){
			case "Travel costs x" : 
				if (Gadgets.get(count).getModifier() < this.getTravelModifier()){
					this.setTravelModifier(Gadgets.get(count).getModifier());
				} break;
			case "Crime fight energy cost x" : 
				if (Gadgets.get(count).getModifier() < this.getFightEnergyModifier()){
					this.setFightEnergyModifier(Gadgets.get(count).getModifier());
				} break;
			case "Action generated x" :
				if (Gadgets.get(count).getModifier() > this.getActionMonifier()){
					this.setActionMonifier(Gadgets.get(count).getModifier());
				} break;
			
			}
			
		}
		System.out.println(this.getTravelModifier() +" " + this.getFightEnergyModifier() + " " + this.getActionMonifier());
		
	}

	public void UpdateMap(){
		for (int count = 0; count < MapButtons.size(); count++) {
			MapButton current = MapButtons.get(count);
			if (current.getCLocation() == this.c.getCLocation()){
				current.setText("X");
				if  (current.getCrime() != null){
					GenerateCrime();
					current.setCrime(null);

				}
			}else{
				if (current.getCrime() == null){
					current.setText("");
				}else{
					System.out.println("Non null map zone at " + current.getName() + " " + current.getCrime().getCrimeExpire());
					Crime z = current.getCrime();
					int x = z.getCrimeExpire();		
					current.setText("" + x);
					int NumToDisplay = current.getCrime().getCrimeExpire() - 1;
					current.getCrime().setCrimeExpire(NumToDisplay);
					if (NumToDisplay <1){
						current.setCrime(null);
						c.adjustAction(-5);
					}
				}
			}
		}
	}

	public void MoveCharacter(MapButton b){
		int pxLoc = this.c.CLocation.getxLoc();
		int pyLoc = this.c.CLocation.getxLoc();
		int nxLoc = b.getxLoc();
		int nyLoc = b.getyLoc();
		int distance = (int)(Math.sqrt((pxLoc-nxLoc)*(pxLoc-nxLoc) + (pyLoc-nyLoc)*(pyLoc-nyLoc)));
		System.out.println("Distance is "+distance/10);
		this.setBackgroundCrime(false);
		this.setDeductEnergy(false);
		this.endNewSleep();
		int neededEnergy = (int)((distance/10) * this.getTravelModifier());
		int yesNo = JOptionPane.showConfirmDialog(null, "Are you sure you would like to move zone to " + b.getName()+ "? It will take " + neededEnergy + " energy." , "Move zone", JOptionPane.YES_NO_OPTION);
		if (yesNo == JOptionPane.YES_OPTION){
			if (this.getEnergy() < distance/10){
				JOptionPane.showMessageDialog(null, "Unable to move zone, you need  " + (this.getEnergy() - neededEnergy) + " more energy.", "Failed!", JOptionPane.ERROR_MESSAGE);
			} else{
				this.setEnergy(this.getEnergy() - distance/10);
				this.c.setLocation(b.getCLocation());
			}
		}
		//UpdateMap();

		this.setDeductEnergy(true);
		this.setBackgroundCrime(true);
	}

	private void CreateMapButtons(){
		int xOff = 21;
		int yOff = 300;
		ArrayList<MapButton> MapButtons = new ArrayList<MapButton>();
		MapButtons.add(new MapButton("","Docks", 245,22 ));
		MapButtons.add(new MapButton("","Odyssey", 184,130 ));
		MapButtons.add(new MapButton("","City Centre", 156,143 ));
		MapButtons.add(new MapButton("","Airport", 301,61 ));
		MapButtons.add(new MapButton("","University", 136,213 ));
		this.MapButtons = MapButtons;

		for (int count = 0; count < MapButtons.size(); count++) {
			MapButtons.get(count).setBackground(Color.GREEN);
			MapButtons.get(count).setBounds(MapButtons.get(count).getxLoc() + xOff, MapButtons.get(count).getyLoc() + yOff, 20, 20 );
			this.panelGame.add(MapButtons.get(count));

			MapButtons.get(count).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("Hello world");
					MapButton button = (MapButton)e.getSource();
					MoveCharacter(button);
				}
			});
		}
	}
	
	private void playDing(){
		try{
		    AudioInputStream audioInputStream =
		        AudioSystem.getAudioInputStream(
		            this.getClass().getResource("/Ding3.aiff"));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    //System.out.println("Playing Music!");
		    clip.start();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	private void playMusic(){
		try{
		    AudioInputStream audioInputStream =
		        AudioSystem.getAudioInputStream(
		            this.getClass().getResource("/DramaticMusic5.aiff"));
		    Clip clip = AudioSystem.getClip();
		    clip.open(audioInputStream);
		    System.out.println("Playing Music!");
		    clip.start();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public void hideAllPanels(){
		this.panelFight.setVisible(false);
		this.panelGame.setVisible(false);
		this.panelGameSetup.setVisible(false);
		this.panelMenu.setVisible(false);
	}
	/**
	 * Create the frame.
	 */

	public MainMenu() {
		this.playMusic();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));

		JPanel panelMenuL = new JPanel();
		contentPane.add(panelMenuL, "name_1428591072336700000");
		panelMenuL.setLayout(null);

		JButton btnHero = new JButton("Hero");
		btnHero.setBounds(32, 98, 117, 29);
		panelMenuL.add(btnHero);

		JButton btnVillian = new JButton("Villian");
		btnVillian.setBounds(32, 139, 117, 29);
		panelMenuL.add(btnVillian);

		final JLabel lblCharImage = new JLabel("");
		lblCharImage.setIcon(smallBat);
		lblCharImage.setBounds(445, 42, 345, 198);
		panelMenuL.add(lblCharImage);
		this.panelMenu = panelMenuL;

		JButton btnTest = new JButton("Load game");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runTest();
			}
		});
		btnTest.setBounds(304, 201, 117, 29);
		panelMenuL.add(btnTest);
		
		JRadioButton rdbtnEasy = new JRadioButton("Easy", true);
		rdbtnEasy.setBounds(232, 98, 141, 23);
		panelMenuL.add(rdbtnEasy);
		
		JRadioButton rdbtnHard = new JRadioButton("Hard", false);
		rdbtnHard.setBounds(232, 140, 141, 23);
		panelMenuL.add(rdbtnHard);
		ButtonGroup b = new ButtonGroup();
		b.add(rdbtnEasy);
		b.add(rdbtnHard);
		this.rdbtnEasy = rdbtnEasy;
		this.rdbtnHard = rdbtnHard;

		
		JPanel panelFight = new JPanel();
		contentPane.add(panelFight, "name_1429893385711104000");
		panelFight.setLayout(null);

		JButton btnPunch = new JButton("Punch");
		btnPunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				punchMiniGame();
			}
		});
		btnPunch.setBounds(509, 196, 67, 43);
		panelFight.add(btnPunch);
		this.panelFight = panelFight;
		this.btnPunch = btnPunch;
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainMenu.class.getResource("/Criminal.jpg")));
		lblNewLabel.setBounds(16, 17, 581, 545);
		panelFight.add(lblNewLabel);
		
		JLabel lblPunchHim = new JLabel("Punch him!");
		lblPunchHim.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		lblPunchHim.setBounds(619, 29, 141, 53);
		panelFight.add(lblPunchHim);
		
		JLabel lblTimeRemaining = new JLabel("Time remaining - ");
		lblTimeRemaining.setBounds(619, 93, 141, 16);
		panelFight.add(lblTimeRemaining);
		this.lblTimeRemaining = lblTimeRemaining;
		
		JLabel lblPunchesRemaining = new JLabel("Punches remaining - ");
		lblPunchesRemaining.setBounds(609, 126, 151, 16);
		panelFight.add(lblPunchesRemaining);
		this.lblPunchesRemaining = lblPunchesRemaining;

		JPanel panelGameL = new JPanel();
		contentPane.add(panelGameL, "name_226128012451582");
		this.panelGame = panelGameL;
		panelGameL.setLayout(null);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SaveGame();
			}
		});
		btnSave.setBounds(408, 490, 117, 29);
		panelGameL.add(btnSave);
		this.btnSave = btnSave;
		CreateMapButtons();


		JLabel lblSleeping_1 = new JLabel("Hello");
		lblSleeping_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblSleeping_1.setBounds(482, 249, 255, 50);
		panelGameL.add(lblSleeping_1);
		this.lblSleeping = lblSleeping_1;
		this.lblSleeping.setText("");

		JLabel lblCharImageGame_1 = new JLabel("");
		lblCharImageGame_1.setBounds(21, 6, 345, 293);
		panelGameL.add(lblCharImageGame_1);
		this.lblCharImageGame = lblCharImageGame_1;

		JButton btnGenCrime_1 = new JButton("Generate crime");
		btnGenCrime_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateCrime();
			}
		});
		btnGenCrime_1.setBounds(387, 442, 160, 29);
		panelGameL.add(btnGenCrime_1);
		this.btnGenCrime = btnGenCrime_1;

		JButton btnSleep_1 = new JButton("Sleep");
		btnSleep_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnSleep.getText() == "Awake"){
					endNewSleep();
				}else{
					startNewSleep();
				}
				//startSleep();
			}
		});
		btnSleep_1.setBounds(408, 381, 117, 29);
		panelGameL.add(btnSleep_1);
		this.btnSleep = btnSleep_1;

		JButton btnOpenshop = new JButton("Open Shop");
		btnOpenshop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(dialogRun("SelectCharacterDialog"));
			}
		});
		btnOpenshop.setBounds(418, 531, 94, 27);
		panelGameL.add(btnOpenshop);
		this.btnOpenshop = btnOpenshop;

		JProgressBar progressBarStatus_1 = new JProgressBar();
		progressBarStatus_1.setStringPainted(true);
		progressBarStatus_1.setBounds(561, 46, 146, 20);
		panelGameL.add(progressBarStatus_1);
		this.progressBarStatus = progressBarStatus_1;


		JProgressBar progressBarAction_1 = new JProgressBar();
		progressBarAction_1.setStringPainted(true);
		progressBarAction_1.setBounds(561, 92, 146, 20);
		panelGameL.add(progressBarAction_1);
		this.progressBarAction = progressBarAction_1;

		JProgressBar progressBarEnergy_1 = new JProgressBar();
		progressBarEnergy_1.setStringPainted(true);
		progressBarEnergy_1.setBounds(561, 140, 146, 20);
		panelGameL.add(progressBarEnergy_1);
		this.progressBarEnergy = progressBarEnergy_1;

		JLabel lblMap = new JLabel("");
		lblMap.setIcon(new ImageIcon(MainMenu.class.getResource("/Belfast-map.png")));
		lblMap.setBounds(21, 300, 350, 261);
		panelGameL.add(lblMap);

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(469, 50, 61, 16);
		panelGameL.add(lblStatus);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(469, 96, 61, 16);
		panelGameL.add(lblActions);

		JLabel lblEnergy = new JLabel("Energy");
		lblEnergy.setBounds(469, 140, 61, 16);
		panelGameL.add(lblEnergy);

		JLabel lblCharName_1 = new JLabel("");
		lblCharName_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblCharName_1.setBounds(469, 6, 262, 29);
		panelGameL.add(lblCharName_1);
		this.lblCharName = lblCharName_1;
		
		JLabel lblBehind = new JLabel("");
		lblBehind.setIcon(new ImageIcon(MainMenu.class.getResource("/Large/BackRec.jpg")));
		lblBehind.setBounds(452, 6, 318, 173);
		panelGameL.add(lblBehind);

		JLabel BackgroundImg = new JLabel("");
		BackgroundImg.setIcon(new ImageIcon(MainMenu.class.getResource("/Large/gothback1.jpg")));
		BackgroundImg.setBounds(6, 0, 784, 568);
		panelGameL.add(BackgroundImg);
														
														JLabel lblBackground2 = new JLabel("");
														lblBackground2.setIcon(new ImageIcon(MainMenu.class.getResource("/Large/gothback1.jpg")));
														lblBackground2.setBounds(0, 242, 784, 326);
														panelMenuL.add(lblBackground2);
														
														JLabel lblCsc = new JLabel("CSC1009 Superhero Assesment");
														lblCsc.setFont(new Font("Lucida Grande", Font.BOLD, 20));
														lblCsc.setBounds(192, 6, 325, 25);
														panelMenuL.add(lblCsc);
														
														JLabel lblDofficulty = new JLabel("Difficulty");
														lblDofficulty.setFont(new Font("Lucida Grande", Font.BOLD, 16));
														lblDofficulty.setBounds(232, 70, 85, 16);
														panelMenuL.add(lblDofficulty);
														
														JLabel lblClass = new JLabel("Class");
														lblClass.setFont(new Font("Lucida Grande", Font.BOLD, 16));
														lblClass.setBounds(51, 71, 85, 16);
														panelMenuL.add(lblClass);
														btnHero.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																hideAllPanels();
																panelGame.setBackground(Color.BLUE);
																panelGame.setVisible(true);
																Hero player = CreateHero();
																RunGame(player);
															}
														});
														btnVillian.addActionListener(new ActionListener() {
															public void actionPerformed(ActionEvent e) {
																hideAllPanels();
																panelGame.setVisible(true);
																Villain player = CreateVillain();
																RunGame(player);
															}
														});
														btnHero.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseEntered(MouseEvent arg0) {
																lblCharImage.setIcon(smallBat);
															}
														});
														btnVillian.addMouseListener(new MouseAdapter() {
															@Override
															public void mouseEntered(MouseEvent e) {
																lblCharImage.setIcon(imgJokeSmall);
															}
														});

		JPanel panelGameSetup = new JPanel();
		contentPane.add(panelGameSetup, "name_307894030242621");
		this.panelGameSetup = panelGameSetup;
		/*this.hideAllPanels();
		this.panelMenu.setVisible(true);
		progressBarAction.setStringPainted(true);
		progressBarAction.setForeground(Color.blue);
		progressBarAction.setString("10%");*/
	}
	public JPanel getPanelGameSetup() {
		return panelGameSetup;
	}
	public void setPanelGameSetup(JPanel panelGameSetup) {
		this.panelGameSetup = panelGameSetup;
	}
}
