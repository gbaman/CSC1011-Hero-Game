package csc1011;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;


public class ShopItemRenderer extends JLabel implements ListCellRenderer<Items> {

	public ShopItemRenderer() {
        setOpaque(true);
    }
	
	@Override
	public Component getListCellRendererComponent(JList<? extends Items> list,
			Items value, int index, boolean isSelected, boolean cellHasFocus) {
		String code = value.getCode();
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Crime-Pix.jpg"));
        //= new ImageIcon(getClass().getResource("/resources/" + "Crime-Pix" + ".jpg"));

        setIcon(imageIcon);
        setText(value.getName());

        if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }

        return this;
	}

}
