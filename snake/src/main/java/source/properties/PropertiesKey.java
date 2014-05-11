package source.properties;

/**
 * Allowed property key values
 * 
 * @author adrianoda
 *
 */
public enum PropertiesKey {	
	
	SHOW_FPS				(PropConst.PREFIX_GAME + "showFps"),
	GAME_SNAKE_SPEED		(PropConst.PREFIX_GAME + "snakeSpeed"),
	
	SCHEMA_OBSTACLE_BODY	(PropConst.PREFIX_SPRITE + PropConst.ENTITY_OBSTACLE + PropConst.PART_BODY),
	
	APPLE_BODY				(PropConst.PREFIX_SPRITE + PropConst.ENTITY_APPLE + PropConst.PART_BODY),
	
	SNAKE_BODY				(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_BODY),
	SNAKE_HEAD_UP			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_HEAD + PropConst.PART_ORIENT_UP),
	SNAKE_HEAD_DOWN			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_HEAD + PropConst.PART_ORIENT_DOWN),
	SNAKE_HEAD_LEFT			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_HEAD + PropConst.PART_ORIENT_LEFT),
	SNAKE_HEAD_RIGHT		(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_HEAD + PropConst.PART_ORIENT_RIGHT),
	SNAKE_TAIL_UP			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_TAIL + PropConst.PART_ORIENT_UP),
	SNAKE_TAIL_DOWN			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_TAIL + PropConst.PART_ORIENT_DOWN),
	SNAKE_TAIL_LEFT			(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_TAIL + PropConst.PART_ORIENT_LEFT),
	SNAKE_TAIL_RIGHT		(PropConst.PREFIX_SPRITE + PropConst.ENTITY_SNAKE + PropConst.PART_TAIL + PropConst.PART_ORIENT_RIGHT),	
	;
	
	private String keyValue;
	
	private PropertiesKey(String keyValue){
		this.keyValue = keyValue;
	}

	/**
	 * Key value in properties file
	 * @return
	 */
	public String getKeyValue() {
		return keyValue;
	}
}
