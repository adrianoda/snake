package com.kof.snake.entities;

import java.util.ArrayList;

import com.kof.snake.GameLogic;
import com.kof.snake.entities.basicEntities.BaseEntity;
import com.kof.snake.entities.basicEntities.MovableEntity;
import com.kof.snake.entities.basicEntities.MovableGridEntity;
import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.java2dEngine.GameWindow;
import com.kof.snake.properties.PropertiesKey;
import com.kof.snake.properties.PropertiesLoader;
import com.kof.snake.utils.Directions;
import com.kof.snake.utils.GameConstants;
import com.kof.snake.utils.GridPosition;

public class SnakeEntity extends MovableGridEntity {	
	
	private Directions startDir = Directions.LEFT;	
	private ArrayList<MovableEntity> snake;
	private GameLogic gameLogic;
	private boolean appleEated;
	private boolean freeLastPosition;

	//To clear snake position on the grid
	private GridPosition lastPosition;
	
	public SnakeEntity(GameWindow gameWindow, GameLogic gameLogic, int startX, int startY) throws SomethingsWrongException {
		
		// Create the snake 
		super(gameWindow, PropertiesKey.SNAKE_HEAD_LEFT);			
		MovableEntity bodyPart = new MovableEntity(gameWindow, PropertiesKey.SNAKE_BODY); 
		MovableEntity tailPart = new MovableEntity(gameWindow, PropertiesKey.SNAKE_TAIL_LEFT);	 	
		this.snake = new ArrayList<MovableEntity>();
		this.snake.add(bodyPart);
		this.snake.add(tailPart);
		
		// Set initial position and direction
		super.setPosition(startX, startY);
		bodyPart.setPosition(startX + GameConstants.GRID_DIMENSION, startY); 
		tailPart.setPosition(bodyPart.getX()+GameConstants.GRID_DIMENSION, startY);
		super.currentDirection = startDir;
		bodyPart.setCurrentDirection(startDir);
		tailPart.setCurrentDirection(startDir);
		lastPosition = new GridPosition(tailPart.getX(), tailPart.getY());
						
		// Set snake speed
		float snakeSpeed;
		try {
			snakeSpeed = Float.valueOf(PropertiesLoader.getProperties(PropertiesKey.GAME_SNAKE_SPEED));
		} catch (NumberFormatException e){
			throw new SomethingsWrongException("SnakeEntity - Incorrect value of property: <" + PropertiesKey.GAME_SNAKE_SPEED.getKeyValue() + ">");
		}
		super.speed = snakeSpeed;
		this.gameLogic = gameLogic;
		this.appleEated = false;
		this.freeLastPosition = true;
		
		// Notify occupied position on the grid
		gameLogic.removeGridPosition(new GridPosition(super.x, super.y));
		for(int i=0;i<snake.size();i++){
			gameLogic.removeGridPosition(new GridPosition(snake.get(i).getX(), snake.get(i).getY()));
		}
	}
	
	/**
	 * Each time head moves body parts must move
	 */
	protected void movementDoneLogic() throws SomethingsWrongException {		
		for(int i=0; i<snake.size(); i++){
			snake.get(i).move();
		}
		
		super.movementDoneLogic();
	}
	
	/**
	 * Switch snake part directions
	 * @throws SomethingsWrongException 
	 */
	protected void gridMovementDoneLogic() throws SomethingsWrongException{				
		
//		super.speed += 10;
		
		//Swtich body part direction and update snake position
		Directions currDir = super.previousDirection;	
		for(int i=0; i<snake.size(); i++){
			Directions prevDir = snake.get(i).getCurrentDirection();
			snake.get(i).setCurrentDirection(currDir);
			currDir = prevDir;			
		}
				
		//Notify new position on the grid
		gameLogic.removeGridPosition(new GridPosition(super.x, super.y));
		if(freeLastPosition){
			gameLogic.addGridPosition(lastPosition);
			MovableEntity tail = snake.get(snake.size()-1);
			lastPosition.setPosition(tail.getX(), tail.getY());
		}
		freeLastPosition = true;
			
		//An apple was eated! Snake grows
		if(appleEated){
			addBodyPart();
			super.speed += 10;
			appleEated = false;
			freeLastPosition = false;
			gameLogic.notifyAppleEated();
		}		
	}
	
	/**
	 * Draw the snake
	 */
	public void draw(){
		super.draw();
		for(int i=0;i<snake.size();i++){
			snake.get(i).draw();
		}
	}
	
	/**
	 * Action do be done when this entity collided with other
	 * @param 
	 * @throws SomethingsWrongException 
	 */
	public void collidedWith(BaseEntity other) {	
		if(other instanceof AppleEntity){
			appleEated = true;
		}
		else if(other instanceof SnakeEntity){
			gameLogic.notifyEndGame();
		}
	}
	
	/**
	 * 
	 * @throws SomethingsWrongException
	 */
	private void addBodyPart() throws SomethingsWrongException{
		MovableEntity bodyPart = new MovableEntity(gameLogic.getGameWindow(), PropertiesKey.SNAKE_BODY);
		MovableEntity tailPart = snake.get(snake.size()-1);
		
		bodyPart.setPosition(tailPart.getX(), tailPart.getY());
		bodyPart.setCurrentDirection(tailPart.getCurrentDirection());
		tailPart.setCurrentDirection(Directions.STOP);
		
		snake.remove(tailPart);
		snake.add(bodyPart);
		snake.add(tailPart);		
	}
	
	/**
	 * 
	 */
	public boolean collidesWith(BaseEntity other) {			
		if(other instanceof SnakeEntity) {
			for(int i=1; i<snake.size();i++){
				if(super.collidesWith(snake.get(i)))
					return true;			
			}
			return false;
		} 
		else return super.collidesWith(other);
	}
	
	/**TODO sistemare questione BOX
	 * Check if the snake collides with border. Limit are 0,0 - resx,resy
	 * @return
	 */
	public boolean collidesWithBorder(){
		if(x < GameConstants.BOX_ACTIVE_START_X || y < GameConstants.BOX_ACTIVE_START_Y || 
				x > GameConstants.BOX_ACTIVE_END_X-getWidth() || y > GameConstants.BOX_ACTIVE_START_Y + GameConstants.BOX_ACTIVE_END_Y-getWidth()){
			return true;			
		}		
		return false;
	}
	
}
