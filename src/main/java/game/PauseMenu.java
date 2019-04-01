package game;

import buttons.Button;
import buttons.ContinueButton;
import buttons.ExitButton;
import buttons.RestartButton;
import nl.han.ica.oopg.objects.GameObject;

import processing.core.PGraphics;

public class PauseMenu extends GameObject {
	private Game world;
	
	private boolean drawPauseMenu = false;
	
	
	public PauseMenu(Game world) {
	this.world = world;
	}
	
	public void setDrawPauseMenu(boolean draw) {
		drawPauseMenu = draw;
		if(!drawPauseMenu) {
			world.deleteAllGameObjectsOfType(ContinueButton.class);
			world.deleteAllGameObjectsOfType(RestartButton.class);
			world.deleteAllGameObjectsOfType(ExitButton.class);
			world.resumeGame();
		}
	}
	
	
	@Override
	public void keyPressed(int keyCode, char key) {
		if(keyCode == 9) {
			drawPauseMenu = !drawPauseMenu;
			world.pauseGame();
			ContinueButton continueButton = new ContinueButton("Continue", 40, world,this);
			world.addGameObject(continueButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 3);
			RestartButton restartButton = new RestartButton("Restart", 40, world,this);
			world.addGameObject(restartButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 2);
			ExitButton exitButton = new ExitButton("Exit", 40, world,this);
			world.addGameObject(exitButton,(world.getWidth() / 2) - (continueButton.getWidth() / 2) + world.getView().getViewport().getX(), world.getHeight() / 1.5f);
		}
	}

	@Override
	public void update() {

	}


	@Override
	public void draw(PGraphics g) {
		
	}

}
