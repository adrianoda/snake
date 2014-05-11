package source.entities.basicEntities;

import java.awt.Rectangle;

import source.GameLogic;
import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.java2dEngine.Sprite;
import source.java2dEngine.SpriteLoader;
import source.properties.PropertiesKey;
import source.properties.PropertiesLoader;

public class BaseEntity {
	
	private Sprite sprite;
	protected GameLogic gameLogic;
	
	protected int x;
	protected int y;	
	
	/** The rectangle used for this entity during collisions  resolution */
	private Rectangle me = new Rectangle();
	/** The rectangle used for other entities during collision resolution */
	private Rectangle him = new Rectangle();
	
	protected BaseEntity(GameWindow gameWindow, PropertiesKey fileNameProperty) throws SomethingsWrongException{
		String fileName = PropertiesLoader.getProperties(fileNameProperty);
		this.sprite = SpriteLoader.getSprite(gameWindow, fileName);
	}	
	
	/**
	 * Draw the sprite at current x,y position
	 */
	public void draw(){
		sprite.draw(x, y);
	}	
	
	/**
	 * Check if this entity collides with other
	 * @param 
	 * @return 
	 */
	public boolean collidesWith(BaseEntity other) {
		me.setBounds(x, y, getWidth(), getHeight());
		him.setBounds(other.getX(), other.getY(), other.getWidth(), other.getHeight());

		return me.intersects(him);
	}
	
	/**
	 * Action do be done when this entity collided with other
	 * @param 
	 * @throws SomethingsWrongException 
	 */
	public void collidedWith(BaseEntity other) throws SomethingsWrongException{		
	}
	
	/**
	 * Put entity to position x,y
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Actual orizontal position
	 * @return
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Actual vertical position
	 * @return
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Entity width in pixel
	 * @return
	 */
	public int getWidth(){
		return sprite.getWidth();
	}
	
	/**
	 * Entity height in pixel
	 * @return
	 */
	public int getHeight(){
		return sprite.getHeight();
	}

}
