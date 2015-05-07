package csc1011;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
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
	
	public void DisposeDialog(){
		this.DialogCloser.interrupt();
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
		
		
		return a;
		
	}
	
	public String DisplayShopList(MainMenu m){
		DefaultListModel<Items> FoodListModel = new DefaultListModel<>();
		// (Name, filename, price, modifier)
		//.addElement(new Food("", "", 20, 10));
		FoodListModel.addElement(new Food("Apple", "Apple", 20, 10));
		FoodListModel.addElement(new Food("Toast", "Toast-Bread", 50, 20));
		
		DefaultListModel<Items> ArmorListModel = new DefaultListModel<>();
		ArmorListModel.addElement(new Clothing("Suit", "Suit", 20, 10));
		
		DefaultListModel<Items> GadgetListModel = new DefaultListModel<>();
		
		
		JList<Items> FoodList = new JList<>(FoodListModel);
		JList<Items> ArmorList = new JList<>(checkItemsNotBought(ArmorListModel));
		JList<Items> GadgetList = new JList<>(checkItemsNotBought(GadgetListModel));
		FoodList.setCellRenderer(new ShopItemRenderer());
		ArmorList.setCellRenderer(new ShopItemRenderer());
		GadgetList.setCellRenderer(new ShopItemRenderer());
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
	
	private void BuyItem(){
		System.out.println(this.FoodList.getSelectedValue());
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
							DisposeDialog();
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
					DisposeDialog();
					
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
			PanelShopFood.setBounds(765, 22, 237, 402);
			panelShopOver.add(PanelShopFood);
			this.PanelShop = PanelShopFood;
			PanelShopFood.setLayout(null);
			
			JButton btnBuyItem = new JButton("Buy Item");
			btnBuyItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyItem();
				}
			});
			btnBuyItem.setBounds(73, 274, 100, 27);
			panelShopOver.add(btnBuyItem);
			
			JPanel PanelShopGadget = new JPanel();
			PanelShopGadget.setBounds(470, 241, 237, 400);
			panelShopOver.add(PanelShopGadget);
			this.PanelShopGadget = PanelShopGadget;
			PanelShopGadget.setLayout(null);
			
			JPanel PanelShopArmor = new JPanel();
			PanelShopArmor.setBounds(251, 49, 207, 400);
			panelShopOver.add(PanelShopArmor);
			this.PanelShopArmor = PanelShopArmor;
			PanelShopArmor.setLayout(null);

		}
	}
}
