package game;

import nl.han.ica.oopg.engine.GameEngine;
import nl.han.ica.oopg.objects.GameObject;
import nl.han.ica.oopg.objects.Sprite;
import nl.han.ica.oopg.tile.TileMap;
import nl.han.ica.oopg.tile.TileType;
import nl.han.ica.oopg.view.View;
import tiles.BackgroundTile;
import tiles.WallTile;

public class Game extends GameEngine {
	private Player player;

	public static String MEDIA_URL = "src/main/java/game/Media/";

	public static void main(String[] args) {
		Game tw = new Game();
		tw.runSketch();
	}

	@Override
	public void setupGame() {
		int worldWidth = 1024;
		int worldHeight = 1024;

		player = new Player(this);
		addGameObject(player, 200, 100);

		View view = new View(worldWidth, worldHeight);

		setView(view);
		size(worldWidth, worldHeight);
		view.setBackground(loadImage(Game.MEDIA_URL.concat("background.bmp")));
		initializeTileMap();
	}

	private void initializeTileMap() {
		Sprite WallSprite = new Sprite(this.MEDIA_URL.concat("Box.png"));
		Sprite BackgroundTile = new Sprite(this.MEDIA_URL.concat("seethrough.png"));
		TileType<WallTile> WallTileType = new TileType<>(WallTile.class, WallSprite);
		TileType<BackgroundTile> BackgroundTileType = new TileType<>(BackgroundTile.class, BackgroundTile);


		TileType[] tileTypes = { WallTileType, BackgroundTileType};
		int tileSize = 64;
		int tilesMap[][] = { 
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0,  1,  1,  1,  1,  1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0,  0,  0,  0,  0,  0, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0,  0,  0,  0, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1,  0,  0,  0,  0,  0, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0,  0,  0,  0,  0, -1, -1, -1,  0,  0,  0,  0,  0, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0,  0,  0,  0,  0, },
				{  0, -1, -1, -1, -1, -1, -1,  0,  0, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0, },
				{  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, },

		};
		tileMap = new TileMap(tileSize, tileTypes, tilesMap);
	}

	@Override
	public void update() {

	}

}