package com.kof.snake;

import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.java2dEngine.GameWindow;
import com.kof.snake.utils.GameConstants;

public class GameLauncher {
	public final static String GAME_TITLE = "Snake!"; 
	
	private GameWindow gameWindow = null;

	public void startGame() throws SomethingsWrongException {
		
		gameWindow = new GameWindow(GAME_TITLE, GameConstants.GAME_RES_X, GameConstants.GAME_RES_Y);
		
		gameWindow.startRendering();

		gameWindow.close();
	}
	
	
	public static void main(String[] args) {
		
		GameLauncher game = new GameLauncher();
		
		try {
			
			game.startGame();
			
		} catch (SomethingsWrongException e) {
			game.manageException(e);
		} catch (Exception e) {
			game.manageException(e);
		}
	}


	private void manageException(Exception e) {
		if(gameWindow != null){
			gameWindow.close();
		}
		
		//TODO graficizzare
		e.printStackTrace();		
	}

}
