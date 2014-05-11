package com.kof.snake.entities;

import com.kof.snake.GameLogic;
import com.kof.snake.entities.basicEntities.BaseEntity;
import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.java2dEngine.GameWindow;
import com.kof.snake.properties.PropertiesKey;
import com.kof.snake.utils.GridPosition;

public class AppleEntity extends BaseEntity {
		
	public AppleEntity(GameWindow gameWindow, GameLogic gameLogic) throws SomethingsWrongException {
		super(gameWindow, PropertiesKey.APPLE_BODY);		
		this.gameLogic = gameLogic;
		putToNewPosition();
	}		
	
	public void snakeGetYou() {	
		putToNewPosition();		
	}

	private void putToNewPosition() {
		GridPosition pos = gameLogic.getFreePosition();
		
//		System.out.print("Freepos<" + pos.getX() + "-" + pos.getY() + "> -->");
//		gameLogic.stampa();		
		
		super.setPosition(pos.getX(), pos.getY());
		gameLogic.removeGridPosition(pos);	
	}
}
