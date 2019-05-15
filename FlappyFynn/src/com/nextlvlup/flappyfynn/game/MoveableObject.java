package com.nextlvlup.flappyfynn.game;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nextlvlup.flappyfynn.Main;

public class MoveableObject {
	
	private JLabel object;
	private ImageIcon image;
	private int xVector = 0;
	private int yVector = 0;
	private boolean gravitation = false;
	private String texture_name;
	
	public MoveableObject(String texture, Game instance, int layer) {
		image = new ImageIcon(Main.class.getResource("/com/nextlvlup/flappyfynn/assets/" + texture));
		texture_name = texture;
		
		object = new JLabel();
		object.setBackground(Color.RED);
		object.setIcon(image);
		object.setSize(image.getIconWidth(), image.getIconHeight());
		instance.getContentPane().add(object);
		instance.getContentPane().setLayer(object, layer);
	}
	
	public String getTextureName() {
		return texture_name;
	}
	
	public void move(int x, int y) {
		object.setLocation(x, y);
	}
	
	public int getX() {
		return (int) object.getLocation().getX();
	}
	
	public int getY() {
		return (int) object.getLocation().getY();
	}
	
	public int getWidth() {
		return image.getIconWidth();
	}
	
	public int getHeight() {
		return image.getIconHeight();
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
		return object;
	}

}
