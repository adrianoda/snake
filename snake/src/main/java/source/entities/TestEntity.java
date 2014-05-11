package source.entities;

import source.entities.basicEntities.MovableGridEntity;
import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.properties.PropertiesKey;
import source.utils.Directions;
import source.utils.GameConstants;

public class TestEntity extends MovableGridEntity { 
	
	public TestEntity(GameWindow gameWindow) throws SomethingsWrongException {
		super(gameWindow,  PropertiesKey.SNAKE_HEAD_LEFT);
		super.setPosition(160, 100);
		super.speed = 10;
	}

	@Override
	protected void gridMovementDoneLogic() {
//		super.previousDirection = super.currentDirection;
//		super.currentDirection = Directions.STOP;
				
		//Check if entity moved off the active border
		if(super.x == GameConstants.BOX_ACTIVE_START_X && super.currentDirection == Directions.LEFT){
			super.x = GameConstants.BOX_ACTIVE_END_X;
		}
		if(super.x == (GameConstants.BOX_ACTIVE_END_X - super.getWidth()) && super.currentDirection == Directions.RIGHT){
			super.x = GameConstants.BOX_ACTIVE_START_X;
		}
		if(super.y == GameConstants.BOX_ACTIVE_START_Y && super.currentDirection == Directions.UP){
			super.y = GameConstants.BOX_ACTIVE_END_Y;
		}
		if(super.y == (GameConstants.BOX_ACTIVE_END_Y - super.getHeight()) && super.currentDirection == Directions.DOWN){
			super.y = GameConstants.BOX_ACTIVE_START_Y;
		}
		
	}	

}
