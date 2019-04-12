package farmGame;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
   
public class BackgroundPanel extends JPanel{

	private Image image;
	
	public BackgroundPanel(){
		super();
		setOpaque(false);//������Ϊfalseʱ�������δ������ʾ���е�ĳЩ���أ�����ؼ�������������ֳ�����
		setLayout(null);
	}
	
	public void setImage(Image image){
		this.image = image;
	}
	
	protected void paintComponent(Graphics g){//��д���������۷���
		if(image!=null){
			int width = getWidth();
			int height = getHeight();
			g.drawImage(image, 0, 0, width, height, this);
		}
		super.paintComponent(g);
		
		
		System.out.println("�ҵ���Ϸ������������һ��������");
		System.out.println("�ҵ���Ϸ�����������ڶ���������");
	}
		
	
	
	
}
