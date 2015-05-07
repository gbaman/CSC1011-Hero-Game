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
	JList FoodList;

	
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
	
	public String DisplayShopList(){
		Items bob = new Items("bob", "CrimePix");
		Items brian = new Items("bob", "CrimePix");
		DefaultListModel<Items> listModel = new DefaultListModel<>();
		listModel.addElement(bob);
		listModel.addElement(brian);
		JList<Items> FoodList = new JList<>(listModel);
		//getContentPane().add(new JScrollPane(countryList));
		FoodList.setCellRenderer(new ShopItemRenderer());
		//countryList.setBounds(204, 208, 130, -117);
		FoodList.setVisibleRowCount(1);
		JScrollPane scrollPane = new JScrollPane();
		this.FoodList = FoodList;
		scrollPane.setViewportView(FoodList);
		scrollPane.setMinimumSize(new Dimension(100, 50));
		scrollPane.setBounds(0, 0, this.PanelShop.getWidth(), this.PanelShop.getHeight());
		this.PanelShop.add(scrollPane);
		this.PanelCrimeGen.setVisible(false);
		this.PanelShop.setVisible(true);
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
		setBounds(100, 100, 795, 493);
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
			JPanel PanelShop = new JPanel();
			PanelShop.setBounds(371, 23, 237, 402);
			panelShopOver.add(PanelShop);
			this.PanelShop = PanelShop;
			PanelShop.setLayout(null);
			
			JButton btnBuyItem = new JButton("Buy Item");
			btnBuyItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BuyItem();
				}
			});
			btnBuyItem.setBounds(73, 274, 94, 27);
			panelShopOver.add(btnBuyItem);

		}
	}
}
