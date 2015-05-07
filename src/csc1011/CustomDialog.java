package csc1011;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;


public class CustomDialog extends JDialog {

	JLabel lblCrimeIcon;
	JLabel lblACrimeIs;
	JPanel PanelCrimeGen;
	MainMenu m;
	String message = null;
	Thread DialogCloser;
	JList CharacterList;
	JPanel PanelShop;
	JPanel PanelShopOverallA;
	private JList<Items> FoodList;
	JPanel PanelShopGadget;
	JPanel PanelShopArmor;
	private JList<Items> ArmorList;
	private JList<Items> GadgetList;
	private DefaultListModel<Items> FoodListModel;
	private DefaultListModel<Items> ArmorListModel;
	private DefaultListModel<Items> GadgetListModel;
	private JLabel labelMoney;


	public String getLabelMoney() {
		return labelMoney.getText();
	}

	public void setLabelMoney(int labelMoney) {
		this.labelMoney.setText("£" + labelMoney);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String CrimeDialog(MainMenu m){
		this.setSize(350,300);
		System.out.println("Crime Dialog!");
		ImageIcon imgCrime = new ImageIcon(getClass().getResource("/Crime-Pix.jpg"));
		this.lblCrimeIcon.setIcon(imgCrime);
		this.setTitle("Crime happening!");
		Thread DialogCloser = new Thread(new DialogClose(this, 5, "<html>\nA crime is being commited at the docks! <br>\nWould you like to fight it?"));
		DialogCloser.start();
		this.DialogCloser = DialogCloser;
		//this.PanelCrimeGen.setVisible(true);
		this.setVisible(true);
		return this.message;
	}

	public void DisposeDialog(Boolean interupt){
		if (interupt){
			this.DialogCloser.interrupt();
		}
		dispose();
		System.out.println("Disposed!");
	}

	public String SelectCharacterDialog(MainMenu m){

		return "";
	}

	private JList setupJList(JList l){
		l.setCellRenderer(new ShopItemRenderer());
		l.setVisibleRowCount(1);

		return CharacterList;

	}

	private DefaultListModel<Items> checkItemsNotBought(DefaultListModel<Items> a){
		
		for (int count = 0; count < this.m.c.InventoryArmor.size(); count++) {
			System.out.println("I own " + this.m.c.InventoryArmor.get(count).getName());
		}
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		for (int count = 0; count < a.size(); count++) {
			Items toCheck = a.getElementAt(count);
			//System.out.println("Are these the same? " + toCheck.getName() + " " + this.m.c.InventoryArmor.) ;
			Boolean DoesContain = this.m.c.InventoryArmor.contains(toCheck);
			System.out.println(DoesContain + " " + toCheck.getName());
			
			for (int count2 = 0; count2 < this.m.c.InventoryArmor.size(); count2++) {
				if (this.m.c.InventoryArmor.get(count2).getName() == toCheck.getName() ){
					System.out.println("I am going to remove " + this.m.c.InventoryArmor.get(count).getName());
					toRemove.add(count);
				}	
			}
			
		}
		if (toRemove.size() > 0){
			for (int count = toRemove.size(); count > 0; count--) {
				a.remove(toRemove.get(count-1));
				//System.out.println("Removing " + a.getElementAt(toRemove.get(count)));
			
			}
		}
		System.out.println("a is long " + a.getSize());
		return a;

	}

	public String DisplayShopList(MainMenu m){
		this.m = m;
		DefaultListModel<Items> FoodListModel = new DefaultListModel<>();
		// (Name, filename, price, modifier)
		//.addElement(new Food("", "", 20, 10));
		FoodListModel.addElement(new Food("Apple", "Apple", 20, 10));
		FoodListModel.addElement(new Food("Toast", "Toast-Bread", 50, 20));

		DefaultListModel<Items> ArmorListModel = new DefaultListModel<>();
		ArmorListModel.addElement(new Clothing("Suit", "Suit", 20, 10));
		
		DefaultListModel<Items> GadgetListModel = new DefaultListModel<>();
		
		ArmorListModel = checkItemsNotBought(ArmorListModel);
		JList<Items> FoodList = new JList<>(FoodListModel);
		JList<Items> ArmorList = new JList<>(checkItemsNotBought(ArmorListModel));
		JList<Items> GadgetList = new JList<>(checkItemsNotBought(GadgetListModel));
		FoodList.setCellRenderer(new ShopItemRenderer());
		ArmorList.setCellRenderer(new ShopItemRenderer());
		GadgetList.setCellRenderer(new ShopItemRenderer());
		this.FoodListModel = FoodListModel;
		this.ArmorListModel = ArmorListModel;
		this.GadgetListModel = GadgetListModel;
		this.FoodList = FoodList;
		this.ArmorList = ArmorList;
		this.GadgetList = GadgetList;
		//FoodList.setVisibleRowCount(1);
		JScrollPane FoodScrollPane = new JScrollPane();
		JScrollPane ArmorScrollPane = new JScrollPane();
		JScrollPane GadgetScrollPane = new JScrollPane();
		FoodScrollPane.setViewportView(FoodList);
		ArmorScrollPane.setViewportView(ArmorList);
		GadgetScrollPane.setViewportView(GadgetList);
		//FoodScrollPane.setMinimumSize(new Dimension(100, 50));
		FoodScrollPane.setBounds(0, 0, this.PanelShop.getWidth(), this.PanelShop.getHeight());
		ArmorScrollPane.setBounds(0, 0, this.PanelShopArmor.getWidth(), this.PanelShopArmor.getHeight());
		GadgetScrollPane.setBounds(0, 0, this.PanelShop.getWidth(), this.PanelShop.getHeight());




		this.PanelShop.add(FoodScrollPane);
		this.PanelShopArmor.add(ArmorScrollPane);
		this.PanelShopGadget.add(GadgetScrollPane);
		this.PanelCrimeGen.setVisible(false);
		this.PanelShop.setVisible(true);
		this.PanelShopArmor.setVisible(true);
		this.PanelShopGadget.setVisible(true);
		this.PanelShopOverallA.setVisible(true);
		this.setVisible(true);
		return "Hi";
	}

	private Boolean CanAfford(Items a){
		int cost = a.getPrice();
		int money = this.m.c.getMoney();
		if ((money - cost) > 0){
			m.c.setMoney(money - cost);
			return true;
		}else{
			int missing = (money - cost) * -1;
			JOptionPane.showMessageDialog(null, "You don't have enough money to purchase that item. You are short by £" + missing, "Not enough money", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}

	private void BuyItem(String type){
		System.out.println("hi");
		//System.out.println(FoodList.getSelectedIndex());
		//System.out.println(toBuy.getPrice());
		//System.out.println(toBuy.getPrice());
		switch (type) {
		case "Armor" :{
			Items toBuy = ArmorList.getSelectedValue();
			if (this.CanAfford(toBuy)){
				ArrayList<Items> I =m.c.getInventoryArmor() ;
				I.add(toBuy);

				m.c.setInventoryArmor(I);
				JOptionPane.showMessageDialog(null, toBuy.getName() + " has been purchased for £" + toBuy.getPrice() + ". £" + this.m.c.getMoney() + " remaining." , "Purchased", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}
		case "Food" :{
			Items toBuy = FoodList.getSelectedValue();
			if (this.CanAfford(toBuy)){
				this.m.setEnergy(this.m.getEnergy() + toBuy.getModifier());
				JOptionPane.showMessageDialog(null, toBuy.getName() + "has been purchased for £" + toBuy.getPrice() + ". £" + this.m.c.getMoney() + " remaining." , "Purchased", JOptionPane.INFORMATION_MESSAGE);

			}
			break;
		}
		case "Gadget" :{
			Items toBuy = GadgetList.getSelectedValue();
			if (this.CanAfford(toBuy)){
				ArrayList<Items> I =m.c.getInventoryGadget() ;
				I.add(toBuy);

				m.c.setInventoryGadget(I);
				JOptionPane.showMessageDialog(null, toBuy.getName() + "has been purchased for £" + toBuy.getPrice() + ". £" + this.m.c.getMoney() + " remaining." , "Purchased", JOptionPane.INFORMATION_MESSAGE);
			}
			break;
		}


		}
		this.setLabelMoney(this.m.c.getMoney());
		System.out.println(this.FoodList.getSelectedValuesList());
		this.DisplayShopList(m);
	}

	/**
	 * Create the dialog.
	 */
	public CustomDialog(MainMenu m) {
		this.m = m;
		this.setModal(true);
		setBounds(100, 100, 1100, 641);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		{
			JPanel PanelCrimeGen = new JPanel();
			getContentPane().add(PanelCrimeGen, "name_1430860833749126000");
			PanelCrimeGen.setLayout(null);
			{
				JLabel lblACrimeIs = new JLabel("");
				lblACrimeIs.setBounds(53, 119, 270, 79);
				PanelCrimeGen.add(lblACrimeIs);
				this.lblACrimeIs = lblACrimeIs;
			}
			{
				{
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {

							setMessage("no");
							DisposeDialog(true);
						}
					});
					btnNo.setBounds(25, 219, 117, 29);
					PanelCrimeGen.add(btnNo);
				}
			}
			JButton btnYes = new JButton("Yes");
			btnYes.setBounds(222, 219, 117, 29);
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setMessage("yes");
					DisposeDialog(true);

				}
			});
			PanelCrimeGen.add(btnYes);

			JLabel lblCrimeIcon = new JLabel("");
			lblCrimeIcon.setBounds(91, 28, 232, 79);
			PanelCrimeGen.add(lblCrimeIcon);
			this.PanelCrimeGen = PanelCrimeGen;
			this.lblCrimeIcon = lblCrimeIcon;
			//this.setVisible(true);
			//System.out.println("Done insideConst " + CrimeDialog());
			JPanel panelShopOver = new JPanel();
			getContentPane().add(panelShopOver, "name_246962339803042");
			this.PanelShopOverallA = panelShopOver;
			panelShopOver.setLayout(null);
			JPanel PanelShopFood = new JPanel();
			PanelShopFood.setBounds(703, 48, 237, 402);
			panelShopOver.add(PanelShopFood);
			this.PanelShop = PanelShopFood;
			PanelShopFood.setLayout(null);

			JButton btnBuyFood = new JButton("Buy selected food");
			btnBuyFood.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyItem("Food");
				}
			});
			btnBuyFood.setBounds(745, 462, 150, 27);
			panelShopOver.add(btnBuyFood);

			JPanel PanelShopGadget = new JPanel();
			PanelShopGadget.setBounds(426, 48, 237, 400);
			panelShopOver.add(PanelShopGadget);
			this.PanelShopGadget = PanelShopGadget;
			PanelShopGadget.setLayout(null);

			JPanel PanelShopArmor = new JPanel();
			PanelShopArmor.setBounds(172, 48, 207, 400);
			panelShopOver.add(PanelShopArmor);
			this.PanelShopArmor = PanelShopArmor;
			PanelShopArmor.setLayout(null);

			JButton btnBuyArmor = new JButton("Buy selected armor");
			btnBuyArmor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("hi");
					BuyItem("Armor");
				}
			});
			btnBuyArmor.setBounds(200, 462, 150, 27);
			panelShopOver.add(btnBuyArmor);

			JButton btnBuyGadget = new JButton("Buy selected gadget");
			btnBuyGadget.setBounds(465, 462, 150, 27);
			panelShopOver.add(btnBuyGadget);

			JLabel labelMoney = new JLabel("");
			labelMoney.setBounds(39, 540, 57, 15);
			panelShopOver.add(labelMoney);
			this.labelMoney = labelMoney;
			
			JButton btnBackToGame = new JButton("Back to game");
			btnBackToGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DisposeDialog(false);
				}
			});
			btnBackToGame.setBounds(6, 6, 111, 27);
			panelShopOver.add(btnBackToGame);
			this.setLabelMoney(this.m.c.getMoney());

		}
	}
}
