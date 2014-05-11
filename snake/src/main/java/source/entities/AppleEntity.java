package source.entities;

import source.GameLogic;
import source.entities.basicEntities.BaseEntity;
import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.properties.PropertiesKey;
import source.utils.GridPosition;

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
