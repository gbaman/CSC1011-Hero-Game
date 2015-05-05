package csc1011;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JLabel;

public class CustomDialog extends JDialog {

	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	public static void main() {
		try {
			CustomDialog dialog = new CustomDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	JLabel lblCrimeIcon;
	JLabel lblACrimeIs;
	JPanel PanelCrimeGen;

	public void CrimeDialog(){
		System.out.println("Crime Dialog!");
		ImageIcon imgCrime = new ImageIcon(getClass().getResource("/Crime-Pix.jpg"));
		this.lblCrimeIcon.setIcon(imgCrime);
		this.setTitle("Crime happening!");
		Thread t = new Thread(new DialogClose(this, 5, "<html>\nA crime is being commited at the docks! <br>\nWould you like to fight it?"));
		t.start();
		this.PanelCrimeGen.setVisible(true);

		
		
	}
	
	
	/**
	 * Create the dialog.
	 */
	public CustomDialog() {
		this.setModal(true);
		setBounds(100, 100, 450, 300);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(new CardLayout(0, 0));
		{
			JPanel PanelCrimeGen = new JPanel();
			getContentPane().add(PanelCrimeGen, "name_1430860833749126000");
			PanelCrimeGen.setLayout(null);
			{
				JLabel lblACrimeIs = new JLabel("");
				lblACrimeIs.setBounds(93, 118, 270, 79);
				PanelCrimeGen.add(lblACrimeIs);
				this.lblACrimeIs = lblACrimeIs;
			}
			{
				{
					JButton btnNo = new JButton("No");
					btnNo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
						}
					});
					btnNo.setBounds(65, 218, 117, 29);
					PanelCrimeGen.add(btnNo);
				}
			}
			JButton btnYes = new JButton("Yes");
			btnYes.setBounds(262, 218, 117, 29);
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			PanelCrimeGen.add(btnYes);
			
			JLabel lblCrimeIcon = new JLabel("");
			lblCrimeIcon.setBounds(93, 27, 270, 79);
			PanelCrimeGen.add(lblCrimeIcon);
			this.PanelCrimeGen = PanelCrimeGen;
			this.lblCrimeIcon = lblCrimeIcon;
		}
	}
}
