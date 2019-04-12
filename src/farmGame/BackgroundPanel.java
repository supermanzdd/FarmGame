package farmGame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
   
public class BackgroundPanel extends JPanel{

	private Image image;
	
	public BackgroundPanel(){
		super();
		setOpaque(false);//当设置为false时，组件并未不会显示其中的某些像素，允许控件下面的像素显现出来。
		setLayout(null);
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	protected void paintComponent(Graphics g){//重写绘制组件外观方法
		if(image!=null){
			int width = getWidth();
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);
		}
		super.paintComponent(g);
		
		
		System.out.println("我的游戏。。。。。第一个。。。");
		System.out.println("我的游戏。。。。。第二个、、、");
	}
		
	
	
	
}
