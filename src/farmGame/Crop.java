package farmGame;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Crop extends JLabel{

	
	Icon icon = null;
	public Crop(){
		super();
	}

	public void setIcon(String picture){
		icon = new ImageIcon(picture);//ªÒ»°Õº∆¨
		setIcon(icon);
	}
	
}
