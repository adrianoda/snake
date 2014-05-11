package source.utils;

public class GameConstants {
	public final static String VERSION = "v0.1dev1";
	public final static String END_GAME_MESSAGE = "DOH!";
	
	public final static int GAME_RES_X = 300;
	public final static int GAME_RES_Y = 200;
	
	public final static int GRID_DIMENSION = 20;
	
	//TODO sistemare questione BOX
	//Header box, where to show score and info
	public final static int BOX_HEADER_START_X = 0;
	public final static int BOX_HEADER_END_X = GAME_RES_X;
	public final static int BOX_HEADER_START_Y = 0;
	public final static int BOX_HEADER_END_Y = GRID_DIMENSION - 1;
	
	//Active box, where game run
	public final static int BOX_ACTIVE_START_X = 0;
	public final static int BOX_ACTIVE_END_X = GAME_RES_X;
	public final static int BOX_ACTIVE_START_Y = BOX_HEADER_END_Y;
	public final static int BOX_ACTIVE_END_Y = GAME_RES_Y - BOX_HEADER_END_Y;
		
		
	public final static int PIXE_PER_STEP = 1;
	public final static int DEFAULT_ENTITY_SPEED = 40;
	

	
	public final static String TRUE = "true";
}
