package source.properties;

/**
 * Store constants about:
 * 	- file name containing the properties
 *  - properties key utils
 *  
 * @author adrianoda
 *
 */
public class PropConst {
	
	//************************
	//Properties file name constants
	
	protected static final String FILE_NAME = "conf";	
	protected static final String FILE_PATH = "META-INF";
	
	protected static final String FILE_RESOURCE_NAME = FILE_PATH + "/" + FILE_NAME + ".properties";
	protected static final String FILE_BASE_NAME = FILE_PATH + "." + FILE_NAME;
	
	
	//************************
	//Properties key constants
	
	protected final static String PREFIX_GAME = "game.";
	protected final static String PREFIX_SPRITE = "sprite.";
	
	protected final static String ENTITY_SNAKE = "snake.";
	protected final static String ENTITY_APPLE = "apple.";
	protected final static String ENTITY_OBSTACLE = "apple.";
	
	protected final static String PART_BODY = "body";
	
	protected final static String PART_TAIL = "tail.";
	protected final static String PART_HEAD = "head.";
	
	protected final static String PART_ORIENT_UP = "up";
	protected final static String PART_ORIENT_DOWN = "down";
	protected final static String PART_ORIENT_LEFT = "left";
	protected final static String PART_ORIENT_RIGHT = "right";
	
	
}
