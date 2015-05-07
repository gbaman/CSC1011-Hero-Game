package csc1011;

import java.awt.Color;
import java.awt.EventQueue;

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
import java.util.Random;

import javax.swing.JProgressBar;

import java.awt.Font;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
	ImageIcon imgJoke = new ImageIcon(getClass().getResource("/Mortal_Kombat_Joker.jpg"));
	ImageIcon imgBankRobber = new ImageIcon(getClass().getResource("/bank-robber.jpg")); 
	ImageIcon goth1 = new ImageIcon(getClass().getResource("/large/gothback1.jpg"));
	int startActionLevel = 60;
	JButton btnSleep;
	JButton btnGenCrime;
	JButton btnPunch;
	Boolean deductEnergy = true;
	JLabel lblSleeping;
	Timer timer;
	private int sleepCounter;
	
	public Boolean getGameRunning() {
		return GameRunning;
	}
	public void setGameRunning(Boolean gameRunning) {
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
	
	//private ImageIcon resizeImage(ImageIcon icon, JLabel lbl){
		//Image img = icon.getImage(); 
		//BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB); 
		//Graphics g = bi.createGraphics(); 
		
		//icon.paintIcon(null, g, 0,0);
	    //g.dispose();
	    //BufferedImage resizedimage=setSize(bi,lbl.getWidth(), lbl.getHeight());
	    //ImageIcon newIcon=new ImageIcon(resizedimage);
		
		
		//g.drawImage(img, 0, 0, lbl.getWidth(), lbl.getHeight(), null); 
		//g.drawImage(img, 0, 0, WIDTH, HEIGHT, null); 
		//ImageIcon newIcon = new ImageIcon(bi); 
		//return newIcon;
	//}
	
	private ImageIcon resizeImage(ImageIcon icon, JLabel lbl){
	/*	Image img = icon.getImage(); 
		int w = lbl.getWidth();
		int h = lbl.getHeight();
		BufferedImage src = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    int finalw = w;
	    int finalh = h;
	    double factor = 1.0d;
	    if(src.getWidth() > src.getHeight()){
	        factor = ((double)src.getHeight()/(double)src.getWidth());
	        finalh = (int)(finalw * factor);                
	    }else{
	        factor = ((double)src.getWidth()/(double)src.getHeight());
	        finalw = (int)(finalh * factor);
	    }   

	    BufferedImage resizedImg = new BufferedImage(finalw, finalh, BufferedImage.TRANSLUCENT);
	    Graphics2D g2 = resizedImg.createGraphics();
	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(src, 0, 0, finalw, finalh, null);
	    g2.dispose();
	    ImageIcon newIcon=new ImageIcon(resizedImg);
	    return newIcon; */
		return icon;
	}
	
	public Boolean getBackgroundCrime() {
		return BackgroundCrime;
	}
	
	public void setBackgroundCrime(Boolean backgroundCrime) {
		System.out.println("Setting background to " + backgroundCrime);
		BackgroundCrime = backgroundCrime;
	}
	
	public void setEnergy(int value){
		this.progressBarEnergy.setValue(value);
		System.out.println(value);
		System.out.println(this.getEnergy());
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

	public void RunGame(Character a){
		this.c = a;
		this.GameRunning = true;
		this.setBackgroundCrime(false);
		(new Thread(new EnergyCountDown(this))).start();
		(new Thread(new GameBackgroundThread(this))).start();
		c.ResetM(this);
		c.setupDisplay();

	}
	
	public void SaveGame(){
		try {
			FileOutputStream f_out = new FileOutputStream("myobject.data");
			ObjectOutputStream obj_out = new ObjectOutputStream (f_out);
			c.setSaveActionBar(this.getAction());
			c.setSaveEnergyBar(this.getEnergy());
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
				System.out.println(player.getSaveActionBar());
				System.out.println("Hello");
				this.setDeductEnergy(true);
				panelMenu.setVisible(false);
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
			if (name.length() == 0){
				JOptionPane.showMessageDialog(null, "You must enter a name!", "Failed!", JOptionPane.ERROR_MESSAGE);
			}
			else{
				notDone = false;
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
		this.panelMenu.setVisible(true);
		this.panelGame.setVisible(false);
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
	
	public void runTest2(){
		/*this.setEnabled(false);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setEnabled(true); */
		//CustomDialogs.main();
		
		
		
		//CustomDialog.main();
		System.out.println(dialogRun("SelectCharacterDialog"));
		
	}
	
	public String dialogRun(String DialogType){
		String message = null;
		try {
			this.setDeductEnergy(false);
			this.setGameRunning(false);
			
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
		this.setGameRunning(true);
		return message;
	}
	
	public void startSleep(){
		this.setBackgroundCrime(false);
		this.lblSleeping.setEnabled(true);
		this.btnSleep.setEnabled(false);
		this.btnGenCrime.setEnabled(false);
		this.lblCharImageGame.setIcon(imgBatSleep);
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
			int rand = getRandom(1, 11);
			System.out.println("Checking energy in random sleep " + rand);
			if ((rand > 9) && ((this.getEnergy()) > 20)){
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
		setEnergy(getEnergy() + energyAdd);
	}

	public void GenerateCrime(){
		this.setBackgroundCrime(false);
		if (getEnergy() < 20){
			JOptionPane.showMessageDialog(null, "Your energy is below 20, you must sleep!", "Failed!", JOptionPane.ERROR_MESSAGE);
			this.setBackgroundCrime(true);
		}
		else{
			Crime TheCrime = c.getRandomCrime(c.getCrimeList());
			this.setDeductEnergy(false); 
			//int crimeResponse = JOptionPane.showConfirmDialog(null, "A " + TheCrime.name + " is happening at " + TheCrime.CrimeLocation + ". Would you like to fight it?","A crime is being committed!", JOptionPane.YES_NO_OPTION);
			String crimeResponse = this.dialogRun("CrimeDialog");
			if (crimeResponse == "yes"){
				int EnergyLoss;
				int ActionGained = 0;
				int MoneyGained = 0;
				if (getRandom() || getRandom()){
					ActionGained = 10;
					MoneyGained = 50;
					EnergyLoss = getRandom(5, 15);
					setEnergy(getEnergy() - EnergyLoss);
					c.adjustAction(ActionGained);
					JOptionPane.showMessageDialog(null, "Crime successfully stopped! You lost " + EnergyLoss + " energy!", "Crime stopped!", JOptionPane.INFORMATION_MESSAGE);
					this.c.setMoney(this.c.getMoney() + MoneyGained);

				}else
				{	
					EnergyLoss = 20;
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
	}
	
	public Boolean getDeductEnergy() {
		return deductEnergy;
	}
	public void setDeductEnergy(Boolean deductEnergy) {
		this.deductEnergy = deductEnergy;
	}
	public void punchMiniGame(){
		int x = getRandom(0,this.getWidth() - 100);
		int y = getRandom(0,this.getHeight() - 100);
		this.panelMenu.setVisible(false);
		this.panelGame.setVisible(false);
		this.panelFight.setVisible(true);
		this.btnPunch.setLocation(x, y);
		System.out.println(x);
		System.out.println(y);
	}
	/**
	 * Create the frame.
	 */
	
	public MainMenu() {
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
		btnHero.setBounds(19, 43, 117, 29);
		panelMenuL.add(btnHero);

		JButton btnVillian = new JButton("Villian");
		btnVillian.setBounds(19, 86, 117, 29);
		panelMenuL.add(btnVillian);

		final JLabel lblCharImage = new JLabel("");
		lblCharImage.setIcon(imgBat);
		lblCharImage.setBounds(171, 2, 345, 566);
		panelMenuL.add(lblCharImage);
		this.panelMenu = panelMenuL;

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runTest();
			}
		});
		btnTest.setBounds(19, 139, 117, 29);
		panelMenuL.add(btnTest);
		
		JButton btnTest_1 = new JButton("Test2");
		btnTest_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runTest2();
			}
		});
		btnTest_1.setBounds(19, 180, 117, 29);
		panelMenuL.add(btnTest_1);
		
		JPanel panelFight = new JPanel();
		contentPane.add(panelFight, "name_1429893385711104000");
		panelFight.setLayout(null);
		
		JButton btnPunch = new JButton("Punch");
		btnPunch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				punchMiniGame();
			}
		});
		btnPunch.setBounds(507, 210, 69, 29);
		panelFight.add(btnPunch);
		this.panelFight = panelFight;
		this.btnPunch = btnPunch;
																						
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
																								
																								JLabel lblSleeping_1 = new JLabel("Hello");
																								lblSleeping_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
																								lblSleeping_1.setBounds(529, 386, 255, 50);
																								panelGameL.add(lblSleeping_1);
																								this.lblSleeping = lblSleeping_1;
																								this.lblSleeping.setText("");
																								
																										JLabel lblCharImageGame_1 = new JLabel("");
																										lblCharImageGame_1.setBounds(26, 0, 345, 566);
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
																																startSleep();
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
																																										
																																										JLabel BackgroundImg = new JLabel("");
																																										BackgroundImg.setIcon(new ImageIcon(MainMenu.class.getResource("/Large/gothback1.jpg")));
																																										BackgroundImg.setBounds(6, 0, 784, 568);
																																										panelGameL.add(BackgroundImg);
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(false);
				panelGame.setBackground(Color.BLUE);
				panelGame.setVisible(true);
				Hero player = CreateHero();
				RunGame(player);
			}
		});
		btnVillian.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(false);
				panelGame.setVisible(true);
				Villain player = CreateVillain();
				RunGame(player);
			}
		});
		btnHero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				lblCharImage.setIcon(imgBat);
			}
		});
		btnVillian.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblCharImage.setIcon(imgJoke);
			}
		});
	}

}
