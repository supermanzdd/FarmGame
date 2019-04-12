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
			returnValue = getMessage()+",不能播种";
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
			returnValue = getMessage()+",不能生长";
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
			returnValue = getMessage()+",不能开花";
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
			returnValue = getMessage()+",不能结果";
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
			returnValue = getMessage()+",不能收获!";
		}
		
		return returnValue;
	}
	
	
	public String getMessage() {
	
		String message = "";
		switch(state){
		case 0:
			message = "作物还没有播种";
			break;
		case 1:
			message = "作物刚刚播种";
			break;
		case 2:
			message = "作物正在生长";
			break;
		case 3:
			message = "作物正处于开花期";
			break;
		case 4:
			message = "作物已经结果";
			break;
		}
		
		return message;
	}
	
}
