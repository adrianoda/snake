package com.kof.snake;

import java.util.concurrent.locks.LockSupport;

import com.kof.snake.exceptions.SomethingsWrongException;
import com.kof.snake.java2dEngine.GameWindow;

public abstract class FrameRenderer {
	
	private final static long FPS = 100;
	private final static long MAX_FRAME_DURATION = 1000 / FPS;		
	private long fpsCounterTime;
	private long fps;
	
	protected long frameDuration=MAX_FRAME_DURATION;
	protected GameWindow gameWindow;
	
	/**
	 * Init game resource
	 * @param gameWindow
	 */
	public FrameRenderer(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}	
	
	/**
	 * Render single frame 
	 * @param currentTimeMillis 
	 * @param graphic
	 * @throws SomethingsWrongException 
	 */
	public long frameRendering(long currentTimeMillis) throws SomethingsWrongException {									
		
		long start = getTime();
		
		doLogic();
		currentTimeMillis += MAX_FRAME_DURATION;
		LockSupport.parkUntil(currentTimeMillis);
		
		// Calculate number of frame per second
		fps++;
		long duration = getTime() - start;
		duration = duration > 0 ? duration:MAX_FRAME_DURATION;
		duration = duration < 15 * MAX_FRAME_DURATION ? duration:MAX_FRAME_DURATION;
		fpsCounterTime += duration;
		if(fpsCounterTime >= 1000){
			gameWindow.showFps(fps);
			fpsCounterTime = 0;			
			fps = 0;
		}
		return currentTimeMillis;
	}
	
	/**
	 * 
	 * @return
	 */
	public GameWindow getGameWindow(){
		return gameWindow;
	}
	
	/**
	 * Game logic executed each frame
	 * @param graphic
	 */
	protected abstract void doLogic() throws SomethingsWrongException;

	/**
	 * get time in mills
	 * @return current time in mills
	 */
	private long getTime() {		
		return System.nanoTime() / 1000000;
	}			
	
}

