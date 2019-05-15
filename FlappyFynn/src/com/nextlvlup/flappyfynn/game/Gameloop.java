package com.nextlvlup.flappyfynn.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;

public class Gameloop {
	
	private TimerTask gameloop;
	private TimerTask score_counter;
	private int score = 0;
	private boolean running = false;
	private List<MoveableObject> objects = new ArrayList<>();
	private JLabel score_text;
	private Character character;
	private Game game;
	private int distance = 200;
	private int speed = 5;
	
	private MoveableObject top1;
	private MoveableObject top2;
	private MoveableObject down1;
	private MoveableObject down2;
	
	public Gameloop(Game game, int fps) {
		this.game = game;
		int rate = 1000 / fps;
		
		game.printText("drücken um zu spielen", 200, 35);
		
		gameloop = new TimerTask() {
			
			@Override
			public void run() {
				if(isRunning()) {
					List<MoveableObject> list = new ArrayList<>();
					list.addAll(objects);
					for(MoveableObject obj : list) {
						obj.triger();
						if(obj.getObject().getBounds().intersects(character.getHitbox())){
							pause();
						}
					}
					
					Random ran = new Random();
					
					if(top1.getX() < -100) {
						top1.move(top2.getX() + 600, -400 + ran.nextInt(game.getContentPane().getHeight() - 350));
						down1.move(top1.getX(), top1.getY() + top1.getHeight() + distance);
					}
					
					if(top2.getX() < -100) {
						top2.move(top1.getX() + 600, -400 + ran.nextInt(game.getContentPane().getHeight() - 350));
						down2.move(top2.getX(), top2.getY() + top2.getHeight() + distance);
					}
					
					character.triger();
					if(character.getY() > (game.getContentPane().getHeight() + 100)) {
						pause();
					}
					if(character.getY() < -200) {
						pause();
					}
				}
			}
		};
		
		new Timer().scheduleAtFixedRate(gameloop, 0, rate);
	}
	
	public void reset() {
		objects.clear();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void pause() {
		if(score_counter != null) {
			score_counter.cancel();
		}
		game.printText("drücken um zu spielen", 200, 35);
		running = false;
	}
	
	public void resum() {
		game.prepare();
		
		spawnBarriers();
		
		if(score_text != null) {
			game.getContentPane().remove(score_text);
		}
		
		distance = 200;
		speed = 5;
		score = 0;
		score_text = game.printText("" + score, 250, 80);
		running = true;
		
		score_counter = new TimerTask() {
			
			@Override
			public void run() {
				score++;
				score_text.setText("" + score);
				
				switch(score) {
				case 20:
					distance = 190;
					changeSpeed(7);
					break;
				case 40:
					distance = 180;
					changeSpeed(9);
					break;
				case 60:
					distance = 170;
					changeSpeed(11);
					break;
				case 80:
					distance = 160;
					changeSpeed(13);
					break;
				case 100:
					distance = 150;
					changeSpeed(15);
					break;
				case 120:
					distance = 140;
					break;
				case 150:
					distance = 130;
					break;
				case 200:
					distance = 120;
				}
				
			}
		};
		new Timer().scheduleAtFixedRate(score_counter, 1000, 1000);
	}
	
	public void spawnBarriers() {
		Random ran = new Random();
		int pos = ran.nextInt(game.getContentPane().getHeight() - 350);
		int pos2 = ran.nextInt(game.getContentPane().getHeight() - 350);
		
		top1 = new MoveableObject("barrier.png", game, 15);
		top1.move(game.getContentPane().getWidth() + 100, -400 + pos);
		top1.setVector(speed, 0);
		addMoveable(top1);
		
		down1 = new MoveableObject("barrier_down.png", game, 15);
		down1.move(game.getContentPane().getWidth() + 100, top1.getY() + top1.getHeight() + distance);
		down1.setVector(speed, 0);
		addMoveable(down1);
		
		top2 = new MoveableObject("barrier.png", game, 15);
		top2.move(game.getContentPane().getWidth() + 700, -400 + pos2);
		top2.setVector(speed, 0);
		addMoveable(top2);
		
		down2 = new MoveableObject("barrier_down.png", game, 15);
		down2.move(game.getContentPane().getWidth() + 700, top2.getY() + top2.getHeight() + distance);
		down2.setVector(speed, 0);
		addMoveable(down2);
	}
	
	public void changeSpeed(int speed) {
		for(MoveableObject obj : objects) {
			obj.setVector(speed, 0);
		}
	}
	
	public void stop() {
		gameloop.cancel();
	}
	
	public void addChar(Character obj) {
		character = obj;
	}
	
	public void addMoveable(MoveableObject obj) {
		objects.add(obj);
	}

}
