package com.nextlvlup.flappyfynn.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

public class Game {
	
	private JFrame frame;
	private JLayeredPane content;
	private Gameloop loop;
	Character fynn;
	
	public Game() {
		frame = new JFrame("FlappyFynn");
		frame.setBounds(0, 0, 800, 600);
		
		content = new JLayeredPane();
		content.setLayout(null);
		frame.setContentPane(content);
		
		frame.setVisible(true);
		
		loop = new Gameloop(this, 30);
		
		frame.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 32) {
					if(loop.isRunning()) {
						fynn.setVector(0, 15);
					}else {
						prepare();
						fynn.move(100, 50);
						fynn.setVector(0, 0);
						loop.resum();
					}
				}
			}
			
		});
	}
	
	public void prepare() {
		content.removeAll();
		content.repaint();
		loop.reset();
		
		fynn = new Character("fynn-head.png", this);
		fynn.move(100, 50);
		fynn.setGravitation(true); 
		loop.addChar(fynn);
	}
	
	public void renderBackground() {
		frame.setBackground(Color.PINK);
	}
	
	public JLayeredPane getContentPane() {
		return content;
	}
	
	public JLabel printText(String text, int y, int size) {
		JLabel label = new JLabel(text);
		label.setBounds(0, y, frame.getWidth(), size);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Corbel", Font.BOLD, size));
		content.add(label);
		content.setLayer(label, 50);
		
		return label;
	}

}
