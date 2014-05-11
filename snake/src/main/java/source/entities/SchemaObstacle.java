package source.entities;

import source.entities.basicEntities.BaseEntity;
import source.exceptions.SomethingsWrongException;
import source.java2dEngine.GameWindow;
import source.properties.PropertiesKey;

public class SchemaObstacle extends BaseEntity {

	protected SchemaObstacle(GameWindow gameWindow) throws SomethingsWrongException {
		super(gameWindow, PropertiesKey.SCHEMA_OBSTACLE_BODY);
	}

}
