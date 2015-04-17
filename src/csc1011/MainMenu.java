package csc1011;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

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
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JProgressBar;

import java.awt.Font;

public class MainMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	int player;
	JPanel panelGame;
	JPanel panelMenu;
	JProgressBar progressBarStatus;
	Character c;
	JProgressBar progressBarEnergy;
	JProgressBar progressBarAction;
	JLabel lblCharImageGame;
	JLabel lblCharName;
	ImageIcon imgBat = new ImageIcon(getClass().getResource("/The_Batman.jpg"));
	ImageIcon imgBatSleep = new ImageIcon(getClass().getResource("/The_Batman_Sleeping.jpg"));
	ImageIcon imgJoke = new ImageIcon(getClass().getResource("/Mortal_Kombat_Joker.jpg"));
	int startActionLevel = 60;
	JButton btnSleep;
	JButton btnGenCrime;
	Boolean deductEnergy = true;
	JLabel lblSleeping;
	Timer timer;
	private int sleepCounter;
	
	private int getRandom(int min, int max){
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
	private Boolean getRandom(){
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
	

	public void setEnergy(int value){
		this.progressBarEnergy.setValue(value);
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
		setName(askName());
		
		c.setupDisplay();

	}
	
	public String askName(){
		Boolean notDone = true;
		String name = "";
		while (notDone){
			name = JOptionPane.showInputDialog(null,"Enter a name for your character", "Name", JOptionPane.PLAIN_MESSAGE);
			if (name.length() == 0){
				JOptionPane.showMessageDialog(null, "You must enter a name!", "Failed!", JOptionPane.ERROR_MESSAGE);
			}
			else{
				notDone = false;
			}
		
		}
		return name;
	}

	public Hero CreateHero(){
		Hero menu = new Hero(this);
		return menu;
	}

	public Villain CreateVillain(){
		Villain menu = new Villain(this);
		return menu;
	}

	public void displayEnd(){
		JOptionPane.showMessageDialog(null,"Game over", "You have lost the game.", JOptionPane.WARNING_MESSAGE);
		this.panelMenu.setVisible(true);
		this.panelGame.setVisible(false);
	}

	public void runTest(){
		Random random = new Random();
		System.out.println(random.nextInt(2));
	}
	
	public void startSleep(){
		this.lblSleeping.setEnabled(true);
		this.btnSleep.setEnabled(false);
		this.btnGenCrime.setEnabled(false);
		this.lblCharImageGame.setIcon(imgBatSleep);
		this.deductEnergy = false;
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
			endSleep();
		}else{
			this.sleepCounter = this.sleepCounter - 1;
			this.lblSleeping.setText("Sleeping.. " + this.sleepCounter);
		}
	}
	
	public void endSleep(){
		this.lblSleeping.setText("");
		this.lblCharImageGame.setIcon(imgBat);
		this.deductEnergy = true;
		this.btnSleep.setEnabled(true);
		this.btnGenCrime.setEnabled(true);
		setEnergy(getEnergy() + 20);
	}

	public void GenerateCrime(){
		if (getEnergy() < 20){
			JOptionPane.showMessageDialog(null, "Your energy is below 20, you must sleep!", "Failed!", JOptionPane.ERROR_MESSAGE);
		}
		else{
			Crime TheCrime = c.getRandomCrime(c.getCrimeList());
			int crimeResponse = JOptionPane.showConfirmDialog(null, "A " + TheCrime.name + " is happening at " + TheCrime.CrimeLocation + ". Would you like to fight it?","A crime is being committed!", JOptionPane.YES_NO_OPTION);
			if (crimeResponse == 0){
				int EnergyLoss;
				if (getRandom()){
					EnergyLoss = getRandom(5, 15);
					setEnergy(getEnergy() - EnergyLoss);
					JOptionPane.showMessageDialog(null, "Crime successfully stopped! You lost " + EnergyLoss + " energy!", "Crime stopped!", JOptionPane.INFORMATION_MESSAGE);

				}else
				{	
					EnergyLoss = 20;
					JOptionPane.showMessageDialog(null, "Crime stopping failed! You lost " + EnergyLoss + " energy!", "Failed!", JOptionPane.ERROR_MESSAGE);
					setEnergy(getEnergy() - EnergyLoss);
				}

			}
		}
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

		JPanel panelGameL = new JPanel();
		this.panelGame = panelGameL;
		this.panelMenu = panelMenuL;

		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				runTest();
			}
		});
		btnTest.setBounds(19, 139, 117, 29);
		panelMenuL.add(btnTest);
		contentPane.add(panelGame, "name_1428591074139768000");
		panelGameL.setLayout(null);
		
		JLabel lblSleeping_1 = new JLabel("Hello");
		lblSleeping_1.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblSleeping_1.setBounds(529, 386, 255, 50);
		panelGameL.add(lblSleeping_1);
		this.lblSleeping = lblSleeping_1;
		this.lblSleeping.setText("");

		JLabel lblCharImageGame = new JLabel("");
		lblCharImageGame.setBounds(26, 0, 345, 566);
		panelGameL.add(lblCharImageGame);
		this.lblCharImageGame = lblCharImageGame;

		JButton btnGenCrime = new JButton("Generate crime");
		btnGenCrime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerateCrime();
			}
		});
		btnGenCrime.setBounds(387, 442, 160, 29);
		panelGameL.add(btnGenCrime);
		this.btnGenCrime = btnGenCrime;

		JButton btnSleep = new JButton("Sleep");
		btnSleep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startSleep();
			}
		});
		btnSleep.setBounds(408, 386, 117, 29);
		panelGameL.add(btnSleep);
		this.btnSleep = btnSleep;

		JProgressBar progressBarStatus = new JProgressBar();
		progressBarStatus.setStringPainted(true);
		progressBarStatus.setBounds(561, 46, 146, 20);
		panelGameL.add(progressBarStatus);
		this.progressBarStatus = progressBarStatus;


		JProgressBar progressBarAction = new JProgressBar();
		progressBarAction.setStringPainted(true);
		progressBarAction.setBounds(561, 92, 146, 20);
		panelGameL.add(progressBarAction);

		JProgressBar progressBarEnergy = new JProgressBar();
		progressBarEnergy.setStringPainted(true);
		progressBarEnergy.setBounds(561, 140, 146, 20);
		panelGameL.add(progressBarEnergy);
		this.progressBarEnergy = progressBarEnergy;

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(469, 50, 61, 16);
		panelGameL.add(lblStatus);

		JLabel lblActions = new JLabel("Actions");
		lblActions.setBounds(469, 96, 61, 16);
		panelGameL.add(lblActions);

		JLabel lblEnergy = new JLabel("Energy");
		lblEnergy.setBounds(469, 140, 61, 16);
		panelGameL.add(lblEnergy);
		
		JLabel lblCharName = new JLabel("");
		lblCharName.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblCharName.setBounds(469, 6, 262, 29);
		panelGameL.add(lblCharName);
		this.lblCharName = lblCharName;
		btnHero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMenu.setVisible(false);
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
