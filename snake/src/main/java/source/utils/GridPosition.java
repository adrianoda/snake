package source.utils;

public class GridPosition {
	private int x;
	private int y;
	
	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		GridPosition other = (GridPosition) obj;
		if(other.getX() == x && other.getY() == y)
			return true;
		else
			return false;
	}
	
	
	public GridPosition clone(){
		return new GridPosition(x, y);		
	}
}
