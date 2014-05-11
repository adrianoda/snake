package com.kof.snake.java2dEngine;

import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.kof.snake.exceptions.SomethingsWrongException;

public class SpriteLoader {

	private static Map<String, Sprite> spriteContainer = new HashMap<String, Sprite>();
	
	private SpriteLoader(){		
	}
	
	/**
	 * Return sprite related to specified file image
	 * @param gameWindow
	 * @param fileName
	 * @return
	 * @throws SomethingsWrongException
	 */
	public static Sprite getSprite(GameWindow gameWindow, String fileName) throws SomethingsWrongException{
		if(spriteContainer.containsKey(fileName)){			
			return spriteContainer.get(fileName);		 	
		}
		
		URL spriteLocation = ClassLoader.getSystemResource(fileName);
		
		if(spriteLocation == null) {
			throw new SomethingsWrongException("SpriteLoader - Unable to find sprite resource: <" + fileName + ">");
		}
		
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(spriteLocation);
		} catch (IOException e) {
			throw new SomethingsWrongException("SpriteLoader - An error occurse while reading sprite URL. " + e.getMessage());
		}
		if(srcImage == null) {
			throw new SomethingsWrongException("SpriteLoader - Unable to decode sprite: <" + fileName + ">");
		}
		
		Image image = gameWindow.getGraphicsConfiguration().createCompatibleImage(srcImage.getWidth(), srcImage.getHeight(), Transparency.BITMASK);
		image.getGraphics().drawImage(srcImage,0,0,null);
		
		Sprite sprite = new Sprite(gameWindow, image);
		
		spriteContainer.put(fileName, sprite);
		
		return sprite;
	}
}
