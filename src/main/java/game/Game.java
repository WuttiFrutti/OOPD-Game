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
import tiles.WallTile;

public class Game extends GameEngine {
	private int screenXSize = 1600;
	private int screenYSize = 896;
	private static ArrayList<Room> rooms = new ArrayList<>();
	private int roomSpeed;
	public static String MEDIA_URL = "src/main/java/game/Media/";
	private View levelView;
	private int update = 0;
	private int maxLevelSize;
	
	
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
		Room room = rooms.get(level);		
		initializeTileMap(level, room.getTilesMap());
		roomSpeed = room.getSpeed();
		deleteAllGameOBjects();
		Player player = new Player(this);
		addGameObject(player, 128, 128);
		levelView = new View(room.getViewPortX(), room.getViewPortY());
		levelView.setBackground(64, 64, 64);
		setView(levelView);
		PauseMenu pause = new PauseMenu(this);
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
		Sprite WallSprite = new Sprite(this.MEDIA_URL.concat("Box.png"));
		Sprite BackgroundTile = new Sprite(this.MEDIA_URL.concat("seethrough.png"));
		TileType<WallTile> WallTileType = new TileType<>(WallTile.class, WallSprite);
		TileType<BackgroundTile> BackgroundTileType = new TileType<>(BackgroundTile.class, BackgroundTile);
		TileType[] tileTypes = { WallTileType, BackgroundTileType };
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
	
	
}