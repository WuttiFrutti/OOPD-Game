package game;

public class Room {
	private int viewPortX;
	private int viewPortY;
	private int tilesMap[][];
	private int roomX;
	private int roomY;
	private int speed;

	public Room(int tilesMap[][], int roomX, int roomY, int speed, int viewPortX, int viewPortY) {
		this.tilesMap = tilesMap;
		this.roomX = roomX;
		this.roomY = roomY;
		this.speed = speed;
		this.viewPortX = viewPortX;
		this.viewPortY = viewPortY;

	}

	public Room() {

	}

	public void setRoomX(int roomX) {
		this.roomX = roomX;
	}

	public void setRoomY(int roomY) {
		this.roomY = roomY;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setTilesMap(int[][] tilesMap) {
		this.tilesMap = tilesMap;
	}

	public int getRoomX() {
		return roomX;
	}

	public int getRoomY() {
		return roomY;
	}

	public int getSpeed() {
		return speed;
	}

	public int[][] getTilesMap() {
		return tilesMap;
	}

	public int getViewPortX() {
		return viewPortX;
	}

	public int getViewPortY() {
		return viewPortY;
	}

	public void setViewPortX(int viewPortX) {
		this.viewPortX = viewPortX;
	}

	public void setViewPortY(int viewPortY) {
		this.viewPortY = viewPortY;
	}

}
