package game;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import buttons.LevelButton;
import buttons.startButton;
import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.userinput.IKeyInput;
import nl.han.ica.oopg.view.View;
import processing.core.PImage;
import tiles.BackgroundTile;
import tiles.SpikeTile;
import tiles.WallTile;
import tiles.WinTile;

public class Game extends GameEngine {
	private int screenXSize = 1600;
	private int screenYSize = 896;
	private static ArrayList<Room> rooms = new ArrayList<>();
	private int roomSpeed;
	public static String MEDIA_URL = "resources/sprites/";
	private View levelView;
	private int update = 0;
	private PauseMenu pause;
	private int currentLevel;
	private int maxLevelSize;
	private Player player;
	
	
	public static void main(String[] args) {
		Game tw = new Game();
		tw.runSketch();
		roomsLoader();
		
	}

	private static void roomsLoader() {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			rooms.addAll(objectMapper.readValue(new File("resources/levels"), new TypeReference<List<Room>>() {	}));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetPlayer() {
		deleteAllGameObjectsOfType(Player.class);
		Player player = new Player(this);
		addGameObject(player, 128, 128);
	}

	public void startGame(int level) {
		currentLevel = level;
		Room room = rooms.get(level);		
		initializeTileMap(level, room.getTilesMap());
		roomSpeed = room.getSpeed();
		deleteAllGameOBjects();
		player = new Player(this);
		addGameObject(player, 128, 128);
		levelView = new View(room.getViewPortX(), room.getViewPortY());
		levelView.setBackground(64, 64, 64);
		setView(levelView);
		pause = new PauseMenu(this);
		addGameObject(pause);
		maxLevelSize = room.getRoomX();
	}
	
	

	public void levelSelector() {
		int buttonAmount = rooms.size();
		int newLine = 0;
		int resetI = 0;
		int fontSize = 20;
		int levelButtonWidth = 100;
		int levelButtonHeight = 100;
		int levelButtonOffSet = 256;
		int levelButtonDistance = 128;
		deleteAllGameOBjects();
		LevelButton[] levels = new LevelButton[buttonAmount];
		for (int i = 0; i < buttonAmount; i++) {
			resetI++;
			if (i % 4 == 0) {
				newLine += levelButtonDistance;
				resetI = 0;
			}
			levels[i] = new LevelButton(this, Integer.toString(i + 1), fontSize, levelButtonWidth, levelButtonHeight,i);
			addGameObject(levels[i], levelButtonOffSet + levelButtonDistance * resetI, levelButtonOffSet + newLine);
		}
	}
	
	public void restartLevel() {
		
	}

	@Override
	public void setupGame() {
		int fontSize = 40;
		View menu = new View(screenXSize, screenYSize);
		menu.setBackground(64, 64, 64);
		setView(menu);
		size(screenXSize, screenYSize);
		startButton startButton = new startButton("Start Game", fontSize, this);
		addGameObject(startButton, (screenXSize / 2) - (startButton.getWidth() / 2),
				(screenYSize / 2) - (startButton.getHeight() / 2));
	}

	private void initializeTileMap(int level, int[][] tilesMap) {	
		TileType<WinTile> WinTileType = new TileType<>(WinTile.class, new Sprite(this.MEDIA_URL.concat("WinSprite.png")));
		TileType<WallTile> WallTileType0 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/Box.png")));
		TileType<WallTile> WallTileType1 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxB.png")));
		TileType<WallTile> WallTileType2 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxBT.png")));
		TileType<WallTile> WallTileType3 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxL.png")));
		TileType<WallTile> WallTileType4 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLB.png")));
		TileType<WallTile> WallTileType5 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLBR.png")));
		TileType<WallTile> WallTileType6 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLBRT.png")));
		TileType<WallTile> WallTileType7 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLBT.png")));
		TileType<WallTile> WallTileType8 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLR.png")));
		TileType<WallTile> WallTileType9 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLRT.png")));
		TileType<WallTile> WallTileType10 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxLT.png")));
		TileType<WallTile> WallTileType11 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxR.png")));
		TileType<WallTile> WallTileType12 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxRB.png")));
		TileType<WallTile> WallTileType13 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxRBT.png")));
		TileType<WallTile> WallTileType14 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxRT.png")));
		TileType<WallTile> WallTileType15 = new TileType<>(WallTile.class, new Sprite(this.MEDIA_URL.concat("Boxes/BoxT.png")));
		TileType<BackgroundTile> BackgroundTileType = new TileType<>(BackgroundTile.class, new Sprite(this.MEDIA_URL.concat("seethrough.png")));
		TileType<SpikeTile> SpikeTileType = new TileType<>(SpikeTile.class, new Sprite(this.MEDIA_URL.concat("SpikeTile.png")));
		TileType<SpikeTile> SpikeTileTypeLined = new TileType<>(SpikeTile.class, new Sprite(this.MEDIA_URL.concat("SpikeTileLined.png")));
		
		TileType[] tileTypes = { WallTileType0,WallTileType1, WallTileType2, WallTileType3, WallTileType4, WallTileType5, WallTileType6, WallTileType7, WallTileType8, WallTileType9, WallTileType10, WallTileType11, WallTileType12, WallTileType13, WallTileType14, WallTileType15, BackgroundTileType, SpikeTileType, WinTileType, SpikeTileTypeLined};
		int tileSize = 64;
		Room room = rooms.get(level);
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);

	}

	@Override
	public void update() {
		update ++;
		if(roomSpeed != 0) {
		if(update % roomSpeed == 0) {
		updateView();
		}
		}
	}

	public void updateView() {
		if (levelView != null && levelView.getViewport().getX() < maxLevelSize - levelView.getViewport().getZoomWidth()) {
			levelView.getViewport().setX(levelView.getViewport().getX() + 1);
		}
	}
	
	public void resetTileMap() {
		tileMap = new TileMap(0);
	}
	
	public void nextLevel() {
		startGame(currentLevel + 1);
	}
	
	public void gameOver() {
		pause.gameOverPause();
	}
	
	public PauseMenu getPauseMenu() {
		return pause;
	}
	
	public Player getPlayer() {
		return player;
	}
}