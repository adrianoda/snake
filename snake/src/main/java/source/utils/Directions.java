package source.utils;

public enum Directions {
	UP(0,-1),
	DOWN(0,1),
	LEFT(-1,0), 
	RIGHT(1,0),
	STOP(0,0)
	;
	
	private int xMultiplier;
	private int yMultiplier;
	
	private Directions(int xMultiplier, int yMultiplier){
		this.xMultiplier = xMultiplier;
		this.yMultiplier = yMultiplier;
	}

	public int getXMultiplier() {
		return xMultiplier;
	}

	public int getYMultiplier() {
		return yMultiplier;
	}
	
}
