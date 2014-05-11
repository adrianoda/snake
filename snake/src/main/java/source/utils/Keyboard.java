package source.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private static boolean[] keyPressed = new boolean[1024];

	@Override
	public void keyPressed(KeyEvent event) {
		keyPressed[event.getKeyCode()] = true;		
	}

	@Override
	public void keyReleased(KeyEvent event) {
    	keyPressed[event.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent event) {
		// TODO Auto-generated method stub		
	}

	public static boolean isKeyPressed(int key){
		return keyPressed[key];
	}

}
