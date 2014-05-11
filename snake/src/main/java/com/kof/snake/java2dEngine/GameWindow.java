package com.kof.snake.java2dEngine;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import com.kof.snake.GameLogic;
import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.properties.PropertiesKey;
import com.kof.snake.properties.PropertiesLoader;
import com.kof.snake.utils.GameConstants;
import com.kof.snake.utils.Keyboard;

public class GameWindow {
	
	private GameLogic gameLogic;
	private Graphics2D graphics;
	private boolean gameRunning = true;
	
	private JFrame frame;
	private Canvas canvas;
	private GraphicsConfiguration graphicConf;
	
	private String title;
	private int resX;
	private int resY;
	private final static int OFFSET = 2;
	
	// window properties
	private boolean showFps = false;
	
	/**
	 * Setup and show game window based on specific graphic configuration
	 * @param resX
	 * @param resY
	 * @throws SomethingsWrongException 
	 */
	public GameWindow(String title, int resX, int resY) {
		this.resX = resX;
		this.resY = resY;
		this.title = title;
		
		// Create base window
		frame = new JFrame();		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle(title);
		JPanel panel = new JPanel();		
		panel.setPreferredSize(new Dimension(resX,resY));
		panel.setLayout(null);
		frame.add(panel);
		frame.addKeyListener(new Keyboard());
		
		// Create canvas 
		GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice graphDevice = graphEnv.getDefaultScreenDevice();
		graphicConf = graphDevice.getDefaultConfiguration();
		
		canvas = new Canvas(graphicConf);		
		canvas.setBounds(0, 0, resX+OFFSET, resY+OFFSET);		
		panel.add(canvas);
		canvas.setIgnoreRepaint(true); // to avoid flickering
		
		// Watch it!
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);		
	}	

	public void startRendering() throws SomethingsWrongException {
		// Load windows properties
		loadProperties();
		
		// TODO vedere se aggiungere LOADING GAME 
		
		// init game logic
		gameLogic = new GameLogic(this);
		
		// Creating double buffering
		try {
			canvas.createBufferStrategy(2, graphicConf.getBufferCapabilities());			
		} catch (AWTException e) {
			throw new SomethingsWrongException("GameWindow - Unable to create double buffering strategy <" + e.getMessage() + ">");
		}

		BufferStrategy bufferStrategy = canvas.getBufferStrategy();		
		
		// Draw to display
		graphics = null;
		long currentTimeMillis = System.currentTimeMillis();
		while(gameRunning){						
			graphics = (Graphics2D) bufferStrategy.getDrawGraphics();
						
			graphics.setBackground(Color.BLACK);
			graphics.clearRect(0, 0, resX+OFFSET, resY+OFFSET);
			
			currentTimeMillis = gameLogic.frameRendering(currentTimeMillis);
			
			graphics.dispose();			
			
			bufferStrategy.show();
		}
		
		// free memory usage
		bufferStrategy.dispose();		 
	}
	
	/**
	 * Set fps to frame title
	 * @param fps
	 */
	public void showFps(long fps){
		if(showFps){
			frame.setTitle(title + " - FPS: " + fps);
		}
	}
	
	/**
	 * Check if specified keys is pressed
	 * @param key
	 * @return
	 */
	public boolean isKeyPressed(int key){
		return Keyboard.isKeyPressed(key);
	}
	
	/**
	 * Write a message on the board
	 * @param message
	 * @param x
	 * @param y
	 */
	public void writeMessage(String message, int x, int y){
		graphics.drawString(message, x, y);
	}
	
	/**
	 * 
	 * @param font
	 * @param c
	 */
	public void setFont(Font font, Color c){		
		graphics.setFont(font);
		graphics.setColor(c);
	}
	
	/**
	 * 
	 * @return
	 */
	public FontMetrics getFontMetrics(Font font){
		return graphics.getFontMetrics(font);
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawBox(int x, int y, int width, int height){
		graphics.drawRect(x, y, width, height);
	}
	
	/**
	 * X resolution of the graphic context
	 * @return
	 */
	public int getGameBoxXResolution() {
		return canvas.getWidth();
	}
	
	/**
	 * Y resolution of the graphic context
	 * @return
	 */
	public int getGameBoxYResolution() {
		return canvas.getHeight();
	}
	
	public void close(){
		frame.dispose();
	}
	
	protected Graphics2D getGraphics() {
		return graphics;
	}
	
	protected GraphicsConfiguration getGraphicsConfiguration(){
		return canvas.getGraphicsConfiguration();
	}
	
	private void loadProperties() throws SomethingsWrongException {
		if(GameConstants.TRUE.equalsIgnoreCase(PropertiesLoader.getProperties(PropertiesKey.SHOW_FPS))){
			showFps = true;
		}		
	}
	
	
}
