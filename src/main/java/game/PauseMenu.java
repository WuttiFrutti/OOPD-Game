package game;

import buttons.ContinueButton;
import buttons.ExitButton;
import buttons.NextLevelButton;
import buttons.RestartButton;
import nl.han.ica.oopg.objects.GameObject;

import processing.core.PGraphics;

public class PauseMenu extends GameObject {
	private Game world;
	private boolean gameOver = false;
	
	private boolean drawPauseMenu = false;
	
	
	public PauseMenu(Game world) {
	this.world = world;
	}
	
	public void setDrawPauseMenu(boolean draw) {
		if(!draw && !gameOver) {
			world.deleteAllGameObjectsOfType(ContinueButton.class);
			world.deleteAllGameObjectsOfType(RestartButton.class);
			world.deleteAllGameObjectsOfType(ExitButton.class);
			world.deleteAllGameObjectsOfType(NextLevelButton.class);
			world.resumeGame();
		}else {
			world.pauseGame();
			ContinueButton continueButton = new ContinueButton("Continue", 40, world,this);
			world.addGameObject(continueButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 3);
			RestartButton restartButton = new RestartButton("Restart", 40, world,this);
			world.addGameObject(restartButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 2);
			ExitButton exitButton = new ExitButton("Exit", 40, world,this);
			world.addGameObject(exitButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 1.5f);
		}
	}
	
	public void nextLevelPause() {
		
		setDrawPauseMenu(true);
		
		world.deleteAllGameObjectsOfType(ContinueButton.class);
		if(world.getLevelAmount() - 1 != world.getCurrentLevel()) {
		NextLevelButton nextLevelButton = new NextLevelButton("Next Level", 40, world,this);
		world.addGameObject(nextLevelButton,(world.getWidth() / 2) - (nextLevelButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 3);
		setGameOver(true);
	}
	}
	
	public void gameOverPause() {
		setGameOver(true);
		setDrawPauseMenu(true);
		world.deleteAllGameObjectsOfType(ContinueButton.class);
		
	}
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	@Override
	public void keyPressed(int keyCode, char key) {
		if(keyCode == 9 && !gameOver) {
			drawPauseMenu = !drawPauseMenu;
			setDrawPauseMenu(drawPauseMenu);
		}
	}

	@Override
	public void update() {

	}


	@Override
	public void draw(PGraphics g) {
		
	}


}