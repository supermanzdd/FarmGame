package farmGame;

import java.io.Serializable;
import java.util.Date;

public class Farm implements Serializable {
	private static final long serialVersionUID = 1L;
	public int state = 0;
	public Date lastTime = null;
	
	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String seed(Crop crop,String picture){
		String returnValue = "";
		if(state == 0){
			crop.setIcon(picture);
			state = 1;
			lastTime = new Date();
		}else{
			returnValue = getMessage()+",���ܲ���";
		}
		
		return returnValue;
	}
	
	public String grow(Crop crop,String picture){
		String returnValue = "";
		if(state == 1){
			crop.setIcon(picture);
			state = 2;
			lastTime = new Date();
		}else{
			returnValue = getMessage()+",��������";
		}
		
		return returnValue;
	}
	
	public String bloom(Crop crop,String picture){
		String returnValue = "";
		if(state == 2){
			crop.setIcon(picture);
			state = 3;
			lastTime = new Date();
		}else{
			returnValue = getMessage()+",���ܿ���";
		}
		
		return returnValue;	
	}
	

	public String fruit(Crop crop,String picture){
		String returnValue = "";
		if(state == 3){
			crop.setIcon(picture);
			state = 4;
			lastTime = new Date();
		}else{
			returnValue = getMessage()+",���ܽ��";
		}
		
		return returnValue;
	}
	
	public String harvest(Crop crop,String picture){
		String returnValue="";
		if(state==4){
			crop.setIcon(picture);
			state = 0;
			lastTime = null;
		}else{
			returnValue = getMessage()+",�����ջ�!";
		}
		
		return returnValue;
	}
	
	
	public String getMessage() {
	
		String message = "";
		switch(state){
		case 0:
			message = "���ﻹû�в���";
			break;
		case 1:
			message = "����ող���";
			break;
		case 2:
			message = "������������";
			break;
		case 3:
			message = "���������ڿ�����";
			break;
		case 4:
			message = "�����Ѿ����";
			break;
		}
		
		return message;
	}
	
}
