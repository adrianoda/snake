package com.kof.snake.java2dEngine;

import java.awt.Image;

public class Sprite implements Cloneable {
	
	private GameWindow gameWindow;
	private Image image;
	
	public Sprite(GameWindow gameWindow, Image image){
		this.gameWindow = gameWindow;
		this.image = image;
	}
	
	public void draw(int x, int y){
		gameWindow.getGraphics().drawImage(image, x, y, null);
	}
	
	public int getWidth(){
		return image.getWidth(null);
	}
	
	public int getHeight(){
		return image.getHeight(null);
	}
}
