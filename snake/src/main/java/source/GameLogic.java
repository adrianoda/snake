package source;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import source.entities.AppleEntity;
import source.entities.SnakeEntity;
import source.entities.basicEntities.BaseEntity;
import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.utils.Directions;
import source.utils.GameConstants;
import source.utils.GridPosition;

public class GameLogic extends FrameRenderer {
	
	private static int START_X = GameConstants.GRID_DIMENSION * 5;
	private static int START_Y = GameConstants.GRID_DIMENSION * 5;
	
	private ArrayList<BaseEntity> entities = null;
	private ArrayList<BaseEntity> entitiesToRemove = null;
	private AppleEntity apple = null;
	private SnakeEntity snake = null;
	
	private ArrayList<GridPosition> freeGridPosition = null;
	
	private Directions snakeDirection;
	private boolean gameRunning = true;
	
	private Font font;
	private int score = 0;

	public GameLogic(GameWindow gameWindow) throws SomethingsWrongException {
		super(gameWindow);
		
		//Create free position on the grid
		freeGridPosition = new ArrayList<GridPosition>();
		for(int x=0; x<GameConstants.BOX_ACTIVE_END_X; x+=GameConstants.GRID_DIMENSION){
			for(int y=GameConstants.BOX_ACTIVE_START_Y+1; y<GameConstants.BOX_ACTIVE_END_Y + GameConstants.BOX_ACTIVE_START_Y; y+=GameConstants.GRID_DIMENSION){
				freeGridPosition.add(new GridPosition(x, y));	
			}
		}
		
//		this.stampa();
				
		//TODO inizializzare gli oggetti del gioco		
		snake = new SnakeEntity(gameWindow,this,START_X,START_Y);
		apple = new AppleEntity(gameWindow, this);
		
		entities = new ArrayList<BaseEntity>();
		entities.add(apple);
		entities.add(snake);
		
		entitiesToRemove = new ArrayList<BaseEntity>();		
		font = new Font(Font.MONOSPACED, Font.BOLD, 12);
	}

	/**
	 * Game logic executed each frame
	 * @param graphic
	 * @throws SomethingsWrongException 
	 */
	@Override
	protected void doLogic() throws SomethingsWrongException {												
		snakeDirection = getSnakeDirection();
		
		//TODO move entity section
		if(gameRunning) {
			snake.move(snakeDirection, super.frameDuration);
		} 
				
		//TODO draw section
		for(int i=0; i<entities.size();i++){
			entities.get(i).draw();
		}
		drawHeaderBox();
				 
		entities.removeAll(entitiesToRemove);
		
		//TODO collisione
		for (int i=0;i<entities.size();i++) {				
			if (snake.collidesWith(entities.get(i))) {
				snake.collidedWith(entities.get(i));
				entities.get(i).collidedWith(snake);
			}
		}
		if(snake.collidesWithBorder()){
			notifyEndGame();
		}
		
	}
	
	/**
	 * TODO sistemare questione BOX
	 */
	private void drawHeaderBox() {				
		super.gameWindow.setFont(font, Color.LIGHT_GRAY);
		
		super.gameWindow.drawBox(GameConstants.BOX_HEADER_START_X, GameConstants.BOX_HEADER_START_Y, 
				GameConstants.BOX_HEADER_END_X, GameConstants.BOX_HEADER_END_Y);
		
		super.gameWindow.drawBox(GameConstants.BOX_ACTIVE_START_X, GameConstants.BOX_ACTIVE_START_Y, 
				GameConstants.BOX_ACTIVE_END_X, GameConstants.BOX_ACTIVE_END_Y);
		
		int height = super.gameWindow.getFontMetrics(font).getHeight();
		int width = super.gameWindow.getFontMetrics(font).stringWidth(GameConstants.VERSION);
		
		super.gameWindow.writeMessage("Score: " + score + " Speed: " + snake.getPixelAlSecondo(), 3, height);
		super.gameWindow.writeMessage(GameConstants.VERSION, GameConstants.GAME_RES_X - width - 2, height);
	}

	/**
	 * 
	 */
	public void notifyAppleEated(){
		score += 10;		
		apple.snakeGetYou();		
	}
	
	/**
	 * 
	 */
	public void notifyEndGame(){
		gameRunning = false;
		
		super.gameWindow.setFont(font, Color.RED);
		int height = super.gameWindow.getFontMetrics(font).getHeight();
		int width = super.gameWindow.getFontMetrics(font).stringWidth(GameConstants.END_GAME_MESSAGE);
		int pos = (GameConstants.BOX_HEADER_END_X / 2) - (width / 2);
		gameWindow.writeMessage(GameConstants.END_GAME_MESSAGE, pos, height);
		
//		stampa();
	}

	/**
	 * Check if movement key is pressed. 
	 * @return
	 */
	private Directions getSnakeDirection() { 
		if(gameWindow.isKeyPressed(KeyEvent.VK_UP) && snake.getCurrentDirection() != Directions.DOWN){
			return Directions.UP;
		}		
		if(gameWindow.isKeyPressed(KeyEvent.VK_DOWN) && snake.getCurrentDirection() != Directions.UP){
			return Directions.DOWN;
		} 
		if(gameWindow.isKeyPressed(KeyEvent.VK_LEFT) && snake.getCurrentDirection() != Directions.RIGHT){
			return Directions.LEFT;
		} 
		if(gameWindow.isKeyPressed(KeyEvent.VK_RIGHT) && snake.getCurrentDirection() != Directions.LEFT){
			return Directions.RIGHT;
		} 		
		if(gameWindow.isKeyPressed(KeyEvent.VK_ESCAPE)){
			System.exit(0);
		} 

		return snakeDirection!=null?snakeDirection:snake.getCurrentDirection();
	}

	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void removeGridPosition(GridPosition position) {
		freeGridPosition.remove(position);
//		System.out.print("Remove<" + position.getX() + "-" + position.getY() + "> --> ");
//		stampa();
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void addGridPosition(GridPosition position) {		
		freeGridPosition.add(position.clone());
//		System.out.print("Add<" + position.getX() + "-" + position.getY() + ">   --> ");
//		stampa();
	}
	
	/**
	 * 
	 * @return
	 */
	public GridPosition getFreePosition(){		
		int p = new Random().nextInt(freeGridPosition.size());
		return freeGridPosition.get(p);
	}

	public void stampa() {
		for(int i=0; i<freeGridPosition.size(); i++){
			System.out.print("<" + freeGridPosition.get(i).getX() + "-" + freeGridPosition.get(i).getY() + ">");
		}
		System.out.println("");
		
	}

}
