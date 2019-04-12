package farmGame;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class MainFrame extends JFrame implements MouseListener,Runnable{

	int fruitNumber = 0;
	Farm[] farms = new Farm[9];
	Crop[] crops = new Crop[9];
	Farm farm = new Farm();
	Crop crop = new Crop();
	JLabel storage;	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	FileInputStream fis = null;
	FileOutputStream fos = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;

	//������1Сʱ��������2Сʱ�������3Сʱ
	public static final long GROWTIME = 1000*60,BLOOMTIME = 1000*60*2,FRUITTIME = 1000*60*3;

	public MainFrame(){
		super();
		setTitle("�����Լ��Ŀ���ũ��");
		setBounds(500,200,900,600);//���ô���λ�úʹ�С
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addMouseListener(this);
		final BackgroundPanel backgroundPanel = new BackgroundPanel();
		Image bk = Toolkit.getDefaultToolkit().getImage("D:/Game/FarmGame/farmBackground.png");
		backgroundPanel.setImage(bk);
		backgroundPanel.setBounds(0,0,855,553);//������Ϸ���λ�úʹ�С
		getContentPane().add(backgroundPanel);
		storage = new JLabel();
		storage.setHorizontalAlignment(SwingConstants.CENTER);
		storage.setText("���Ĳֿ�û���κι�ʵ����첥�ְɣ�");
		storage.setBounds(200,70,253,28);//��ǩλ��
		backgroundPanel.add(storage);

		initlize();
		for(Crop temp:crops){
			backgroundPanel.add(temp);
		}		

		final JButton button_1 = new JButton();//����
		button_1.setRolloverIcon(new ImageIcon("D:/Game/FarmGame/����1.png"));//�ƶ���ͼ������ʾ��ͼƬ
		button_1.setBorderPainted(false);
		button_1.setContentAreaFilled(false);
		button_1.setIcon(new ImageIcon("D:/Game/FarmGame/����.png"));//������ʾͼƬ
		button_1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(farm == null)//���û����ѡ������
					return ;

				String message = farm.seed(crop, "D:/Game/FarmGame/seed.png");
				if(!message.equals("")){
					JOptionPane.showMessageDialog(null, message);
				}
			}			
		});		
		button_1.setBounds(140,477,56,56);//29, 185, 56, 56
		backgroundPanel.add(button_1);


		final JButton button_2 = new JButton();//����
		button_2.setContentAreaFilled(false);
		button_2.setBorderPainted(false);
		button_2.setRolloverIcon(new ImageIcon("D:/Game/FarmGame/����1.png"));
		button_2.setIcon(new ImageIcon("D:/Game/FarmGame/����.png"));
		button_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(farm == null)//���û����ѡ������
					return ;

				String message = farm.grow(crop, "D:/Game/FarmGame/grow.png");
				if(!message.equals("")){
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		backgroundPanel.add(button_2);
		button_2.setBounds(280,477,56,56);//114,185,56,56


		final JButton button_3 = new JButton();
		button_3.setContentAreaFilled(false);
		button_3.setBorderPainted(false);
		button_3.setRolloverIcon(new ImageIcon("D:/Game/FarmGame/����1.png"));
		button_3.setIcon(new ImageIcon("D:/Game/FarmGame/����.png"));
		button_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if(farm == null)//���û����ѡ������
					return ;

				String message = farm.bloom(crop, "D:/Game/FarmGame/bloom.png");
				if(!message.equals("")){
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		backgroundPanel.add(button_3);
		button_3.setBounds(420,477,56,56);//199,185,56,56;


		final JButton button_4 = new JButton();
		button_4.setContentAreaFilled(false);
		button_4.setBorderPainted(false);
		button_4.setRolloverIcon(new ImageIcon("D:/Game/FarmGame/���1.png"));
		button_4.setIcon(new ImageIcon("D:/Game/FarmGame/���.png"));
		button_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(farm == null)//���û����ѡ������
					return ;

				String message = farm.fruit(crop,"D:/Game/FarmGame/fruit.png");
				if(!message.equals("")){
					JOptionPane.showMessageDialog(null, message);
				}
			}
		});
		backgroundPanel.add(button_4);
		button_4.setBounds(560,477,56,56);


		final JButton button_5 = new JButton();
		button_5.setContentAreaFilled(false);
		button_5.setBorderPainted(false);
		button_5.setRolloverIcon(new ImageIcon("D:/Game/FarmGame/�ջ�1.png"));
		button_5.setIcon(new ImageIcon("D:/Game/FarmGame/�ջ�.png"));
		button_5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(farm == null)//���û����ѡ������
					return ;

				String message = farm.harvest(crop, "");
				if(!message.equals("")){
					JOptionPane.showMessageDialog(null, message);
				}else{
					fruitNumber++;
					storage.setText("���Ĳֿ�������"+fruitNumber+"����ʵ.");
				}
			}
		});
		backgroundPanel.add(button_5);
		button_5.setBounds(700,477,56,56);//369,185,56,56

		new Thread(this).start();

	}


	public void initlize(){

		for(int i=0;i<9;i++){
			farms[i] = new Farm();
			crops[i] = new Crop();
		}

		crops[0].setBounds(269, 153, 106, 96);
		crops[1].setBounds(165, 195, 106, 96);
		crops[2].setBounds(59, 238, 106, 96);
		crops[3].setBounds(378, 199, 106, 96);
		crops[4].setBounds(278, 236, 106, 96);
		crops[5].setBounds(169, 284, 106, 96);
		crops[6].setBounds(501, 247, 106, 96);
		crops[7].setBounds(393, 281, 106, 96);
		crops[8].setBounds(286, 333, 106, 96);


		farm = farms[0];//Ĭ��ѡ�е�һ������
		crop = crops[0];//Ĭ�ϵ�һ��ֲ��
		readNowState();//��������״̬
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();

		System.out.println("x is "+x+", y is "+y);

		if(isInDiamond(x-324,y-258,160,65)){//��һ������
			System.out.println("��һ������");
			farm = farms[0];
			crop = crops[0];
		}else if(isInDiamond(x-220,y-300,160,65)){
			System.out.println("�ڶ�������");			
			farm = farms[1];
			crop = crops[1];
		}else if(isInDiamond(x-114,y-343,160,65)){
			System.out.println("����������");
			farm = farms[2];
			crop = crops[2];
		}else if(isInDiamond(x-433,y-304,160,65)){
			System.out.println("���Ŀ�����");
			farm = farms[3];
			crop = crops[3];
		}else if(isInDiamond(x-333,y-341,160,65)){
			System.out.println("���������");
			farm = farms[4];
			crop = crops[4];
		}else if(isInDiamond(x-224,y-389,160,65)){
			System.out.println("����������");
			farm = farms[5];
			crop = crops[5];
		}else if(isInDiamond(x-556,y-352,160,65)){
			System.out.println("���߿�����");
			farm = farms[6];
			crop = crops[6];
		}else if(isInDiamond(x-448,y-386,160,65)){
			System.out.println("�ڰ˿�����");
			farm = farms[7];
			crop = crops[7];
		}else if(isInDiamond(x-341,y-438,160,65)){//�ھſ�����
			System.out.println("�ھſ�����");
			farm = farms[8];
			crop = crops[8];
		}else{
			farm = null;
			crop = null;
			System.out.println("û��ѡ���κ�����");
		}

	}

	//width���ο�height���θ�
	public boolean isInDiamond(int x,int y,int width,int height){
		if(Math.abs(x*height)+Math.abs(y*width)<=width*height*0.5){
			return true;
		}

		return false;
	}

	public void readNowState(){

		File file = new File("D://GameRecordAboutSwing");

		if(!file.exists()){
			file.mkdirs();
		}

		File record = new File("D://GameRecordAboutSwing/recordFarmGame.txt");

		try{
			if(!record.exists()){//���������,�½��ı�
				record.createNewFile();
				fos = new FileOutputStream(record);
				dos = new DataOutputStream(fos);
				String s = "0,null;0,null;0,null;0,null;0,null;0,null;0,null;0,null;0,null&0";
				dos.writeBytes(s);
				System.out.println(record.isFile());;
			}

			//��ȡ��¼
			fis = new FileInputStream(record);
			dis = new DataInputStream(fis);
			String str = dis.readLine();
			String[] array1 = str.split("&");//�������
			String[] array2 = array1[0].split(";");//�������
			fruitNumber = Integer.parseInt(array1[1]);//�ֿ��ʵ
			if(fruitNumber == 0){
				storage.setText("���Ĳֿ�û���κι�ʵ����첥�ְɣ�");
			}else{
				storage.setText("���Ĳֿ�������"+fruitNumber+"����ʵ.");
			}

			for(int i=0;i<9;i++){
				String[] help = array2[i].split(",");
				int state = Integer.parseInt(help[0]);
				if(help[1].equals("null")){//δ���֣�ֱ������
					continue;
				}
				Date lastTime = sdf.parse(help[1]);
				Date nowTime = new Date();
				long subTime = nowTime.getTime() - lastTime.getTime();//�õ������딵
				long temp = 0;//�洢farm[i]��������ʱ�䵽��ǰʱ���ʱ���(�����ʾ)
				if(state == 1){//�Ѳ���
					if(subTime>=GROWTIME){
						subTime -= GROWTIME;
						temp += GROWTIME;
						state = 2;
					}
				}
				if(state == 2){//������
					if(subTime>=BLOOMTIME){
						subTime -= BLOOMTIME;
						temp += BLOOMTIME;
						state = 3;
					}
				}
				if(state == 3){//�ѿ���
					if(subTime>=FRUITTIME){
						subTime -= FRUITTIME;
						temp += FRUITTIME;
						state = 4;
					}
				}

				switch(state){
				case 1:
					farms[i].state = 1;
					farms[i].lastTime = lastTime;
					crops[i].setIcon("D:/Game/FarmGame/seed.png");
					break;
				case 2:
					farms[i].state = 2;
					farms[i].lastTime = new Date(lastTime.getTime()+temp);
					crops[i].setIcon("D:/Game/FarmGame/grow.png");
					break;
				case 3:
					farms[i].state = 3;
					farms[i].lastTime = new Date(lastTime.getTime()+temp);
					crops[i].setIcon("D:/Game/FarmGame/bloom.png");
					break;
				case 4:
					farms[i].state = 4;
					farms[i].lastTime = new Date(lastTime.getTime()+temp);
					crops[i].setIcon("D:/Game/FarmGame/fruit.png");
					break;
				}

			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(fis!=null)
					fis.close();
				if(dis!=null)
					dis.close();			
				if(fos!=null)
					fos.close();
				if(dos!=null)
					dos.close();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}



	public void saveNowState(){

		StringBuilder sb = new StringBuilder();
		String[] strs = new String[9];

		for(int i=0;i<9;i++){
			strs[i] = farms[i].lastTime == null?"null":sdf.format(farms[i].lastTime);//�������һ�β���ʱ��
			if(i!=8)
				sb.append(farms[i].state+","+strs[i]+";");
			else
				sb.append(farms[i].state+","+strs[i]);				
		}

		sb.append("&"+fruitNumber);
//		System.out.println(sb);	

		File record = new File("D://GameRecordAboutSwing/recordFarmGame.txt");

		try {
			//���ԭ�м�¼
			FileWriter fileWriter =new FileWriter(record);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
			//����д���ı�
			fos = new FileOutputStream(record);
			dos = new DataOutputStream(fos);
			String s = sb.toString();
			dos.writeBytes(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				if(fos!=null)
					fos.close();
				if(dos!=null)
					dos.close();				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@Override
	public void run() {
		try {
			while(true){
				Thread.sleep(1000);
				saveNowState();
				readNowState();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}



}
