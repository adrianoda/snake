package source.entities.basicEntities;

import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.properties.PropertiesKey;
import source.utils.Directions;
import source.utils.GameConstants;

public class MovableGridEntity extends MovableEntity {
		
	private int stepInPixel;	
	protected Directions previousDirection;
	private Directions newDirection;

	protected MovableGridEntity(GameWindow gameWindow, PropertiesKey fileNameProperty) throws SomethingsWrongException {
		super(gameWindow, fileNameProperty);				
		this.stepInPixel = 0;
	}

	/**
	 * Move the entity on the axis (x or y) respecting entity movement speed.<br>
	 * It looks for direction to go each time grid movement is done;<br>
	 * ignore it elsewhere.
	 * @param direction
	 * @param frameDuration
	 * @return true if entity moves
	 * @throws SomethingsWrongException 
	 */
	public void move(Directions direction, long frameDuration) throws SomethingsWrongException{ 
		newDirection = direction;

		super.move(super.currentDirection, frameDuration);
	}		
	
	/**
	 * Check if entity reached the grid and look for new direction to go
	 * @throws SomethingsWrongException 
	 */
	protected void movementDoneLogic() throws SomethingsWrongException {
		stepInPixel+=GameConstants.PIXE_PER_STEP;
		
		//Check if grid movement is done
		if(stepInPixel == GameConstants.GRID_DIMENSION){
			//At this moment entity must stay on the grid
			if((super.x % GameConstants.GRID_DIMENSION) != 0 || (super.y % GameConstants.GRID_DIMENSION != 0)){
				throw new SomethingsWrongException("MovableEntity - Wrong entity position.. it must stay on the grid." +
						" Position x<" + super.x + ">, y<" + super.y + ">, Entity <" + this.getClass().getName() + ">");
			}			
			previousDirection = currentDirection;
			currentDirection = newDirection;
			
			//Each entity can apply its own logic, default is do nothing
			gridMovementDoneLogic();			
			
			stepInPixel = 0;		
		} 			
	}

	/**
	 * Logic to do when the entity complete its grid movement. 
	 * @throws SomethingsWrongException 
	 */
	protected void gridMovementDoneLogic() throws SomethingsWrongException{		
	}
	
	public void setPreviousDirection(Directions previousDirection) {
		this.previousDirection = previousDirection;
	}

	public Directions getPreviousDirection() {
		return previousDirection;
	}
	
	
	
}
