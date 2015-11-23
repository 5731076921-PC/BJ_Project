package entity;
ackage logic;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Tank {

	private static final int speed = 5;
	private int angle = 0; //angle 0 = EAST
	
	private boolean flashing = false;
	private int flashCounter = 0;
	private int flashDurationCounter = 0;
	
	public Tank(int x, int y){
		this.x = x;
		this.y = y;
		this.radius = 20;
	}
	
	private void forward(){
		this.x += Math.cos(Math.toRadians(30))*speed;
		this.y += Math.sin(Math.toRadians(30))*speed;
	}
	
	private void turn(boolean left){
		if(left){
			angle -= 3;
			if(angle < 0) angle += 360;
		}else{
			angle += 3;
			if(angle >= 360) angle -= 360;
		}
	}
	
	public void hitByMine(){
		flashing = true;
		flashCounter = 10;
		flashDurationCounter = 10;
	}
	
	public void update(){
		if(flashing){
			if(flashCounter == 0){
				this.visible = true;
				flashing = false;
			}else{
				if(flashDurationCounter > 0){
					this.visible = flashCounter <= 5;
					flashDurationCounter--;
				}else{
					this.visible = true;
					flashDurationCounter = 10;
					flashCounter--;
				}
			}
		}
		forward();
	}
	
	@Override
	public void draw(Graphics2D g2d) {
		// TODO Auto-generated method stub
		
		g2d.setColor(Color.BLUE);
		g2d.fillArc(x-radius, y-radius, radius*2, radius*2, 0, 360);
		g2d.translate(x, y);
		g2d.rotate(Math.toRadians(angle));
		g2d.setColor(Color.YELLOW);
		int gunSize = radius/5;
		g2d.fillRect(0, -gunSize, radius*3/2, gunSize*2);
		g2d.rotate(Math.toRadians(-angle));
		g2d.translate(-x, -y);
		
	}

}
