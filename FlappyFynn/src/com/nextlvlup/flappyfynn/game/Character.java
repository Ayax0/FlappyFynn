package com.nextlvlup.flappyfynn.game;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import com.nextlvlup.flappyfynn.Main;

public class Character {
	
	private JLabel image;
	private ImageIcon icon;
	private int xVector = 0;
	private int yVector = 0;
	private boolean gravitation = false;
	private String texture_name;
	
	public Character(String texture, Game instance) {
		icon = new ImageIcon(Main.class.getResource("/com/nextlvlup/flappyfynn/assets/" + texture));
		texture_name = texture;
		
		image = new JLabel();
		image.setIcon(icon);
		image.setSize(icon.getIconWidth(), icon.getIconHeight());
		instance.getContentPane().add(image);
		instance.getContentPane().setLayer(image, 10);
	}
	
	public String getTextureName() {
		return texture_name;
	}
	
	public void move(int x, int y) {
		image.setLocation(x, y);
	}
	
	public int getX() {
		return (int) image.getLocation().getX();
	}
	
	public int getY() {
		return (int) image.getLocation().getY();
	}
	
	public int getWidth() {
		return icon.getIconWidth();
	}
	
	public int getHeight() {
		return icon.getIconHeight();
	}
	
	public void setVector(int xV, int yV) {
		xVector = xV;
		yVector = yV;
	}
	
	public void addVector(int xV, int yV) {
		xVector += xV;
		yVector += yV;
	}
	
	public int getXVector() {
		return xVector;
	}
	
	public int getYVector() {
		return yVector;
	}
	
	public void setGravitation(boolean state) {
		gravitation = state;
	}
	
	public void triger() {
		move(getX() - getXVector(), getY() - getYVector());
		if(gravitation) {
			yVector--;
		}
	}
	
	public JLabel getObject() {
		return image;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(image.getX() + 8, image.getY() + 8, icon.getIconWidth() - 16, icon.getIconHeight() - 16);
	}

}
