package buttons;

import game.Game;
import game.PauseMenu;

public class NextLevelButton extends Button{
	private PauseMenu pauseMenu;
	public NextLevelButton(String text, int fontSize, Game world, PauseMenu pauseMenu) {
		super(text, fontSize, world);
		this.pauseMenu = pauseMenu;
	}

	@Override
	public void clickedButton(Game game) {
		pauseMenu.setDrawPauseMenu(false);
		pauseMenu.setGameOver(false);
		game.resumeGame();
		game.startGame(game.getCurrentLevel() + 1);
		
	}
	
	
}
