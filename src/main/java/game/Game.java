package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import nl.han.ica.oopg.view.View;
import nl.han.ica.oopg.view.Viewport;
import processing.core.PImage;
import tiles.BackgroundTile;
import tiles.WallTile;

public class Game extends GameEngine {
	private Player player;
	private int screenXSize = 1024;
	private int screenYSize = 768;
	private PImage backgroundImage = loadImage(Game.MEDIA_URL.concat("background.bmp"));
	private Viewport followingView = new Viewport(-500, -1, 1536, 1024);
	private static ArrayList<Room> rooms = new ArrayList<>();
	
	public static String MEDIA_URL = "src/main/java/game/Media/";
	
	
	
	public static void main(String[] args) {
		Game tw = new Game();
		tw.runSketch();		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			rooms.addAll(objectMapper.readValue(new File("resources/levels"), new TypeReference<List<Room>>(){}));
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void startGame(int level) {
		int levelYSize = 1024, levelXSize = 1536;
		initializeTileMap(level);
		deleteAllGameOBjects();
		player = new Player(this);
		addGameObject(player, 200, 50);
//		CenterFollowingViewport followingView = new CenterFollowingViewport(player, levelXSize, levelYSize);
//		followingView.setTolerance(0, 0, 0, 0);
		View Level = new View(followingView, levelXSize, levelYSize);
		backgroundImage.resize(screenXSize, screenYSize);
		background(backgroundImage);
		setView(Level);
	}

	public void levelSelector() {
		deleteAllGameOBjects();
		for (int i = 0; i < 3; i++) {
			LevelButton[] levels = new LevelButton[3];
			levels[i] = new LevelButton(this, Integer.toString(i + 1), 20, 100, 100, i);
			addGameObject(levels[i], 200 + (200 * i), 512);
		}
	}

	@Override
	public void setupGame() {

		View menu = new View(screenXSize, screenYSize);

		setView(menu);
		size(screenXSize, screenYSize);
		backgroundImage.resize(screenXSize, screenYSize);
		menu.setBackground(backgroundImage);

		startButton startButton = new startButton(this, "Start Game", 40);
		addGameObject(startButton, (screenXSize / 2) - (startButton.getWidth() / 2),
				(screenYSize / 2) - (startButton.getHeight() / 2));
	}

	public void showLevelButtons() {

	}

	private void initializeTileMap(int level) {
		Sprite WallSprite = new Sprite(this.MEDIA_URL.concat("Box.png"));
		Sprite BackgroundTile = new Sprite(this.MEDIA_URL.concat("seethrough.png"));
		TileType<WallTile> WallTileType = new TileType<>(WallTile.class, WallSprite);
		TileType<BackgroundTile> BackgroundTileType = new TileType<>(BackgroundTile.class, BackgroundTile);

		TileType[] tileTypes = { WallTileType, BackgroundTileType };
		int tileSize = 64;
		
		
		Room room = rooms.get(level);
		tileMap = new TileMap(tileSize, tileTypes, room.getTilesMap());
		
//		int tilesMap[][][] = {
//				// level 0
//				{ { 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } },
//				// level 1
//				{ { 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, 0, 0, 0, -1 },
//						{ 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, 0, 0, 0, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1 },
//						{ 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, 0, 0, 0, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, },
//				// level 2
//				{ { 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 1, 1, 1, 1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, 0, 0, 0, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, 0, 0, 0, -1, -1, 0, 0, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1, -1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
//								-1 },
//						{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, } };
		
	}

	@Override
	public void update() {
		followingView.setX(followingView.getX() + 2);
	}

}