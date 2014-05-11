package com.kof.snake.entities;

import com.kof.snake.entities.basicEntities.BaseEntity;
import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.java2dEngine.GameWindow;
import com.kof.snake.properties.PropertiesKey;

public class SchemaObstacle extends BaseEntity {

	protected SchemaObstacle(GameWindow gameWindow) throws SomethingsWrongException {
		super(gameWindow, PropertiesKey.SCHEMA_OBSTACLE_BODY);
	}

}
