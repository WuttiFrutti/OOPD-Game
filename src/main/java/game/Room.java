package game;

public class Room {
	private int tilesMap[][];
	private int roomX;
	private int roomY;
	private float speed;

	public Room(int tilesMap[][], int roomX, int roomY, float speed) {
		this.tilesMap = tilesMap;
		this.roomX = roomX;
		this.roomY = roomY;
		this.speed = speed;

	}

	public Room() {

	}

	public void setRoomX(int roomX) {
		this.roomX = roomX;
	}

	public void setRoomY(int roomY) {
		this.roomY = roomY;
	}

	public void setSpeed(float speed) {
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

	public float getSpeed() {
		return speed;
	}

	public int[][] getTilesMap() {
		return tilesMap;
	}

}
