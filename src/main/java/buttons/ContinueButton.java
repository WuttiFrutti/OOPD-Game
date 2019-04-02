package buttons;

import game.Game;
import game.PauseMenu;

public class ContinueButton extends Button{
	private PauseMenu pauseMenu;
	
	public ContinueButton(String text, int fontSize, Game game, PauseMenu pauseMenu) {
		super(text, fontSize, game);
		this.pauseMenu = pauseMenu;
	}

	@Override
	public void clickedButton(Game game) {
		pauseMenu.setDrawPauseMenu(false);
		game.resumeGame();
	}

}
