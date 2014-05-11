package source.entities.basicEntities;

import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.properties.PropertiesKey;
import source.utils.Directions;
import source.utils.GameConstants;

public class MovableEntity extends BaseEntity {
			
	protected float speed;
	private float deltaPixel;
	
	protected Directions currentDirection;

	//FIXME per monitorare la velocita'.. da rimuovere
	public float pxMisurati=0;
	public float tempo=0;
	public float pixelAlSecondo=0;
	
	public MovableEntity(GameWindow gameWindow, PropertiesKey fileNameProperty) throws SomethingsWrongException {
		super(gameWindow, fileNameProperty);
		this.speed = GameConstants.DEFAULT_ENTITY_SPEED;
		this.currentDirection = Directions.STOP;
		this.deltaPixel = 0;
	}		
	
	/**
	 * Move the entity on the axis (x or y) respecting entity movement speed.
	 * @param direction
	 * @return true if entity moves
	 * @throws SomethingsWrongException 
	 */
	public void move(Directions direction, long frameDuration) throws SomethingsWrongException{		
		//to able movementDoneLogic to change entity direction
		currentDirection = direction;
		
		deltaPixel += (frameDuration * speed) / 1000;
		
		
		tempo+=frameDuration;
		for(;deltaPixel>=1; deltaPixel--) {
			pxMisurati+=GameConstants.PIXE_PER_STEP;
			move(currentDirection);
		}
		
		if(tempo >= 1000){
			pixelAlSecondo = pxMisurati;
			pxMisurati = 0;
			tempo = 0;
		}
	}
	
	/**
	 * Move the entity for 1pixel, ignoring entity speed. Movement speed is the maximum allowed (correspond to FPS).
	 * If directions is null, try to move in current direction
	 * @param direction
	 * @throws SomethingsWrongException 
	 */
	public void move(Directions direction) throws SomethingsWrongException{		
		if(direction == null)
			direction = currentDirection;
		super.x += direction.getXMultiplier() * GameConstants.PIXE_PER_STEP;
		super.y += direction.getYMultiplier() * GameConstants.PIXE_PER_STEP;
		
		movementDoneLogic();
	}
	
	/**
	 * Logic to do when and entity move for 1px
	 * @throws SomethingsWrongException 
	 */
	protected void movementDoneLogic() throws SomethingsWrongException {		
	}

	/**
	 * Move the entity for 1pixel in current direction, ignoring entity speed. Movement speed is the maximum allowed (correspond to FPS).
	 * @param direction
	 * @throws SomethingsWrongException 
	 */
	public void move() throws SomethingsWrongException{				
		move(null);
	}
	
	/**
	 * Set entity speed
	 * @param speed
	 */
	protected void setSpeed(float speed){
		this.speed = speed;
	}
	
	/**
	 * Get entity speed
	 * @param speed
	 */
	public float getSpeed(){
		return speed;
	}
	
	public float getPixelAlSecondo(){
		return pixelAlSecondo;
	}
	
	/**
	 * 
	 * @param currentDirection
	 */
	public void setCurrentDirection(Directions currentDirection){
		this.currentDirection = currentDirection;
	}
	
	/**
	 * 
	 * @return
	 */
	public Directions getCurrentDirection(){
		return currentDirection;
	}
}
